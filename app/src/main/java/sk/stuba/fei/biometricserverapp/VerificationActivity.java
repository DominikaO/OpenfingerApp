package sk.stuba.fei.biometricserverapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import Helpers.MessageCreator;
import OpenFinger.WrapperOuterClass;
import handlers.ImageHandler;
import handlers.SocketHandler;
import handlers.VerificationHandler;

public class VerificationActivity extends Activity {
    ImageView imageView;
    ImageHandler imageHandler = new ImageHandler();
    ImageButton imageButton, b_back;
    TextInputEditText ti_login;
    Button b_send;
    VerificationHandler verificationHandler = new VerificationHandler(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        imageButton= findViewById(R.id.b_gallery2);
        ti_login = findViewById(R.id.tin_login_verify);
        b_send = findViewById(R.id.b_verify);
        imageView = findViewById(R.id.imageView_fingerprint1);
        b_back = findViewById(R.id.b_back_to_menu2);


        imageButton.setOnClickListener(new ImageButtonClick());
        b_send.setOnClickListener(new ButtonClick());
        b_back.setOnClickListener(new B_BackClick());
    }

    private void imageButtonClicked() {
        selectImage(this);
    }

    class ImageButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            imageButtonClicked();
        }
    }


    private void B_BackClicked() {

        Intent new_intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(new_intent);
        finish();
    }

    class B_BackClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            B_BackClicked();
        }
    }


    private void ButtonClicked() {
        new Thread(new Verification()).start();

    }

    class ButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ButtonClicked();
        }
    }

    public void showToast(final String toast)
    {

        runOnUiThread(() ->  Toast.makeText(this, toast, Toast.LENGTH_LONG).show());

    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose picture you want to upload");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item){

                if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            Uri chosenImageUri = data.getData();

            Bitmap mBitmap = null;
            try {
                mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), chosenImageUri);
                imageView.setImageBitmap(mBitmap);
                imageHandler.setPic(mBitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public class Verification implements Runnable {



        @Override
        public void run() {

            Socket clientSocket = SocketHandler.getSocket();


            try {
                OutputStream out = clientSocket.getOutputStream();
                DataInputStream is = new DataInputStream(clientSocket.getInputStream());
                WrapperOuterClass.Wrapper verification
                        = MessageCreator.createVerification(ti_login, imageHandler,verificationHandler);
                verification.writeTo(clientSocket.getOutputStream());


                byte[] buffer = new byte[1024];
                byte[] temp = null;

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int numbytesread = 0;
                while (true) {
                    numbytesread = is.read(buffer, 0, buffer.length );
                    //byte[] copy = new byte[numbytesread];
                    //stringBuilder.append(buffer);
                    byteArrayOutputStream.write(buffer,0,numbytesread);
                    //System.arraycopy(buffer, 0, copy, 0, numbytesread);
                    temp =byteArrayOutputStream.toByteArray();


                    //cakam na server
                    try {
                       WrapperOuterClass.Wrapper verificationResponse =
                               WrapperOuterClass.Wrapper.parseFrom(temp);
                            //Toast.makeText(getApplicationContext(),"Registration",Toast.LENGTH_LONG).show();
                        if (verificationResponse.getVerifyResponseOlejarnikova().getSuccess()) {
                            showToast("VERIFICATION SUCESSFUL, VERIFICATION SCORE: "
                                    + verificationResponse.getVerifyResponseOlejarnikova().getScore());
                            //verificationHandler.removeRequest(verification.getVerifyRequest());
                        } else {
                            showToast("VERIFICATION FAILED, VERIFICATION SCORE:" +
                                    verificationResponse.getVerifyResponseOlejarnikova().getScore());
                            //verificationHandler.removeRequest(verification.getVerifyRequest());
                        }    //registrationRequestHandler.removeRequest(registration2.getRegisterRequest());
                        finish();
                        startActivity(getIntent());
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }
            }catch (Exception e) {
                e.printStackTrace();

            }

        }
        }

    }
