// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Request.proto

package com.lxd.protobuf.msg.request;

public final class Request {
  private Request() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface Request_OrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // optional .msg.request.console.Console_ console = 1;
    /**
     * <code>optional .msg.request.console.Console_ console = 1;</code>
     *
     * <pre>
     *&#47;&lt; 控制台
     * </pre>
     */
    boolean hasConsole();
    /**
     * <code>optional .msg.request.console.Console_ console = 1;</code>
     *
     * <pre>
     *&#47;&lt; 控制台
     * </pre>
     */
    com.lxd.protobuf.msg.request.console.Console.Console_ getConsole();
    /**
     * <code>optional .msg.request.console.Console_ console = 1;</code>
     *
     * <pre>
     *&#47;&lt; 控制台
     * </pre>
     */
    com.lxd.protobuf.msg.request.console.Console.Console_OrBuilder getConsoleOrBuilder();

    // optional .msg.request.user.User_ user = 2;
    /**
     * <code>optional .msg.request.user.User_ user = 2;</code>
     *
     * <pre>
     *&#47;&lt; 用户
     * </pre>
     */
    boolean hasUser();
    /**
     * <code>optional .msg.request.user.User_ user = 2;</code>
     *
     * <pre>
     *&#47;&lt; 用户
     * </pre>
     */
    com.lxd.protobuf.msg.request.user.User.User_ getUser();
    /**
     * <code>optional .msg.request.user.User_ user = 2;</code>
     *
     * <pre>
     *&#47;&lt; 用户
     * </pre>
     */
    com.lxd.protobuf.msg.request.user.User.User_OrBuilder getUserOrBuilder();
  }
  /**
   * Protobuf type {@code msg.request.Request_}
   */
  public static final class Request_ extends
      com.google.protobuf.GeneratedMessage
      implements Request_OrBuilder {
    // Use Request_.newBuilder() to construct.
    private Request_(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private Request_(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final Request_ defaultInstance;
    public static Request_ getDefaultInstance() {
      return defaultInstance;
    }

    public Request_ getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private Request_(
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
            case 10: {
              com.lxd.protobuf.msg.request.console.Console.Console_.Builder subBuilder = null;
              if (((bitField0_ & 0x00000001) == 0x00000001)) {
                subBuilder = console_.toBuilder();
              }
              console_ = input.readMessage(com.lxd.protobuf.msg.request.console.Console.Console_.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(console_);
                console_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000001;
              break;
            }
            case 18: {
              com.lxd.protobuf.msg.request.user.User.User_.Builder subBuilder = null;
              if (((bitField0_ & 0x00000002) == 0x00000002)) {
                subBuilder = user_.toBuilder();
              }
              user_ = input.readMessage(com.lxd.protobuf.msg.request.user.User.User_.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(user_);
                user_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000002;
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
      return com.lxd.protobuf.msg.request.Request.internal_static_msg_request_Request__descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.lxd.protobuf.msg.request.Request.internal_static_msg_request_Request__fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.lxd.protobuf.msg.request.Request.Request_.class, com.lxd.protobuf.msg.request.Request.Request_.Builder.class);
    }

    public static com.google.protobuf.Parser<Request_> PARSER =
        new com.google.protobuf.AbstractParser<Request_>() {
      public Request_ parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Request_(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<Request_> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // optional .msg.request.console.Console_ console = 1;
    public static final int CONSOLE_FIELD_NUMBER = 1;
    private com.lxd.protobuf.msg.request.console.Console.Console_ console_;
    /**
     * <code>optional .msg.request.console.Console_ console = 1;</code>
     *
     * <pre>
     *&#47;&lt; 控制台
     * </pre>
     */
    public boolean hasConsole() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional .msg.request.console.Console_ console = 1;</code>
     *
     * <pre>
     *&#47;&lt; 控制台
     * </pre>
     */
    public com.lxd.protobuf.msg.request.console.Console.Console_ getConsole() {
      return console_;
    }
    /**
     * <code>optional .msg.request.console.Console_ console = 1;</code>
     *
     * <pre>
     *&#47;&lt; 控制台
     * </pre>
     */
    public com.lxd.protobuf.msg.request.console.Console.Console_OrBuilder getConsoleOrBuilder() {
      return console_;
    }

    // optional .msg.request.user.User_ user = 2;
    public static final int USER_FIELD_NUMBER = 2;
    private com.lxd.protobuf.msg.request.user.User.User_ user_;
    /**
     * <code>optional .msg.request.user.User_ user = 2;</code>
     *
     * <pre>
     *&#47;&lt; 用户
     * </pre>
     */
    public boolean hasUser() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional .msg.request.user.User_ user = 2;</code>
     *
     * <pre>
     *&#47;&lt; 用户
     * </pre>
     */
    public com.lxd.protobuf.msg.request.user.User.User_ getUser() {
      return user_;
    }
    /**
     * <code>optional .msg.request.user.User_ user = 2;</code>
     *
     * <pre>
     *&#47;&lt; 用户
     * </pre>
     */
    public com.lxd.protobuf.msg.request.user.User.User_OrBuilder getUserOrBuilder() {
      return user_;
    }

    private void initFields() {
      console_ = com.lxd.protobuf.msg.request.console.Console.Console_.getDefaultInstance();
      user_ = com.lxd.protobuf.msg.request.user.User.User_.getDefaultInstance();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (hasConsole()) {
        if (!getConsole().isInitialized()) {
          memoizedIsInitialized = 0;
          return false;
        }
      }
      if (hasUser()) {
        if (!getUser().isInitialized()) {
          memoizedIsInitialized = 0;
          return false;
        }
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeMessage(1, console_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeMessage(2, user_);
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
          .computeMessageSize(1, console_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, user_);
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

    public static com.lxd.protobuf.msg.request.Request.Request_ parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lxd.protobuf.msg.request.Request.Request_ parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.request.Request.Request_ parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lxd.protobuf.msg.request.Request.Request_ parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.request.Request.Request_ parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.lxd.protobuf.msg.request.Request.Request_ parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.request.Request.Request_ parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.lxd.protobuf.msg.request.Request.Request_ parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.request.Request.Request_ parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.lxd.protobuf.msg.request.Request.Request_ parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.lxd.protobuf.msg.request.Request.Request_ prototype) {
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
     * Protobuf type {@code msg.request.Request_}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.lxd.protobuf.msg.request.Request.Request_OrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.lxd.protobuf.msg.request.Request.internal_static_msg_request_Request__descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.lxd.protobuf.msg.request.Request.internal_static_msg_request_Request__fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.lxd.protobuf.msg.request.Request.Request_.class, com.lxd.protobuf.msg.request.Request.Request_.Builder.class);
      }

      // Construct using com.lxd.protobuf.msg.request.Request.Request_.newBuilder()
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
          getConsoleFieldBuilder();
          getUserFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (consoleBuilder_ == null) {
          console_ = com.lxd.protobuf.msg.request.console.Console.Console_.getDefaultInstance();
        } else {
          consoleBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        if (userBuilder_ == null) {
          user_ = com.lxd.protobuf.msg.request.user.User.User_.getDefaultInstance();
        } else {
          userBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.lxd.protobuf.msg.request.Request.internal_static_msg_request_Request__descriptor;
      }

      public com.lxd.protobuf.msg.request.Request.Request_ getDefaultInstanceForType() {
        return com.lxd.protobuf.msg.request.Request.Request_.getDefaultInstance();
      }

      public com.lxd.protobuf.msg.request.Request.Request_ build() {
        com.lxd.protobuf.msg.request.Request.Request_ result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.lxd.protobuf.msg.request.Request.Request_ buildPartial() {
        com.lxd.protobuf.msg.request.Request.Request_ result = new com.lxd.protobuf.msg.request.Request.Request_(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        if (consoleBuilder_ == null) {
          result.console_ = console_;
        } else {
          result.console_ = consoleBuilder_.build();
        }
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        if (userBuilder_ == null) {
          result.user_ = user_;
        } else {
          result.user_ = userBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.lxd.protobuf.msg.request.Request.Request_) {
          return mergeFrom((com.lxd.protobuf.msg.request.Request.Request_)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.lxd.protobuf.msg.request.Request.Request_ other) {
        if (other == com.lxd.protobuf.msg.request.Request.Request_.getDefaultInstance()) return this;
        if (other.hasConsole()) {
          mergeConsole(other.getConsole());
        }
        if (other.hasUser()) {
          mergeUser(other.getUser());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (hasConsole()) {
          if (!getConsole().isInitialized()) {
            
            return false;
          }
        }
        if (hasUser()) {
          if (!getUser().isInitialized()) {
            
            return false;
          }
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.lxd.protobuf.msg.request.Request.Request_ parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.lxd.protobuf.msg.request.Request.Request_) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // optional .msg.request.console.Console_ console = 1;
      private com.lxd.protobuf.msg.request.console.Console.Console_ console_ = com.lxd.protobuf.msg.request.console.Console.Console_.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.lxd.protobuf.msg.request.console.Console.Console_, com.lxd.protobuf.msg.request.console.Console.Console_.Builder, com.lxd.protobuf.msg.request.console.Console.Console_OrBuilder> consoleBuilder_;
      /**
       * <code>optional .msg.request.console.Console_ console = 1;</code>
       *
       * <pre>
       *&#47;&lt; 控制台
       * </pre>
       */
      public boolean hasConsole() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional .msg.request.console.Console_ console = 1;</code>
       *
       * <pre>
       *&#47;&lt; 控制台
       * </pre>
       */
      public com.lxd.protobuf.msg.request.console.Console.Console_ getConsole() {
        if (consoleBuilder_ == null) {
          return console_;
        } else {
          return consoleBuilder_.getMessage();
        }
      }
      /**
       * <code>optional .msg.request.console.Console_ console = 1;</code>
       *
       * <pre>
       *&#47;&lt; 控制台
       * </pre>
       */
      public Builder setConsole(com.lxd.protobuf.msg.request.console.Console.Console_ value) {
        if (consoleBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          console_ = value;
          onChanged();
        } else {
          consoleBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>optional .msg.request.console.Console_ console = 1;</code>
       *
       * <pre>
       *&#47;&lt; 控制台
       * </pre>
       */
      public Builder setConsole(
          com.lxd.protobuf.msg.request.console.Console.Console_.Builder builderForValue) {
        if (consoleBuilder_ == null) {
          console_ = builderForValue.build();
          onChanged();
        } else {
          consoleBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>optional .msg.request.console.Console_ console = 1;</code>
       *
       * <pre>
       *&#47;&lt; 控制台
       * </pre>
       */
      public Builder mergeConsole(com.lxd.protobuf.msg.request.console.Console.Console_ value) {
        if (consoleBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001) &&
              console_ != com.lxd.protobuf.msg.request.console.Console.Console_.getDefaultInstance()) {
            console_ =
              com.lxd.protobuf.msg.request.console.Console.Console_.newBuilder(console_).mergeFrom(value).buildPartial();
          } else {
            console_ = value;
          }
          onChanged();
        } else {
          consoleBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>optional .msg.request.console.Console_ console = 1;</code>
       *
       * <pre>
       *&#47;&lt; 控制台
       * </pre>
       */
      public Builder clearConsole() {
        if (consoleBuilder_ == null) {
          console_ = com.lxd.protobuf.msg.request.console.Console.Console_.getDefaultInstance();
          onChanged();
        } else {
          consoleBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      /**
       * <code>optional .msg.request.console.Console_ console = 1;</code>
       *
       * <pre>
       *&#47;&lt; 控制台
       * </pre>
       */
      public com.lxd.protobuf.msg.request.console.Console.Console_.Builder getConsoleBuilder() {
        bitField0_ |= 0x00000001;
        onChanged();
        return getConsoleFieldBuilder().getBuilder();
      }
      /**
       * <code>optional .msg.request.console.Console_ console = 1;</code>
       *
       * <pre>
       *&#47;&lt; 控制台
       * </pre>
       */
      public com.lxd.protobuf.msg.request.console.Console.Console_OrBuilder getConsoleOrBuilder() {
        if (consoleBuilder_ != null) {
          return consoleBuilder_.getMessageOrBuilder();
        } else {
          return console_;
        }
      }
      /**
       * <code>optional .msg.request.console.Console_ console = 1;</code>
       *
       * <pre>
       *&#47;&lt; 控制台
       * </pre>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.lxd.protobuf.msg.request.console.Console.Console_, com.lxd.protobuf.msg.request.console.Console.Console_.Builder, com.lxd.protobuf.msg.request.console.Console.Console_OrBuilder> 
          getConsoleFieldBuilder() {
        if (consoleBuilder_ == null) {
          consoleBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.lxd.protobuf.msg.request.console.Console.Console_, com.lxd.protobuf.msg.request.console.Console.Console_.Builder, com.lxd.protobuf.msg.request.console.Console.Console_OrBuilder>(
                  console_,
                  getParentForChildren(),
                  isClean());
          console_ = null;
        }
        return consoleBuilder_;
      }

      // optional .msg.request.user.User_ user = 2;
      private com.lxd.protobuf.msg.request.user.User.User_ user_ = com.lxd.protobuf.msg.request.user.User.User_.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.lxd.protobuf.msg.request.user.User.User_, com.lxd.protobuf.msg.request.user.User.User_.Builder, com.lxd.protobuf.msg.request.user.User.User_OrBuilder> userBuilder_;
      /**
       * <code>optional .msg.request.user.User_ user = 2;</code>
       *
       * <pre>
       *&#47;&lt; 用户
       * </pre>
       */
      public boolean hasUser() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional .msg.request.user.User_ user = 2;</code>
       *
       * <pre>
       *&#47;&lt; 用户
       * </pre>
       */
      public com.lxd.protobuf.msg.request.user.User.User_ getUser() {
        if (userBuilder_ == null) {
          return user_;
        } else {
          return userBuilder_.getMessage();
        }
      }
      /**
       * <code>optional .msg.request.user.User_ user = 2;</code>
       *
       * <pre>
       *&#47;&lt; 用户
       * </pre>
       */
      public Builder setUser(com.lxd.protobuf.msg.request.user.User.User_ value) {
        if (userBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          user_ = value;
          onChanged();
        } else {
          userBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>optional .msg.request.user.User_ user = 2;</code>
       *
       * <pre>
       *&#47;&lt; 用户
       * </pre>
       */
      public Builder setUser(
          com.lxd.protobuf.msg.request.user.User.User_.Builder builderForValue) {
        if (userBuilder_ == null) {
          user_ = builderForValue.build();
          onChanged();
        } else {
          userBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>optional .msg.request.user.User_ user = 2;</code>
       *
       * <pre>
       *&#47;&lt; 用户
       * </pre>
       */
      public Builder mergeUser(com.lxd.protobuf.msg.request.user.User.User_ value) {
        if (userBuilder_ == null) {
          if (((bitField0_ & 0x00000002) == 0x00000002) &&
              user_ != com.lxd.protobuf.msg.request.user.User.User_.getDefaultInstance()) {
            user_ =
              com.lxd.protobuf.msg.request.user.User.User_.newBuilder(user_).mergeFrom(value).buildPartial();
          } else {
            user_ = value;
          }
          onChanged();
        } else {
          userBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>optional .msg.request.user.User_ user = 2;</code>
       *
       * <pre>
       *&#47;&lt; 用户
       * </pre>
       */
      public Builder clearUser() {
        if (userBuilder_ == null) {
          user_ = com.lxd.protobuf.msg.request.user.User.User_.getDefaultInstance();
          onChanged();
        } else {
          userBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }
      /**
       * <code>optional .msg.request.user.User_ user = 2;</code>
       *
       * <pre>
       *&#47;&lt; 用户
       * </pre>
       */
      public com.lxd.protobuf.msg.request.user.User.User_.Builder getUserBuilder() {
        bitField0_ |= 0x00000002;
        onChanged();
        return getUserFieldBuilder().getBuilder();
      }
      /**
       * <code>optional .msg.request.user.User_ user = 2;</code>
       *
       * <pre>
       *&#47;&lt; 用户
       * </pre>
       */
      public com.lxd.protobuf.msg.request.user.User.User_OrBuilder getUserOrBuilder() {
        if (userBuilder_ != null) {
          return userBuilder_.getMessageOrBuilder();
        } else {
          return user_;
        }
      }
      /**
       * <code>optional .msg.request.user.User_ user = 2;</code>
       *
       * <pre>
       *&#47;&lt; 用户
       * </pre>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.lxd.protobuf.msg.request.user.User.User_, com.lxd.protobuf.msg.request.user.User.User_.Builder, com.lxd.protobuf.msg.request.user.User.User_OrBuilder> 
          getUserFieldBuilder() {
        if (userBuilder_ == null) {
          userBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.lxd.protobuf.msg.request.user.User.User_, com.lxd.protobuf.msg.request.user.User.User_.Builder, com.lxd.protobuf.msg.request.user.User.User_OrBuilder>(
                  user_,
                  getParentForChildren(),
                  isClean());
          user_ = null;
        }
        return userBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:msg.request.Request_)
    }

    static {
      defaultInstance = new Request_(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:msg.request.Request_)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_msg_request_Request__descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_msg_request_Request__fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rRequest.proto\022\013msg.request\032\017R_Console." +
      "proto\032\014R_User.proto\"a\n\010Request_\022.\n\007conso" +
      "le\030\001 \001(\0132\035.msg.request.console.Console_\022" +
      "%\n\004user\030\002 \001(\0132\027.msg.request.user.User_B\'" +
      "\n\034com.lxd.protobuf.msg.requestB\007Request"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_msg_request_Request__descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_msg_request_Request__fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_msg_request_Request__descriptor,
              new java.lang.String[] { "Console", "User", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.lxd.protobuf.msg.request.console.Console.getDescriptor(),
          com.lxd.protobuf.msg.request.user.User.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
