// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Result.proto

package com.lxd.protobuf.msg.result;

public final class Result {
  private Result() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface Result_OrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // optional .msg.result.user.User_ user = 1;
    /**
     * <code>optional .msg.result.user.User_ user = 1;</code>
     */
    boolean hasUser();
    /**
     * <code>optional .msg.result.user.User_ user = 1;</code>
     */
    com.lxd.protobuf.msg.result.user.User.User_ getUser();
    /**
     * <code>optional .msg.result.user.User_ user = 1;</code>
     */
    com.lxd.protobuf.msg.result.user.User.User_OrBuilder getUserOrBuilder();

    // optional .msg.result.console.Console_ console = 2;
    /**
     * <code>optional .msg.result.console.Console_ console = 2;</code>
     */
    boolean hasConsole();
    /**
     * <code>optional .msg.result.console.Console_ console = 2;</code>
     */
    com.lxd.protobuf.msg.result.console.Console.Console_ getConsole();
    /**
     * <code>optional .msg.result.console.Console_ console = 2;</code>
     */
    com.lxd.protobuf.msg.result.console.Console.Console_OrBuilder getConsoleOrBuilder();
  }
  /**
   * Protobuf type {@code msg.result.Result_}
   */
  public static final class Result_ extends
      com.google.protobuf.GeneratedMessage
      implements Result_OrBuilder {
    // Use Result_.newBuilder() to construct.
    private Result_(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private Result_(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final Result_ defaultInstance;
    public static Result_ getDefaultInstance() {
      return defaultInstance;
    }

    public Result_ getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private Result_(
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
              com.lxd.protobuf.msg.result.user.User.User_.Builder subBuilder = null;
              if (((bitField0_ & 0x00000001) == 0x00000001)) {
                subBuilder = user_.toBuilder();
              }
              user_ = input.readMessage(com.lxd.protobuf.msg.result.user.User.User_.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(user_);
                user_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000001;
              break;
            }
            case 18: {
              com.lxd.protobuf.msg.result.console.Console.Console_.Builder subBuilder = null;
              if (((bitField0_ & 0x00000002) == 0x00000002)) {
                subBuilder = console_.toBuilder();
              }
              console_ = input.readMessage(com.lxd.protobuf.msg.result.console.Console.Console_.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(console_);
                console_ = subBuilder.buildPartial();
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
      return com.lxd.protobuf.msg.result.Result.internal_static_msg_result_Result__descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.lxd.protobuf.msg.result.Result.internal_static_msg_result_Result__fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.lxd.protobuf.msg.result.Result.Result_.class, com.lxd.protobuf.msg.result.Result.Result_.Builder.class);
    }

    public static com.google.protobuf.Parser<Result_> PARSER =
        new com.google.protobuf.AbstractParser<Result_>() {
      public Result_ parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Result_(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<Result_> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // optional .msg.result.user.User_ user = 1;
    public static final int USER_FIELD_NUMBER = 1;
    private com.lxd.protobuf.msg.result.user.User.User_ user_;
    /**
     * <code>optional .msg.result.user.User_ user = 1;</code>
     */
    public boolean hasUser() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional .msg.result.user.User_ user = 1;</code>
     */
    public com.lxd.protobuf.msg.result.user.User.User_ getUser() {
      return user_;
    }
    /**
     * <code>optional .msg.result.user.User_ user = 1;</code>
     */
    public com.lxd.protobuf.msg.result.user.User.User_OrBuilder getUserOrBuilder() {
      return user_;
    }

    // optional .msg.result.console.Console_ console = 2;
    public static final int CONSOLE_FIELD_NUMBER = 2;
    private com.lxd.protobuf.msg.result.console.Console.Console_ console_;
    /**
     * <code>optional .msg.result.console.Console_ console = 2;</code>
     */
    public boolean hasConsole() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional .msg.result.console.Console_ console = 2;</code>
     */
    public com.lxd.protobuf.msg.result.console.Console.Console_ getConsole() {
      return console_;
    }
    /**
     * <code>optional .msg.result.console.Console_ console = 2;</code>
     */
    public com.lxd.protobuf.msg.result.console.Console.Console_OrBuilder getConsoleOrBuilder() {
      return console_;
    }

    private void initFields() {
      user_ = com.lxd.protobuf.msg.result.user.User.User_.getDefaultInstance();
      console_ = com.lxd.protobuf.msg.result.console.Console.Console_.getDefaultInstance();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (hasUser()) {
        if (!getUser().isInitialized()) {
          memoizedIsInitialized = 0;
          return false;
        }
      }
      if (hasConsole()) {
        if (!getConsole().isInitialized()) {
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
        output.writeMessage(1, user_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeMessage(2, console_);
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
          .computeMessageSize(1, user_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, console_);
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

    public static com.lxd.protobuf.msg.result.Result.Result_ parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lxd.protobuf.msg.result.Result.Result_ parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.result.Result.Result_ parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lxd.protobuf.msg.result.Result.Result_ parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.result.Result.Result_ parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.lxd.protobuf.msg.result.Result.Result_ parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.result.Result.Result_ parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.lxd.protobuf.msg.result.Result.Result_ parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.lxd.protobuf.msg.result.Result.Result_ parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.lxd.protobuf.msg.result.Result.Result_ parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.lxd.protobuf.msg.result.Result.Result_ prototype) {
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
     * Protobuf type {@code msg.result.Result_}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.lxd.protobuf.msg.result.Result.Result_OrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.lxd.protobuf.msg.result.Result.internal_static_msg_result_Result__descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.lxd.protobuf.msg.result.Result.internal_static_msg_result_Result__fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.lxd.protobuf.msg.result.Result.Result_.class, com.lxd.protobuf.msg.result.Result.Result_.Builder.class);
      }

      // Construct using com.lxd.protobuf.msg.result.Result.Result_.newBuilder()
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
          getUserFieldBuilder();
          getConsoleFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (userBuilder_ == null) {
          user_ = com.lxd.protobuf.msg.result.user.User.User_.getDefaultInstance();
        } else {
          userBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        if (consoleBuilder_ == null) {
          console_ = com.lxd.protobuf.msg.result.console.Console.Console_.getDefaultInstance();
        } else {
          consoleBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.lxd.protobuf.msg.result.Result.internal_static_msg_result_Result__descriptor;
      }

      public com.lxd.protobuf.msg.result.Result.Result_ getDefaultInstanceForType() {
        return com.lxd.protobuf.msg.result.Result.Result_.getDefaultInstance();
      }

      public com.lxd.protobuf.msg.result.Result.Result_ build() {
        com.lxd.protobuf.msg.result.Result.Result_ result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.lxd.protobuf.msg.result.Result.Result_ buildPartial() {
        com.lxd.protobuf.msg.result.Result.Result_ result = new com.lxd.protobuf.msg.result.Result.Result_(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        if (userBuilder_ == null) {
          result.user_ = user_;
        } else {
          result.user_ = userBuilder_.build();
        }
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        if (consoleBuilder_ == null) {
          result.console_ = console_;
        } else {
          result.console_ = consoleBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.lxd.protobuf.msg.result.Result.Result_) {
          return mergeFrom((com.lxd.protobuf.msg.result.Result.Result_)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.lxd.protobuf.msg.result.Result.Result_ other) {
        if (other == com.lxd.protobuf.msg.result.Result.Result_.getDefaultInstance()) return this;
        if (other.hasUser()) {
          mergeUser(other.getUser());
        }
        if (other.hasConsole()) {
          mergeConsole(other.getConsole());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (hasUser()) {
          if (!getUser().isInitialized()) {
            
            return false;
          }
        }
        if (hasConsole()) {
          if (!getConsole().isInitialized()) {
            
            return false;
          }
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.lxd.protobuf.msg.result.Result.Result_ parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.lxd.protobuf.msg.result.Result.Result_) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // optional .msg.result.user.User_ user = 1;
      private com.lxd.protobuf.msg.result.user.User.User_ user_ = com.lxd.protobuf.msg.result.user.User.User_.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.lxd.protobuf.msg.result.user.User.User_, com.lxd.protobuf.msg.result.user.User.User_.Builder, com.lxd.protobuf.msg.result.user.User.User_OrBuilder> userBuilder_;
      /**
       * <code>optional .msg.result.user.User_ user = 1;</code>
       */
      public boolean hasUser() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional .msg.result.user.User_ user = 1;</code>
       */
      public com.lxd.protobuf.msg.result.user.User.User_ getUser() {
        if (userBuilder_ == null) {
          return user_;
        } else {
          return userBuilder_.getMessage();
        }
      }
      /**
       * <code>optional .msg.result.user.User_ user = 1;</code>
       */
      public Builder setUser(com.lxd.protobuf.msg.result.user.User.User_ value) {
        if (userBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          user_ = value;
          onChanged();
        } else {
          userBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>optional .msg.result.user.User_ user = 1;</code>
       */
      public Builder setUser(
          com.lxd.protobuf.msg.result.user.User.User_.Builder builderForValue) {
        if (userBuilder_ == null) {
          user_ = builderForValue.build();
          onChanged();
        } else {
          userBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>optional .msg.result.user.User_ user = 1;</code>
       */
      public Builder mergeUser(com.lxd.protobuf.msg.result.user.User.User_ value) {
        if (userBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001) &&
              user_ != com.lxd.protobuf.msg.result.user.User.User_.getDefaultInstance()) {
            user_ =
              com.lxd.protobuf.msg.result.user.User.User_.newBuilder(user_).mergeFrom(value).buildPartial();
          } else {
            user_ = value;
          }
          onChanged();
        } else {
          userBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>optional .msg.result.user.User_ user = 1;</code>
       */
      public Builder clearUser() {
        if (userBuilder_ == null) {
          user_ = com.lxd.protobuf.msg.result.user.User.User_.getDefaultInstance();
          onChanged();
        } else {
          userBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      /**
       * <code>optional .msg.result.user.User_ user = 1;</code>
       */
      public com.lxd.protobuf.msg.result.user.User.User_.Builder getUserBuilder() {
        bitField0_ |= 0x00000001;
        onChanged();
        return getUserFieldBuilder().getBuilder();
      }
      /**
       * <code>optional .msg.result.user.User_ user = 1;</code>
       */
      public com.lxd.protobuf.msg.result.user.User.User_OrBuilder getUserOrBuilder() {
        if (userBuilder_ != null) {
          return userBuilder_.getMessageOrBuilder();
        } else {
          return user_;
        }
      }
      /**
       * <code>optional .msg.result.user.User_ user = 1;</code>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.lxd.protobuf.msg.result.user.User.User_, com.lxd.protobuf.msg.result.user.User.User_.Builder, com.lxd.protobuf.msg.result.user.User.User_OrBuilder> 
          getUserFieldBuilder() {
        if (userBuilder_ == null) {
          userBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.lxd.protobuf.msg.result.user.User.User_, com.lxd.protobuf.msg.result.user.User.User_.Builder, com.lxd.protobuf.msg.result.user.User.User_OrBuilder>(
                  user_,
                  getParentForChildren(),
                  isClean());
          user_ = null;
        }
        return userBuilder_;
      }

      // optional .msg.result.console.Console_ console = 2;
      private com.lxd.protobuf.msg.result.console.Console.Console_ console_ = com.lxd.protobuf.msg.result.console.Console.Console_.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.lxd.protobuf.msg.result.console.Console.Console_, com.lxd.protobuf.msg.result.console.Console.Console_.Builder, com.lxd.protobuf.msg.result.console.Console.Console_OrBuilder> consoleBuilder_;
      /**
       * <code>optional .msg.result.console.Console_ console = 2;</code>
       */
      public boolean hasConsole() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional .msg.result.console.Console_ console = 2;</code>
       */
      public com.lxd.protobuf.msg.result.console.Console.Console_ getConsole() {
        if (consoleBuilder_ == null) {
          return console_;
        } else {
          return consoleBuilder_.getMessage();
        }
      }
      /**
       * <code>optional .msg.result.console.Console_ console = 2;</code>
       */
      public Builder setConsole(com.lxd.protobuf.msg.result.console.Console.Console_ value) {
        if (consoleBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          console_ = value;
          onChanged();
        } else {
          consoleBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>optional .msg.result.console.Console_ console = 2;</code>
       */
      public Builder setConsole(
          com.lxd.protobuf.msg.result.console.Console.Console_.Builder builderForValue) {
        if (consoleBuilder_ == null) {
          console_ = builderForValue.build();
          onChanged();
        } else {
          consoleBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>optional .msg.result.console.Console_ console = 2;</code>
       */
      public Builder mergeConsole(com.lxd.protobuf.msg.result.console.Console.Console_ value) {
        if (consoleBuilder_ == null) {
          if (((bitField0_ & 0x00000002) == 0x00000002) &&
              console_ != com.lxd.protobuf.msg.result.console.Console.Console_.getDefaultInstance()) {
            console_ =
              com.lxd.protobuf.msg.result.console.Console.Console_.newBuilder(console_).mergeFrom(value).buildPartial();
          } else {
            console_ = value;
          }
          onChanged();
        } else {
          consoleBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>optional .msg.result.console.Console_ console = 2;</code>
       */
      public Builder clearConsole() {
        if (consoleBuilder_ == null) {
          console_ = com.lxd.protobuf.msg.result.console.Console.Console_.getDefaultInstance();
          onChanged();
        } else {
          consoleBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }
      /**
       * <code>optional .msg.result.console.Console_ console = 2;</code>
       */
      public com.lxd.protobuf.msg.result.console.Console.Console_.Builder getConsoleBuilder() {
        bitField0_ |= 0x00000002;
        onChanged();
        return getConsoleFieldBuilder().getBuilder();
      }
      /**
       * <code>optional .msg.result.console.Console_ console = 2;</code>
       */
      public com.lxd.protobuf.msg.result.console.Console.Console_OrBuilder getConsoleOrBuilder() {
        if (consoleBuilder_ != null) {
          return consoleBuilder_.getMessageOrBuilder();
        } else {
          return console_;
        }
      }
      /**
       * <code>optional .msg.result.console.Console_ console = 2;</code>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.lxd.protobuf.msg.result.console.Console.Console_, com.lxd.protobuf.msg.result.console.Console.Console_.Builder, com.lxd.protobuf.msg.result.console.Console.Console_OrBuilder> 
          getConsoleFieldBuilder() {
        if (consoleBuilder_ == null) {
          consoleBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.lxd.protobuf.msg.result.console.Console.Console_, com.lxd.protobuf.msg.result.console.Console.Console_.Builder, com.lxd.protobuf.msg.result.console.Console.Console_OrBuilder>(
                  console_,
                  getParentForChildren(),
                  isClean());
          console_ = null;
        }
        return consoleBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:msg.result.Result_)
    }

    static {
      defaultInstance = new Result_(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:msg.result.Result_)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_msg_result_Result__descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_msg_result_Result__fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014Result.proto\022\nmsg.result\032\016Res_User.pro" +
      "to\032\021Res_Console.proto\"^\n\007Result_\022$\n\004user" +
      "\030\001 \001(\0132\026.msg.result.user.User_\022-\n\007consol" +
      "e\030\002 \001(\0132\034.msg.result.console.Console_B%\n" +
      "\033com.lxd.protobuf.msg.resultB\006Result"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_msg_result_Result__descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_msg_result_Result__fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_msg_result_Result__descriptor,
              new java.lang.String[] { "User", "Console", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.lxd.protobuf.msg.result.user.User.getDescriptor(),
          com.lxd.protobuf.msg.result.console.Console.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
