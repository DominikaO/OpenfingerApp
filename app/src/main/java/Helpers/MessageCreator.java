package Helpers;

import com.google.android.material.textfield.TextInputEditText;
import com.google.protobuf.ByteString;

import OpenFinger.ExtractionRequestOuterClass;
import OpenFinger.FingerprintOuterClass;
import OpenFinger.PreprocessingRequestOuterClass;
import OpenFinger.RegistrationRequestOuterClass;
import OpenFinger.VerificationRequestOlejarnikovaOuterClass;
import OpenFinger.WrapperOuterClass;
import handlers.ImageHandler;
import handlers.RegistrationRequestHandler;
import handlers.VerificationHandler;

public class MessageCreator {

    public static FingerprintOuterClass.Fingerprint
    createFingerprint(ImageHandler img) {

        byte[] fingerptintData = BitmapHelper.BitmapToBytes(img.getPic());

        FingerprintOuterClass.Fingerprint fingerprint =
                FingerprintOuterClass.Fingerprint.newBuilder()
                        .setHeight(img.getPic().getHeight())
                        .setWidth(img.getPic().getWidth())
                        .setColor(FingerprintOuterClass.Fingerprint.Colorspace.GRAYSCALE)
                        .setResolution(500) //-> vzdy 500
                        .setData(ByteString.copyFrom(fingerptintData))
                        .build();

        return  fingerprint;
    }

    public static WrapperOuterClass.Wrapper
    createRegistrationRequest(TextInputEditText ti_name, ImageHandler img, RegistrationRequestHandler registrationRequestHandler) {

        String name = ti_name.getText().toString();

        FingerprintOuterClass.Fingerprint fingerprint = createFingerprint(img);


        RegistrationRequestOuterClass.RegistrationRequest message =
                RegistrationRequestOuterClass.RegistrationRequest.newBuilder()
                        .setRequestId(registrationRequestHandler.getId()).
                        setFingerprint(fingerprint).
                        setLogin(name).build();

        WrapperOuterClass.Wrapper wrapper = WrapperOuterClass.Wrapper.newBuilder().
                setHeader(WrapperOuterClass.Wrapper.Header.REGISTRATION_REQUEST).
                setRegisterRequest(message)
                .build();

        registrationRequestHandler.addRequest(message);

        return wrapper;
    }

    public static WrapperOuterClass.Wrapper
    createVerification(TextInputEditText ti_login, ImageHandler img, VerificationHandler verificationHandler) {

        String name = ti_login.getText().toString();
        FingerprintOuterClass.Fingerprint fingerprint = createFingerprint(img);


        VerificationRequestOlejarnikovaOuterClass.VerificationRequestOlejarnikova verification =
                VerificationRequestOlejarnikovaOuterClass.VerificationRequestOlejarnikova.newBuilder()
                        .setLogin(name)
                        .setFingerprint(fingerprint)
                        .setRequestId(verificationHandler.getId())
                        .build();

        WrapperOuterClass.Wrapper wrapper = WrapperOuterClass.Wrapper.newBuilder().
                setHeader(WrapperOuterClass.Wrapper.Header.VERIFICATION_REQUEST_OLEJARNIKOVA).
                setVerifyRequestOlejarnikova(verification)
                .build();

        verificationHandler.addRequests(verification);

        return wrapper;
    }

    public static WrapperOuterClass.Wrapper
    createPreprocessRequest(ImageHandler img, TextInputEditText block, TextInputEditText lambda, TextInputEditText sigma) {


        FingerprintOuterClass.Fingerprint fingerprint = createFingerprint(img);

        String block_str = block.getText().toString();
        String lambda_str = lambda.getText().toString();
        String sigma_str = sigma.getText().toString();


        PreprocessingRequestOuterClass.PreprocessingParams params =
                PreprocessingRequestOuterClass.PreprocessingParams.newBuilder()
                        .setBlockSize(Integer.parseInt(block_str))
                        .setGaborLambda(Double.parseDouble(lambda_str))
                        .setGaborSigma(Double.parseDouble(sigma_str))
                        .build();
        PreprocessingRequestOuterClass.PreprocessingRequest message =
                PreprocessingRequestOuterClass.PreprocessingRequest.newBuilder()
                        .setFingerprint(fingerprint)
                        .setParams(params)
                        .build();

        WrapperOuterClass.Wrapper wrapper = WrapperOuterClass.Wrapper.newBuilder().
                setHeader(WrapperOuterClass.Wrapper.Header.PREPROCESSING_REQUEST).
                setPreprocRequest(message)
                .build();


        return wrapper;
    }

    public static WrapperOuterClass.Wrapper
    createExtractionRequest(ImageHandler img) {
        FingerprintOuterClass.Fingerprint fingerprint = createFingerprint(img);

        ExtractionRequestOuterClass.ExtractionRequest message = ExtractionRequestOuterClass.ExtractionRequest.newBuilder()
                .setFingerprint(fingerprint)
                .build();

        WrapperOuterClass.Wrapper wrapper = WrapperOuterClass.Wrapper.newBuilder().
                setHeader(WrapperOuterClass.Wrapper.Header.EXTRACTION_REQUEST).
                setExtractRequest(message)
                .build();

        return wrapper;

    }
}
