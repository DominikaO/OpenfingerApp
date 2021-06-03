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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import Helpers.MessageCreator;
import OpenFinger.WrapperOuterClass;
import handlers.ImageHandler;
import handlers.SocketHandler;
import handlers.SyncPrepRequestHandler;

public class PreprocessingRequestActivity extends Activity {


    public TextInputEditText block, lambda, sigma;
    private ImageButton imageButton;
    private ImageHandler imageHandler = new ImageHandler();
    private Button b_submit;
    private ImageView b_back;
    public CheckBox quality, contrast, colormap, gaborfilter, binar, skelet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preprocessing_request);

        imageButton= findViewById(R.id.b_gallery3);
        block =findViewById(R.id.tine_block);
        lambda =findViewById(R.id.tine_lambda);
        sigma =findViewById(R.id.tine_sigma);
        b_submit=findViewById(R.id.b_submit);
        b_back = findViewById(R.id.b_back_to_menu3);

        quality = findViewById(R.id.chckB_quality1);
        contrast = findViewById(R.id.chcB_contrast2);
        colormap = findViewById(R.id.chckB_colormap3);
        gaborfilter = findViewById(R.id.chcB_gabor4);
        binar = findViewById(R.id.chcB_binarization5);
        skelet = findViewById(R.id.chcB_skeleton6);




        imageButton.setOnClickListener(new ImageButtonClick());
        b_submit.setOnClickListener(new ButtonClick());
        b_back.setOnClickListener(new B_BackClick());
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
        //checking checkboxes and setting to true/false to handler
        SyncPrepRequestHandler.setBinarization(skelet.isChecked());
        SyncPrepRequestHandler.setColormap(colormap.isChecked());
        SyncPrepRequestHandler.setConstrast(contrast.isChecked());
        SyncPrepRequestHandler.setGaborfilter(gaborfilter.isChecked());
        SyncPrepRequestHandler.setQualityMap(quality.isChecked());
        SyncPrepRequestHandler.setSkeleton(skelet.isChecked());

        SyncPrepRequestHandler.addCheckBoxes();


        new Thread(new Submit()).start();

    }

    class ButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ButtonClicked();
        }
    }

    private void imageButtonClicked() {
        selectImage(PreprocessingRequestActivity.this);
    }

    class ImageButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            imageButtonClicked();
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

                imageHandler.setPic(mBitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public class Submit implements Runnable {



        @Override
        public void run() {

            Socket clientSocket = SocketHandler.getSocket();


            try {
                OutputStream out = clientSocket.getOutputStream();
                DataInputStream is = new DataInputStream(clientSocket.getInputStream());
                WrapperOuterClass.Wrapper prepRequest = MessageCreator.createPreprocessRequest(imageHandler,block,lambda,sigma);
                prepRequest.writeTo(out);



                Intent prepResultIntent = new Intent(getApplicationContext(), PreprocessingResultActivity.class);
                startActivity(prepResultIntent);
                finish();


            }catch (Exception e) {
                e.printStackTrace();

            }

        }

    }

}