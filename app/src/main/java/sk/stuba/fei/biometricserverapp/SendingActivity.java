package sk.stuba.fei.biometricserverapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendingActivity extends Activity {
    TextInputEditText messageText;
    Button b_send;
    ImageButton imageButton;
    ImageView imageView;
    TextView tw_answer;






    public void setTw_answer(String message) {
        this.tw_answer = findViewById(R.id.tw_answer);
        tw_answer.setText(message);
    }

    public void setImageView(Bitmap bmImage) {
        this.imageView=findViewById(R.id.imageView);
        imageView.setImageBitmap(bmImage);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sending_activity);
        b_send = findViewById(R.id.b_send);
        messageText = findViewById(R.id.ti_message);
        imageView = findViewById(R.id.imageView);
        imageButton= new ImageButton(this);
        imageButton= findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new ImageButtonClick());
        b_send.setOnClickListener(new SendButtonClick());

        //Thread getAnswerthread=new Thread(new ClientThread());
       // getAnswerthread.start();


    }

    private void sendButtonClicked() {
        //new SendMessage().execute();
        new Thread(new ClientThread()).start();
    }

    class SendButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            sendButtonClicked();
        }
    }

    private void imageButtonClicked() {
        selectImage(SendingActivity.this);
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

    @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(resultCode != RESULT_CANCELED) {
                switch (requestCode) {
                    case 1:
                        if (resultCode == RESULT_OK && data != null) {
                            Uri selectedImage =  data.getData();
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};
                            if (selectedImage != null) {
                                Cursor cursor = getContentResolver().query(selectedImage,
                                        filePathColumn, null, null, null);
                                if (cursor != null) {
                                    cursor.moveToFirst();

                                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                    String picturePath = cursor.getString(columnIndex);
                                    setImageView(BitmapFactory.decodeFile(picturePath));
                                    //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                    cursor.close();
                                }
                            }

                        }
                        break;
                }
            }
        }

    private class SendMessage extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... voids) {

            String message = messageText.getText().toString();
            Socket socket;
            socket=SocketHandler.getSocket();
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //out.write(message);
                //out.flush();
               // out.close();
                out.println(message);
                out.flush();
                String resp = in.readLine();
                Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    class ClientThread implements Runnable{
        Socket clientSocket = SocketHandler.getSocket();
        String message = messageText.getText().toString();
        Integer skus=Integer.valueOf(message);
        String response;
        StringBuilder answer=new StringBuilder();
        char[] buffer = new char[1024];

        @Override
        public void run() {
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //out.println(message);
                out.write(message);
                out.flush();


                while (in.read(buffer,0,1024) > 0)  {
                    answer.append(buffer);
                    String check = answer.toString();
                   if (check.contains(".")) {
                       break;
                   }



                }
                response = answer.toString();
                setTw_answer(response);


            } catch (IOException e) {
                System.out.println("chyba");

            }

        }
    }




}


