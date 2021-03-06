// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Res_C_DeleteFile.proto

package com.lxd.protobuf.msg.result.console;

public final class DeleteFile {
  private DeleteFile() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface DeleteFile_OrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required bool success = 1;
    /**
     * <code>required bool success = 1;</code>
     *
     * <pre>
     *&#47;&lt; 结果
     * </pre>
     */
    boolean hasSuccess();
    /**
     * <code>required bool success = 1;</code>
     *
     * <pre>
     *&#47;&lt; 结果
     * </pre>
     */
    boolean getSuccess();

    // optional string error_msg = 2;
    /**
     * <code>optional string error_msg = 2;</code>
     *
     * <pre>
     *&#47;&lt; 失败信息
     * </pre>
     */
    boolean hasErrorMsg();
    /**
     * <code>optional string error_msg = 2;</code>
     *
     * <pre>
     *&#47;&lt; 失败信息
     * </pre>
     */
    java.lang.String getErrorMsg();
    /**
     * <code>optional string error_msg = 2;</code>
     *
     * <pre>
     *&#47;&lt; 失败信息
     * </pre>
     */
    com.google.protobuf.ByteString
        getErrorMsgBytes();
  }
  /**
   * Protobuf type {@code msg.result.console.DeleteFile_}
   *
   * <pre>
   *&#47;&lt; 删除文件结果
   * </pre>
   */
  public static final class DeleteFile_ extends
      com.google.protobuf.GeneratedMessage
      implements DeleteFile_OrBuilder {
    // Use DeleteFile_.newBuilder() to construct.
    private DeleteFile_(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private DeleteFile_(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final DeleteFile_ defaultInstance;
    public static DeleteFile_ getDefaultInstance() {
      return defaultInstance;
    }

    public DeleteFile_ getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private DeleteFile_(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              success_ = input.readBool();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              errorMsg_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.lxd.protobuf.msg.result.console.DeleteFile.internal_static_msg_result_console_DeleteFile__descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.lxd.protobuf.msg.result.console.DeleteFile.internal_static_msg_result_console_DeleteFile__fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_.class, com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_.Builder.class);
    }

    public static com.google.protobuf.Parser<DeleteFile_> PARSER =
        new com.google.protobuf.AbstractParser<DeleteFile_>() {
      public DeleteFile_ parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new DeleteFile_(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<DeleteFile_> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required bool success = 1;
    public static final int SUCCESS_FIELD_NUMBER = 1;
    private boolean success_;
    /**
     * <code>required bool success = 1;</code>
     *
     * <pre>
     *&#47;&lt; 结果
     * </pre>
     */
    public boolean hasSuccess() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required bool success = 1;</code>
     *
     * <pre>
     *&#47;&lt; 结果
     * </pre>
     */
    public boolean getSuccess() {
      return success_;
    }

    // optional string error_msg = 2;
    public static final int ERROR_MSG_FIELD_NUMBER = 2;
    private java.lang.Object errorMsg_;
    /**
     * <code>optional string error_msg = 2;</code>
     *
     * <pre>
     *&#47;&lt; 失败信息
     * </pre>
     */
    public boolean hasErrorMsg() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional string error_msg = 2;</code>
     *
     * <pre>
     *&#47;&lt; 失败信息
     * </pre>
     */
    public java.lang.String getErrorMsg() {
      java.lang.Object ref = errorMsg_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          errorMsg_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string error_msg = 2;</code>
     *
     * <pre>
     *&#47;&lt; 失败信息
     * </pre>
     */
    public com.google.protobuf.ByteString
        getErrorMsgBytes() {
      java.lang.Object ref = errorMsg_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        errorMsg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      success_ = false;
      errorMsg_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasSuccess()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBool(1, success_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getErrorMsgBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(1, success_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getErrorMsgBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code msg.result.console.DeleteFile_}
     *
     * <pre>
     *&#47;&lt; 删除文件结果
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_OrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.lxd.protobuf.msg.result.console.DeleteFile.internal_static_msg_result_console_DeleteFile__descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.lxd.protobuf.msg.result.console.DeleteFile.internal_static_msg_result_console_DeleteFile__fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_.class, com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_.Builder.class);
      }

      // Construct using com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        success_ = false;
        bitField0_ = (bitField0_ & ~0x00000001);
        errorMsg_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.lxd.protobuf.msg.result.console.DeleteFile.internal_static_msg_result_console_DeleteFile__descriptor;
      }

      public com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ getDefaultInstanceForType() {
        return com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_.getDefaultInstance();
      }

      public com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ build() {
        com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ buildPartial() {
        com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ result = new com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.success_ = success_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.errorMsg_ = errorMsg_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_) {
          return mergeFrom((com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ other) {
        if (other == com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_.getDefaultInstance()) return this;
        if (other.hasSuccess()) {
          setSuccess(other.getSuccess());
        }
        if (other.hasErrorMsg()) {
          bitField0_ |= 0x00000002;
          errorMsg_ = other.errorMsg_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasSuccess()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_ parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required bool success = 1;
      private boolean success_ ;
      /**
       * <code>required bool success = 1;</code>
       *
       * <pre>
       *&#47;&lt; 结果
       * </pre>
       */
      public boolean hasSuccess() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required bool success = 1;</code>
       *
       * <pre>
       *&#47;&lt; 结果
       * </pre>
       */
      public boolean getSuccess() {
        return success_;
      }
      /**
       * <code>required bool success = 1;</code>
       *
       * <pre>
       *&#47;&lt; 结果
       * </pre>
       */
      public Builder setSuccess(boolean value) {
        bitField0_ |= 0x00000001;
        success_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required bool success = 1;</code>
       *
       * <pre>
       *&#47;&lt; 结果
       * </pre>
       */
      public Builder clearSuccess() {
        bitField0_ = (bitField0_ & ~0x00000001);
        success_ = false;
        onChanged();
        return this;
      }

      // optional string error_msg = 2;
      private java.lang.Object errorMsg_ = "";
      /**
       * <code>optional string error_msg = 2;</code>
       *
       * <pre>
       *&#47;&lt; 失败信息
       * </pre>
       */
      public boolean hasErrorMsg() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional string error_msg = 2;</code>
       *
       * <pre>
       *&#47;&lt; 失败信息
       * </pre>
       */
      public java.lang.String getErrorMsg() {
        java.lang.Object ref = errorMsg_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          errorMsg_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string error_msg = 2;</code>
       *
       * <pre>
       *&#47;&lt; 失败信息
       * </pre>
       */
      public com.google.protobuf.ByteString
          getErrorMsgBytes() {
        java.lang.Object ref = errorMsg_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          errorMsg_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string error_msg = 2;</code>
       *
       * <pre>
       *&#47;&lt; 失败信息
       * </pre>
       */
      public Builder setErrorMsg(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        errorMsg_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string error_msg = 2;</code>
       *
       * <pre>
       *&#47;&lt; 失败信息
       * </pre>
       */
      public Builder clearErrorMsg() {
        bitField0_ = (bitField0_ & ~0x00000002);
        errorMsg_ = getDefaultInstance().getErrorMsg();
        onChanged();
        return this;
      }
      /**
       * <code>optional string error_msg = 2;</code>
       *
       * <pre>
       *&#47;&lt; 失败信息
       * </pre>
       */
      public Builder setErrorMsgBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        errorMsg_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:msg.result.console.DeleteFile_)
    }

    static {
      defaultInstance = new DeleteFile_(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:msg.result.console.DeleteFile_)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_msg_result_console_DeleteFile__descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_msg_result_console_DeleteFile__fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026Res_C_DeleteFile.proto\022\022msg.result.con" +
      "sole\"1\n\013DeleteFile_\022\017\n\007success\030\001 \002(\010\022\021\n\t" +
      "error_msg\030\002 \001(\tB1\n#com.lxd.protobuf.msg." +
      "result.consoleB\nDeleteFile"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_msg_result_console_DeleteFile__descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_msg_result_console_DeleteFile__fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_msg_result_console_DeleteFile__descriptor,
              new java.lang.String[] { "Success", "ErrorMsg", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
