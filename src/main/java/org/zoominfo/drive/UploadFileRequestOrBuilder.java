// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: file_service.proto

package org.zoominfo.drive;

public interface UploadFileRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:file_service.UploadFileRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string token = 1;</code>
   * @return The token.
   */
  java.lang.String getToken();
  /**
   * <code>string token = 1;</code>
   * @return The bytes for token.
   */
  com.google.protobuf.ByteString
      getTokenBytes();

  /**
   * <code>string filename = 2;</code>
   * @return The filename.
   */
  java.lang.String getFilename();
  /**
   * <code>string filename = 2;</code>
   * @return The bytes for filename.
   */
  com.google.protobuf.ByteString
      getFilenameBytes();

  /**
   * <code>bytes data = 3;</code>
   * @return The data.
   */
  com.google.protobuf.ByteString getData();
}
