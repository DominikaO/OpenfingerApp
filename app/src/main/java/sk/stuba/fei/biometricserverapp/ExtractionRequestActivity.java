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

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import Helpers.MessageCreator;
import OpenFinger.WrapperOuterClass;
import handlers.ImageHandler;
import handlers.SocketHandler;
import handlers.SyncImageHandler;

public class ExtractionRequestActivity extends Activity {

    ImageView imgFingerprint, b_back;
    ImageButton b_gallery4;
    Button b_extract;
    ImageHandler imgHandler = new ImageHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extraction_request);
        imgFingerprint = findViewById(R.id.imageView_fingerprint3);
        b_gallery4 = findViewById(R.id.b_gallery4);
        b_extract = findViewById(R.id.b_extract);
        b_back = findViewById(R.id.b_back_to_menu5);
        b_back.setOnClickListener(new B_BackClick());
        b_extract.setOnClickListener(new ButtonClick());
        b_gallery4.setOnClickListener(new ImageButtonClick());

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
        new Thread(new Extract()).start();

    }

    class ButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ButtonClicked();
        }
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
                imgFingerprint.setImageBitmap(mBitmap);
                imgHandler.setPic(mBitmap);
                SyncImageHandler.setBmp(mBitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class Extract implements Runnable {
        @Override
        public void run() {

            Socket clientSocket = SocketHandler.getSocket();


            try {
                OutputStream out = clientSocket.getOutputStream();
                DataInputStream is = new DataInputStream(clientSocket.getInputStream());
                WrapperOuterClass.Wrapper extractReq = MessageCreator.createExtractionRequest(imgHandler);
                extractReq.writeTo(out);


                Intent prepResultIntent = new Intent(getApplicationContext(), ExtractionResultActivity.class);
                startActivity(prepResultIntent);
                finish();


            }catch (Exception e) {
                e.printStackTrace();

            }

        }

    }
}