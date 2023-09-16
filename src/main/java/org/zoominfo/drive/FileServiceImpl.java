package org.zoominfo.drive;//package org.example;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.zoominfo.drive.dao.UserRepository;
import org.zoominfo.drive.entity.User;

import java.util.Optional;

@GRpcService
public class FileServiceImpl extends FileServiceGrpc.FileServiceImplBase {

    private final UserRepository userRepository;

    @Autowired
    public FileServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }
    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        String username = request.getUsername();
        String password = request.getPassword();

        // Authenticate the user using Spring Data JPA
        Optional<User> userOptional = userRepository.findByName(username);

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            responseObserver.onNext(LoginResponse.newBuilder()
                    .setSuccess(true)
                    .setToken("Success Token")
                    .setMessage("Login successful")
                    .build());
        } else {
            responseObserver.onNext(LoginResponse.newBuilder()
                    .setSuccess(false)
                    .setToken("Failed Token")
                    .setMessage("Invalid username or password")
                    .build());
        }

        responseObserver.onCompleted();

//        String userName = request.getUsername();
//        String password = request.getPassword();
//        LoginResponse.Builder response = LoginResponse.newBuilder();
//        if ("admin".equals(userName) && "password".equals(password)) {
//            System.out.println("successful");
//            response.setToken("Success");
//        } else {
//            response.setToken("Failed");
//        }
//        responseObserver.onNext(response.build());
//        responseObserver.onCompleted();

    }

//    @Override
//    public void signup(SignupRequest request, StreamObserver<SignupResponse> responseObserver) {
//        // Implement signup logic
//    }
//
//    @Override
//    public void uploadFile(UploadFileRequest request, StreamObserver<UploadFileResponse> responseObserver) {
//        // Implement file upload logic
//    }
//
//    @Override
//    public void uploadFolder(UploadFolderRequest request, StreamObserver<UploadFolderResponse> responseObserver) {
//        // Implement folder upload logic
//    }
//
//    @Override
//    public void downloadFile(DownloadFileRequest request, StreamObserver<DownloadFileResponse> responseObserver) {
//        // Implement file download logic
//    }
//
//    @Override
//    public void deleteFile(DeleteFileRequest request, StreamObserver<DeleteFileResponse> responseObserver) {
//        // Implement file delete logic
//    }
}
