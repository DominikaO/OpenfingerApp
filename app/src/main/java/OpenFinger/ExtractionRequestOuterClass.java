// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ExtractionRequest.proto

package OpenFinger;

public final class ExtractionRequestOuterClass {
  private ExtractionRequestOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ExtractionRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:OpenFinger.ExtractionRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * fingerprint
     * </pre>
     *
     * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
     * @return Whether the fingerprint field is set.
     */
    boolean hasFingerprint();
    /**
     * <pre>
     * fingerprint
     * </pre>
     *
     * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
     * @return The fingerprint.
     */
    OpenFinger.FingerprintOuterClass.Fingerprint getFingerprint();
    /**
     * <pre>
     * fingerprint
     * </pre>
     *
     * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
     */
    OpenFinger.FingerprintOuterClass.FingerprintOrBuilder getFingerprintOrBuilder();
  }
  /**
   * Protobuf type {@code OpenFinger.ExtractionRequest}
   */
  public static final class ExtractionRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:OpenFinger.ExtractionRequest)
      ExtractionRequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ExtractionRequest.newBuilder() to construct.
    private ExtractionRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ExtractionRequest() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new ExtractionRequest();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ExtractionRequest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
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
            case 10: {
              OpenFinger.FingerprintOuterClass.Fingerprint.Builder subBuilder = null;
              if (fingerprint_ != null) {
                subBuilder = fingerprint_.toBuilder();
              }
              fingerprint_ = input.readMessage(OpenFinger.FingerprintOuterClass.Fingerprint.parser(), extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(fingerprint_);
                fingerprint_ = subBuilder.buildPartial();
              }

              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return OpenFinger.ExtractionRequestOuterClass.internal_static_OpenFinger_ExtractionRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return OpenFinger.ExtractionRequestOuterClass.internal_static_OpenFinger_ExtractionRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              OpenFinger.ExtractionRequestOuterClass.ExtractionRequest.class, OpenFinger.ExtractionRequestOuterClass.ExtractionRequest.Builder.class);
    }

    public static final int FINGERPRINT_FIELD_NUMBER = 1;
    private OpenFinger.FingerprintOuterClass.Fingerprint fingerprint_;
    /**
     * <pre>
     * fingerprint
     * </pre>
     *
     * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
     * @return Whether the fingerprint field is set.
     */
    @java.lang.Override
    public boolean hasFingerprint() {
      return fingerprint_ != null;
    }
    /**
     * <pre>
     * fingerprint
     * </pre>
     *
     * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
     * @return The fingerprint.
     */
    @java.lang.Override
    public OpenFinger.FingerprintOuterClass.Fingerprint getFingerprint() {
      return fingerprint_ == null ? OpenFinger.FingerprintOuterClass.Fingerprint.getDefaultInstance() : fingerprint_;
    }
    /**
     * <pre>
     * fingerprint
     * </pre>
     *
     * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
     */
    @java.lang.Override
    public OpenFinger.FingerprintOuterClass.FingerprintOrBuilder getFingerprintOrBuilder() {
      return getFingerprint();
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (fingerprint_ != null) {
        output.writeMessage(1, getFingerprint());
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (fingerprint_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, getFingerprint());
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof OpenFinger.ExtractionRequestOuterClass.ExtractionRequest)) {
        return super.equals(obj);
      }
      OpenFinger.ExtractionRequestOuterClass.ExtractionRequest other = (OpenFinger.ExtractionRequestOuterClass.ExtractionRequest) obj;

      if (hasFingerprint() != other.hasFingerprint()) return false;
      if (hasFingerprint()) {
        if (!getFingerprint()
            .equals(other.getFingerprint())) return false;
      }
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasFingerprint()) {
        hash = (37 * hash) + FINGERPRINT_FIELD_NUMBER;
        hash = (53 * hash) + getFingerprint().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static OpenFinger.ExtractionRequestOuterClass.ExtractionRequest parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static OpenFinger.ExtractionRequestOuterClass.ExtractionRequest parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static OpenFinger.ExtractionRequestOuterClass.ExtractionRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static OpenFinger.ExtractionRequestOuterClass.ExtractionRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static OpenFinger.ExtractionRequestOuterClass.ExtractionRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static OpenFinger.ExtractionRequestOuterClass.ExtractionRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static OpenFinger.ExtractionRequestOuterClass.ExtractionRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static OpenFinger.ExtractionRequestOuterClass.ExtractionRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static OpenFinger.ExtractionRequestOuterClass.ExtractionRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static OpenFinger.ExtractionRequestOuterClass.ExtractionRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static OpenFinger.ExtractionRequestOuterClass.ExtractionRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static OpenFinger.ExtractionRequestOuterClass.ExtractionRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(OpenFinger.ExtractionRequestOuterClass.ExtractionRequest prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code OpenFinger.ExtractionRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:OpenFinger.ExtractionRequest)
        OpenFinger.ExtractionRequestOuterClass.ExtractionRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return OpenFinger.ExtractionRequestOuterClass.internal_static_OpenFinger_ExtractionRequest_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return OpenFinger.ExtractionRequestOuterClass.internal_static_OpenFinger_ExtractionRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                OpenFinger.ExtractionRequestOuterClass.ExtractionRequest.class, OpenFinger.ExtractionRequestOuterClass.ExtractionRequest.Builder.class);
      }

      // Construct using OpenFinger.ExtractionRequestOuterClass.ExtractionRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        if (fingerprintBuilder_ == null) {
          fingerprint_ = null;
        } else {
          fingerprint_ = null;
          fingerprintBuilder_ = null;
        }
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return OpenFinger.ExtractionRequestOuterClass.internal_static_OpenFinger_ExtractionRequest_descriptor;
      }

      @java.lang.Override
      public OpenFinger.ExtractionRequestOuterClass.ExtractionRequest getDefaultInstanceForType() {
        return OpenFinger.ExtractionRequestOuterClass.ExtractionRequest.getDefaultInstance();
      }

      @java.lang.Override
      public OpenFinger.ExtractionRequestOuterClass.ExtractionRequest build() {
        OpenFinger.ExtractionRequestOuterClass.ExtractionRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public OpenFinger.ExtractionRequestOuterClass.ExtractionRequest buildPartial() {
        OpenFinger.ExtractionRequestOuterClass.ExtractionRequest result = new OpenFinger.ExtractionRequestOuterClass.ExtractionRequest(this);
        if (fingerprintBuilder_ == null) {
          result.fingerprint_ = fingerprint_;
        } else {
          result.fingerprint_ = fingerprintBuilder_.build();
        }
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof OpenFinger.ExtractionRequestOuterClass.ExtractionRequest) {
          return mergeFrom((OpenFinger.ExtractionRequestOuterClass.ExtractionRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(OpenFinger.ExtractionRequestOuterClass.ExtractionRequest other) {
        if (other == OpenFinger.ExtractionRequestOuterClass.ExtractionRequest.getDefaultInstance()) return this;
        if (other.hasFingerprint()) {
          mergeFingerprint(other.getFingerprint());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        OpenFinger.ExtractionRequestOuterClass.ExtractionRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (OpenFinger.ExtractionRequestOuterClass.ExtractionRequest) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private OpenFinger.FingerprintOuterClass.Fingerprint fingerprint_;
      private com.google.protobuf.SingleFieldBuilderV3<
          OpenFinger.FingerprintOuterClass.Fingerprint, OpenFinger.FingerprintOuterClass.Fingerprint.Builder, OpenFinger.FingerprintOuterClass.FingerprintOrBuilder> fingerprintBuilder_;
      /**
       * <pre>
       * fingerprint
       * </pre>
       *
       * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
       * @return Whether the fingerprint field is set.
       */
      public boolean hasFingerprint() {
        return fingerprintBuilder_ != null || fingerprint_ != null;
      }
      /**
       * <pre>
       * fingerprint
       * </pre>
       *
       * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
       * @return The fingerprint.
       */
      public OpenFinger.FingerprintOuterClass.Fingerprint getFingerprint() {
        if (fingerprintBuilder_ == null) {
          return fingerprint_ == null ? OpenFinger.FingerprintOuterClass.Fingerprint.getDefaultInstance() : fingerprint_;
        } else {
          return fingerprintBuilder_.getMessage();
        }
      }
      /**
       * <pre>
       * fingerprint
       * </pre>
       *
       * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
       */
      public Builder setFingerprint(OpenFinger.FingerprintOuterClass.Fingerprint value) {
        if (fingerprintBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          fingerprint_ = value;
          onChanged();
        } else {
          fingerprintBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <pre>
       * fingerprint
       * </pre>
       *
       * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
       */
      public Builder setFingerprint(
          OpenFinger.FingerprintOuterClass.Fingerprint.Builder builderForValue) {
        if (fingerprintBuilder_ == null) {
          fingerprint_ = builderForValue.build();
          onChanged();
        } else {
          fingerprintBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <pre>
       * fingerprint
       * </pre>
       *
       * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
       */
      public Builder mergeFingerprint(OpenFinger.FingerprintOuterClass.Fingerprint value) {
        if (fingerprintBuilder_ == null) {
          if (fingerprint_ != null) {
            fingerprint_ =
              OpenFinger.FingerprintOuterClass.Fingerprint.newBuilder(fingerprint_).mergeFrom(value).buildPartial();
          } else {
            fingerprint_ = value;
          }
          onChanged();
        } else {
          fingerprintBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <pre>
       * fingerprint
       * </pre>
       *
       * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
       */
      public Builder clearFingerprint() {
        if (fingerprintBuilder_ == null) {
          fingerprint_ = null;
          onChanged();
        } else {
          fingerprint_ = null;
          fingerprintBuilder_ = null;
        }

        return this;
      }
      /**
       * <pre>
       * fingerprint
       * </pre>
       *
       * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
       */
      public OpenFinger.FingerprintOuterClass.Fingerprint.Builder getFingerprintBuilder() {
        
        onChanged();
        return getFingerprintFieldBuilder().getBuilder();
      }
      /**
       * <pre>
       * fingerprint
       * </pre>
       *
       * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
       */
      public OpenFinger.FingerprintOuterClass.FingerprintOrBuilder getFingerprintOrBuilder() {
        if (fingerprintBuilder_ != null) {
          return fingerprintBuilder_.getMessageOrBuilder();
        } else {
          return fingerprint_ == null ?
              OpenFinger.FingerprintOuterClass.Fingerprint.getDefaultInstance() : fingerprint_;
        }
      }
      /**
       * <pre>
       * fingerprint
       * </pre>
       *
       * <code>.OpenFinger.Fingerprint fingerprint = 1;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          OpenFinger.FingerprintOuterClass.Fingerprint, OpenFinger.FingerprintOuterClass.Fingerprint.Builder, OpenFinger.FingerprintOuterClass.FingerprintOrBuilder> 
          getFingerprintFieldBuilder() {
        if (fingerprintBuilder_ == null) {
          fingerprintBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              OpenFinger.FingerprintOuterClass.Fingerprint, OpenFinger.FingerprintOuterClass.Fingerprint.Builder, OpenFinger.FingerprintOuterClass.FingerprintOrBuilder>(
                  getFingerprint(),
                  getParentForChildren(),
                  isClean());
          fingerprint_ = null;
        }
        return fingerprintBuilder_;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:OpenFinger.ExtractionRequest)
    }

    // @@protoc_insertion_point(class_scope:OpenFinger.ExtractionRequest)
    private static final OpenFinger.ExtractionRequestOuterClass.ExtractionRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new OpenFinger.ExtractionRequestOuterClass.ExtractionRequest();
    }

    public static OpenFinger.ExtractionRequestOuterClass.ExtractionRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ExtractionRequest>
        PARSER = new com.google.protobuf.AbstractParser<ExtractionRequest>() {
      @java.lang.Override
      public ExtractionRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ExtractionRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ExtractionRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ExtractionRequest> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public OpenFinger.ExtractionRequestOuterClass.ExtractionRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_OpenFinger_ExtractionRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_OpenFinger_ExtractionRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027ExtractionRequest.proto\022\nOpenFinger\032\021F" +
      "ingerprint.proto\"A\n\021ExtractionRequest\022,\n" +
      "\013fingerprint\030\001 \001(\0132\027.OpenFinger.Fingerpr" +
      "intb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          OpenFinger.FingerprintOuterClass.getDescriptor(),
        });
    internal_static_OpenFinger_ExtractionRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_OpenFinger_ExtractionRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_OpenFinger_ExtractionRequest_descriptor,
        new java.lang.String[] { "Fingerprint", });
    OpenFinger.FingerprintOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}