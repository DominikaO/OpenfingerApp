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
import handlers.RegistrationRequestHandler;
import handlers.SocketHandler;

public class Registration_Activity extends Activity {

    ImageView imageView;
    ImageHandler img = new ImageHandler();
    ImageButton imageButton, b_back;
    TextInputEditText ti_name;
    Button b_send;
    RegistrationRequestHandler registrationRequestHandler = new RegistrationRequestHandler(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);

        imageButton= findViewById(R.id.b_gallery1);
        ti_name = findViewById(R.id.tin_login);
        b_send = findViewById(R.id.b_register);
        imageView = findViewById(R.id.imageView_fingerprint1);
        b_back = findViewById(R.id.b_back_to_menu1);


        imageButton.setOnClickListener(new Registration_Activity.ImageButtonClick());
        b_send.setOnClickListener(new Registration_Activity.ButtonClick());
        b_back.setOnClickListener(new Registration_Activity.B_BackClick());
//        registration= createMessage(ti_name,imageView);

    }


    private void imageButtonClicked() {
        selectImage(Registration_Activity.this);
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
        new Thread(new RequestAndAnswer()).start();

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
                img.setPic(mBitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public class RequestAndAnswer implements Runnable {



        @Override
        public void run() {

            Socket clientSocket = SocketHandler.getSocket();


            try {
                OutputStream out = clientSocket.getOutputStream();
                DataInputStream is = new DataInputStream(clientSocket.getInputStream());
                WrapperOuterClass.Wrapper registration =
                        MessageCreator.createRegistrationRequest(ti_name,img,registrationRequestHandler);
                registration.writeTo(out);

                byte[] buffer = new byte[1024];
                byte[] temp = null;

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int numbytesread = 0;
                while (true) {
                    numbytesread = is.read(buffer, 0, buffer.length);
                    byteArrayOutputStream.write(buffer,0,numbytesread);
                    temp =byteArrayOutputStream.toByteArray();


                    //cakam na server
                    try {
                        WrapperOuterClass.Wrapper response =
                                WrapperOuterClass.Wrapper.parseFrom(temp);
                        showToast(response.getRegisterResponse().getRegistrationStatus());
                        //registrationRequestHandler.removeRequest(registration2.getRegisterRequest());
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
