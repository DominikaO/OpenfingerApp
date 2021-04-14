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

import com.google.android.material.textfield.TextInputEditText;
import com.google.protobuf.ByteString;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import handlers.ImageHandler;
import handlers.SocketHandler;
import message.RegistrationProto;

public class Registration_Activity extends Activity {
    private static int operation = 1;
    ImageView imageView;
    ImageHandler img = new ImageHandler();
    ImageButton imageButton;
    TextInputEditText ti_name;
    RegistrationProto.Registration registration;
    Button b_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);

        imageButton= findViewById(R.id.imageButton2);
        ti_name = findViewById(R.id.tin_name);
        b_send = findViewById(R.id.b_registerme);
        imageView = findViewById(R.id.imageView2);


        imageButton.setOnClickListener(new Registration_Activity.ImageButtonClick());
        b_send.setOnClickListener(new Registration_Activity.ButtonClick());
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

    private void ButtonClicked() {
        new Thread(new RequestAndAnswer()).start();

    }

    class ButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ButtonClicked();
        }
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

    public RegistrationProto.Registration createMessage(TextInputEditText ti_name,ImageView imageView){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        img.getPic().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        img.getPic().recycle();

        String name =ti_name.getText().toString();



        RegistrationProto.Registration mess =
                RegistrationProto.Registration.newBuilder().
                        setName(name).setPic(ByteString.copyFrom(byteArray))
                        .build();

        return mess;
    }

    public class RequestAndAnswer implements Runnable {

        @Override
        public void run() {

            Socket clientSocket = SocketHandler.getSocket();


            try {

                OutputStream out = clientSocket.getOutputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                RegistrationProto.Registration registration2 = createMessage(ti_name,imageView);
                registration2.writeTo(clientSocket.getOutputStream());

                char[] charArray = new char[20000];

                StringBuilder builder = new StringBuilder();
                int numCharsRead;
                while ((numCharsRead = in.read(charArray, 0, charArray.length)) != -1) {

                    builder.append(charArray, 0, numCharsRead);

                    byte[] targetArray = builder.toString().getBytes();

                    //cakam na server


                }

            } catch (IOException e) {
                System.out.println("chyba");

            }

        }
    }
}