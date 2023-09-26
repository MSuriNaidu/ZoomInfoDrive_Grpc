package org.zoominfo.drive;

import com.google.protobuf.ByteString;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zoominfo.drive.dao.UserRepository;
import org.zoominfo.drive.entity.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@GRpcService
public class FileServiceImpl extends FileServiceGrpc.FileServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
    private static final String UPLOAD_DIRECTORY = "/path/to/upload/directory/";
    private static final String FILE_DIRECTORY = "/path/to/files/";

    private final UserRepository userRepository;

    @Autowired
    public FileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        log.info("LoginRequest is for the user: " + request.getUsername());
        String username = request.getUsername();
        String password = request.getPassword();

        // Authenticate the user using Spring Data JPA
        Optional<User> userOptional = userRepository.findByName(username);

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            log.info("Login is successfully done");
            responseObserver.onNext(LoginResponse.newBuilder()
                    .setSuccess(true)
                    .setToken("Success Token")
                    .setMessage("Login successful")
                    .build());
        } else {
            log.info("Login is failed");
            responseObserver.onNext(LoginResponse.newBuilder()
                    .setSuccess(false)
                    .setToken("Failed Token")
                    .setMessage("Invalid username or password")
                    .build());
        }

        responseObserver.onCompleted();
    }

    @Override
    public void signup(SignupRequest request, StreamObserver<SignupResponse> responseObserver) {
        log.info("Registration request received for " + request.getUsername());
        User user = new User();
        user.setName( request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        userRepository.save(user);
        responseObserver.onNext(SignupResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Registration is done successfully ")
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<UploadFileRequest> uploadFile(UploadFileRequest request, StreamObserver<UploadFileResponse> responseObserver) {
        return new StreamObserver<UploadFileRequest>() {
            private FileOutputStream fileOutputStream;
            private boolean firstChunk = true;

            @Override
            public void onNext(UploadFileRequest chunk) {
                try {
                    if (firstChunk) {
                        // Create a new file for the upload
                        String filePath = UPLOAD_DIRECTORY + "uploaded_file.ext"; // Change the extension accordingly
                        fileOutputStream = new FileOutputStream(filePath);
                        firstChunk = false;
                    }
                    // Write the chunk data to the file
                    fileOutputStream.write(chunk.getData().toByteArray());
                } catch (IOException e) {
                    log.error("Upload request is failed: " + e.getMessage());
                    responseObserver.onError(e);
                }
            }

            @Override
            public void onError(Throwable t) {
                log.error("Error upload is failed");
            }

            @Override
            public void onCompleted() {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    responseObserver.onNext(UploadFileResponse.newBuilder()
                            .setMessage("File upload completed")
                            .build());
                    responseObserver.onCompleted();
                } catch (IOException e) {
                   log.error("Upload is failed: " + e.getMessage());
                    responseObserver.onError(e);
                }
            }
        };
    }

    @Override
    public void uploadFolder(UploadFolderRequest request, StreamObserver<UploadFolderResponse> responseObserver) {
        try {

            String folderName = request.getFolderName();
            List<UploadFileRequest> folderItems = request.getFilesList();

            // Create a folder to store the uploaded items
            File folder = new File("uploads/" + folderName);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Iterate through folder items and save files or create subfolders
            for (UploadFileRequest item : folderItems) {
                String itemName = item.getFilename();
                byte[] fileData = item.getData().toByteArray();

                // Save the file
                File file = new File(folder, itemName);
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    fos.write(fileData);
                }
            }
            UploadFolderResponse response = UploadFolderResponse.newBuilder()
                    .setMessage("Folder uploaded successfully")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("An Error occured while uploading a folder: " + e.getMessage());
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription("Failed to upload folder: " + e.getMessage())
                            .asRuntimeException());
        }
    }

    @Override
    public void downloadFile(DownloadFileRequest request, StreamObserver<DownloadFileResponse> responseObserver) {
        try {
            log.info("Downloading file: " + request.getFilename());
            String fileName = request.getFilename();
            String filePath = FILE_DIRECTORY + fileName;

            FileInputStream fileInputStream = new FileInputStream(filePath);
            byte[] buffer = new byte[1024]; // Adjust the buffer size as needed
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                byte[] chunkData = new byte[bytesRead];
                System.arraycopy(buffer, 0, chunkData, 0, bytesRead);
                DownloadFileResponse chunk = DownloadFileResponse.newBuilder().setData(ByteString.copyFrom(chunkData)).build();
                responseObserver.onNext(chunk);
            }

            responseObserver.onCompleted();
            fileInputStream.close();
        } catch (IOException e) {
            log.error("Download failed: " + e.getMessage());
            responseObserver.onError(e);
        }
    }

    @Override
    public void deleteFile(DeleteFileRequest request, StreamObserver<DeleteFileResponse> responseObserver) {
        String fileName = request.getFilename();
        String filePath = FILE_DIRECTORY + fileName;

        File fileToDelete = new File(filePath);

        if (fileToDelete.exists() && fileToDelete.isFile() && fileToDelete.delete()) {
            responseObserver.onNext(DeleteFileResponse.newBuilder()
                    .setMessage("File deleted successfully.")
                    .build());
        } else {
            responseObserver.onNext(DeleteFileResponse.newBuilder()
                    .setMessage("File deletion failed or file not found.")
                    .build());
        }
        responseObserver.onCompleted();
    }
}
