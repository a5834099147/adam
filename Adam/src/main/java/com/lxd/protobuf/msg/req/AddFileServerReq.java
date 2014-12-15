// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AddFileServerReq.proto

package com.lxd.protobuf.msg.req;

public final class AddFileServerReq {
  private AddFileServerReq() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface AddFileServerReq_OrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 id = 1;
    /**
     * <code>required int32 id = 1;</code>
     */
    boolean hasId();
    /**
     * <code>required int32 id = 1;</code>
     */
    int getId();
  }
  /**
   * Protobuf type {@code req.AddFileServerReq_}
   */
  public static final class AddFileServerReq_ extends
      com.google.protobuf.GeneratedMessage
      implements AddFileServerReq_OrBuilder {
    // Use AddFileServerReq_.newBuilder() to construct.
    private AddFileServerReq_(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private AddFileServerReq_(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final AddFileServerReq_ defaultInstance;
    public static AddFileServerReq_ getDefaultInstance() {
      return defaultInstance;
    }

    public AddFileServerReq_ getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private AddFileServerReq_(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      @SuppressWarnings("unused")
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
              id_ = input.readInt32();
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
      return com.lxd.protobuf.msg.req.AddFileServerReq.internal_static_req_AddFileServerReq__descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.lxd.protobuf.msg.req.AddFileServerReq.internal_static_req_AddFileServerReq__fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_.class, com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_.Builder.class);
    }

    public static com.google.protobuf.Parser<AddFileServerReq_> PARSER =
        new com.google.protobuf.AbstractParser<AddFileServerReq_>() {
      public AddFileServerReq_ parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new AddFileServerReq_(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<AddFileServerReq_> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 id = 1;
    public static final int ID_FIELD_NUMBER = 1;
    private int id_;
    /**
     * <code>required int32 id = 1;</code>
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 id = 1;</code>
     */
    public int getId() {
      return id_;
    }

    private void initFields() {
      id_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasId()) {
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
        output.writeInt32(1, id_);
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
          .computeInt32Size(1, id_);
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

    public static com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ prototype) {
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
     * Protobuf type {@code req.AddFileServerReq_}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_OrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.lxd.protobuf.msg.req.AddFileServerReq.internal_static_req_AddFileServerReq__descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.lxd.protobuf.msg.req.AddFileServerReq.internal_static_req_AddFileServerReq__fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_.class, com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_.Builder.class);
      }

      // Construct using com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_.newBuilder()
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
        id_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.lxd.protobuf.msg.req.AddFileServerReq.internal_static_req_AddFileServerReq__descriptor;
      }

      public com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ getDefaultInstanceForType() {
        return com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_.getDefaultInstance();
      }

      public com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ build() {
        com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ buildPartial() {
        com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ result = new com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.id_ = id_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_) {
          return mergeFrom((com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ other) {
        if (other == com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_.getDefaultInstance()) return this;
        if (other.hasId()) {
          setId(other.getId());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasId()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_ parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.lxd.protobuf.msg.req.AddFileServerReq.AddFileServerReq_) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 id = 1;
      private int id_ ;
      /**
       * <code>required int32 id = 1;</code>
       */
      public boolean hasId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 id = 1;</code>
       */
      public int getId() {
        return id_;
      }
      /**
       * <code>required int32 id = 1;</code>
       */
      public Builder setId(int value) {
        bitField0_ |= 0x00000001;
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 id = 1;</code>
       */
      public Builder clearId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        id_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:req.AddFileServerReq_)
    }

    static {
      defaultInstance = new AddFileServerReq_(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:req.AddFileServerReq_)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_req_AddFileServerReq__descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_req_AddFileServerReq__fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026AddFileServerReq.proto\022\003req\"\037\n\021AddFile" +
      "ServerReq_\022\n\n\002id\030\001 \002(\005B,\n\030com.lxd.protob" +
      "uf.msg.reqB\020AddFileServerReq"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_req_AddFileServerReq__descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_req_AddFileServerReq__fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_req_AddFileServerReq__descriptor,
              new java.lang.String[] { "Id", });
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