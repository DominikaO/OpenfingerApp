package sk.stuba.fei.biometricserverapp;

import android.app.Activity;

public class SendActivity extends Activity {
   /* ImageButton imageButton;
    Button b_send;
    TextInputEditText messageText;
    ImageView imageView;

    public void setImageView(Bitmap bmImage) {
        this.imageView=findViewById(R.id.imageView);
        imageView.setImageBitmap(bmImage);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sending_activity);
        imageButton= new ImageButton(this);
        imageButton= findViewById(R.id.imageButton);
        messageText = findViewById(R.id.ti_message);

        imageButton.setOnClickListener(new ImageButtonClick());
        b_send.setOnClickListener(new SendButtonClick());



    }

    private void sendButtonClicked() {
        new SendMessage().execute();
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
            ConnectionStatusActivity displayMessage = new ConnectionStatusActivity();
            Socket socket = displayMessage.getSocket();

            String message = messageText.getText().toString();

            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.write(message);
                printWriter.flush();
                printWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }*/
}
