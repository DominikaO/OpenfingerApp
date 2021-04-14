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
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import handlers.ImageHandler;
//skusobna activity na Person
public class SendingActivity extends Activity {
    TextInputEditText messageText;
    Button b_send;
    ImageButton imageButton;
    ImageView imageView;
    TextView tw_answer;
    private ImageHandler img;






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
        // new Communication().execute();
        //new Thread(new RequestAndAnswer()).start();
        //ClientThread ct = new ClientThread();
       // ct.run();

       // ct.start();


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
                img = new ImageHandler(mBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
/*
    private class Communication extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... voids) {

            //String message = messageText.getText().toString();
            Socket clientSocket = SocketHandler.getSocket();
            try {
                OutputStream out = clientSocket.getOutputStream();
                pers.writeTo(out);
                System.out.println(pers.getSerializedSize());

                InputStream is = clientSocket.getInputStream();
                DataInputStream ds = new DataInputStream(is);
                int bufferSize = ds.readInt();
                System.out.println(bufferSize);
                byte[] message = new byte[bufferSize];
                ds.readFully(message);

                Person2Proto.Person input = Person2Proto.Person.parseFrom(message);
                Person human = new Person(input.getAge(), input.getName());
                System.out.println(human.toString());


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    class ClientThread extends Thread implements  Runnable{
        Socket clientSocket = SocketHandler.getSocket();
        private String name ;
        private int age;

        ClientThread(int age, String name){
            this.age= age;
            this.name = name;

        }
        ClientThread(){

        }

        @Override
        public void run() {
            try {
                //PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                OutputStream out = clientSocket.getOutputStream();
               //BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //out.println(message);
                //out.write(message);
               // out.flush();

                pers.writeTo(clientSocket.getOutputStream());


                InputStream is = clientSocket.getInputStream();
                DataInputStream ds = new DataInputStream(is);
                int bufferSize = ds.readInt();
                System.out.println(bufferSize);
                byte[] message = new byte[bufferSize];
                ds.readFully(message);

                Person2Proto.Person input = Person2Proto.Person.parseFrom(message);
                Person human = new Person(input.getAge(), input.getName());
                System.out.println(human.toString());

                setTw_answer(human.toString());






            } catch (IOException e) {
                System.out.println("chyba");

            }

        }
    }
    byte[] toBytes(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
        return bytes;
    }

    public class RequestAndAnswer implements Runnable {

            @Override
            public void run() {
                System.out.println("hej");
                Socket clientSocket = SocketHandler.getSocket();
                String name;
                int age;

                try {
                    //PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    OutputStream out = clientSocket.getOutputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    //out.println(message);
                    //out.write(message);
                    // out.flush();

                   pers.writeTo(clientSocket.getOutputStream());
                    System.out.println("poslalsom");

                    int[] buffer = new int[2000];

                    char[] charArray = new char[20000];

                    StringBuilder builder = new StringBuilder();
                    int numCharsRead;
                    while ((numCharsRead = in.read(charArray, 0, charArray.length)) != -1) {

                        builder.append(charArray, 0, numCharsRead);

                        byte[] targetArray = builder.toString().getBytes();


                        //DataInputStream ds = new DataInputStream(is)
                        ByteArrayOutputStream result = new ByteArrayOutputStream();
                        //DataInputStream ds = new DataInputStream(is);
                        //int bufferSize = ds.readInt();
                        //byte[] buffer = new byte[bufferSize];
                        //System.out.println(bufferSize);
                        System.out.println("citam");
                        //is.read(buffer);
                        //is.read(buffer,0,bufferSize);
                        //buffer[bufferSize-1]= '\n';


                        int nRead;
                        byte[] data = new byte[20132692];

                        // while ((nRead = is.read(data, 0, data.length)) != -1) {
                        //  result.write(data, 0, nRead);


                        System.out.println("precitalsom");


                        //PersonOuterClass.Person in = PersonOuterClass.Person.parseFrom(buffer);
                        PersonOuterClass.Person input = null;

                        input = PersonOuterClass.Person.parseFrom(targetArray);
                        Person2Proto.Person inp = Person2Proto.Person.parseFrom(targetArray);

                        Person human2 = new Person(inp.getAge(), inp.getName());
                        Person human = new Person(input.getAge(), input.getName());


                        System.out.println(human.toString());

                        setTw_answer("ideto");

                    }

                } catch (IOException e) {
                    System.out.println("chyba");

                }

            }
        }*/

    }






