package org.zoominfo.drive;

import io.grpc.stub.StreamObserver;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: file_service.proto")
public final class FileServiceGrpc {

  private FileServiceGrpc() {}

  public static final String SERVICE_NAME = "file_service.FileService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.zoominfo.drive.LoginRequest,
      org.zoominfo.drive.LoginResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "login",
      requestType = org.zoominfo.drive.LoginRequest.class,
      responseType = org.zoominfo.drive.LoginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.zoominfo.drive.LoginRequest,
      org.zoominfo.drive.LoginResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<org.zoominfo.drive.LoginRequest, org.zoominfo.drive.LoginResponse> getLoginMethod;
    if ((getLoginMethod = FileServiceGrpc.getLoginMethod) == null) {
      synchronized (FileServiceGrpc.class) {
        if ((getLoginMethod = FileServiceGrpc.getLoginMethod) == null) {
          FileServiceGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<org.zoominfo.drive.LoginRequest, org.zoominfo.drive.LoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.zoominfo.drive.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.zoominfo.drive.LoginResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FileServiceMethodDescriptorSupplier("login"))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FileServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileServiceStub>() {
        @java.lang.Override
        public FileServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileServiceStub(channel, callOptions);
        }
      };
    return FileServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FileServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileServiceBlockingStub>() {
        @java.lang.Override
        public FileServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileServiceBlockingStub(channel, callOptions);
        }
      };
    return FileServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FileServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileServiceFutureStub>() {
        @java.lang.Override
        public FileServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileServiceFutureStub(channel, callOptions);
        }
      };
    return FileServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class FileServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *rpc signup(SignupRequest) returns (SignupResponse);
     *rpc uploadFile(UploadFileRequest) returns (UploadFileResponse);
     *rpc uploadFolder(UploadFolderRequest) returns (UploadFolderResponse);
     *rpc downloadFile(DownloadFileRequest) returns (DownloadFileResponse);
     *rpc deleteFile(DeleteFileRequest) returns (DeleteFileResponse);
     * </pre>
     */
    public void login(org.zoominfo.drive.LoginRequest request,
        io.grpc.stub.StreamObserver<org.zoominfo.drive.LoginResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                org.zoominfo.drive.LoginRequest,
                org.zoominfo.drive.LoginResponse>(
                  this, METHODID_LOGIN)))
          .build();
    }

      public abstract void signup(SignupRequest request, StreamObserver<SignupResponse> responseObserver);

    public abstract void uploadFile(UploadFileRequest request, StreamObserver<UploadFileResponse> responseObserver);

    public abstract void uploadFolder(UploadFolderRequest request, StreamObserver<UploadFolderResponse> responseObserver);

    public abstract void downloadFile(DownloadFileRequest request, StreamObserver<DownloadFileResponse> responseObserver);

    public abstract void deleteFile(DeleteFileRequest request, StreamObserver<DeleteFileResponse> responseObserver);
  }

  /**
   */
  public static final class FileServiceStub extends io.grpc.stub.AbstractAsyncStub<FileServiceStub> {
    private FileServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *rpc signup(SignupRequest) returns (SignupResponse);
     *rpc uploadFile(UploadFileRequest) returns (UploadFileResponse);
     *rpc uploadFolder(UploadFolderRequest) returns (UploadFolderResponse);
     *rpc downloadFile(DownloadFileRequest) returns (DownloadFileResponse);
     *rpc deleteFile(DeleteFileRequest) returns (DeleteFileResponse);
     * </pre>
     */
    public void login(org.zoominfo.drive.LoginRequest request,
        io.grpc.stub.StreamObserver<org.zoominfo.drive.LoginResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FileServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<FileServiceBlockingStub> {
    private FileServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *rpc signup(SignupRequest) returns (SignupResponse);
     *rpc uploadFile(UploadFileRequest) returns (UploadFileResponse);
     *rpc uploadFolder(UploadFolderRequest) returns (UploadFolderResponse);
     *rpc downloadFile(DownloadFileRequest) returns (DownloadFileResponse);
     *rpc deleteFile(DeleteFileRequest) returns (DeleteFileResponse);
     * </pre>
     */
    public org.zoominfo.drive.LoginResponse login(org.zoominfo.drive.LoginRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FileServiceFutureStub extends io.grpc.stub.AbstractFutureStub<FileServiceFutureStub> {
    private FileServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *rpc signup(SignupRequest) returns (SignupResponse);
     *rpc uploadFile(UploadFileRequest) returns (UploadFileResponse);
     *rpc uploadFolder(UploadFolderRequest) returns (UploadFolderResponse);
     *rpc downloadFile(DownloadFileRequest) returns (DownloadFileResponse);
     *rpc deleteFile(DeleteFileRequest) returns (DeleteFileResponse);
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.zoominfo.drive.LoginResponse> login(
        org.zoominfo.drive.LoginRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FileServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FileServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((org.zoominfo.drive.LoginRequest) request,
              (io.grpc.stub.StreamObserver<org.zoominfo.drive.LoginResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FileServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FileServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.zoominfo.drive.FileServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FileService");
    }
  }

  private static final class FileServiceFileDescriptorSupplier
      extends FileServiceBaseDescriptorSupplier {
    FileServiceFileDescriptorSupplier() {}
  }

  private static final class FileServiceMethodDescriptorSupplier
      extends FileServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FileServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FileServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FileServiceFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .build();
        }
      }
    }
    return result;
  }
}
