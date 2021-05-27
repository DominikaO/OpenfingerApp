package sk.stuba.fei.biometricserverapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.Socket;

import Helpers.BitmapHelper;
import OpenFinger.FingerprintOuterClass;
import OpenFinger.WrapperOuterClass;
import handlers.SocketHandler;

public class PreprocessingResultActivity extends Activity {

    private ImageView result1, back;
    public boolean createIntent = false;
    public WrapperOuterClass.Wrapper wrapperHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preprocessing_result);
        back = findViewById(R.id.b_back_to_menu4);
        back.setOnClickListener(new B_BackClick());

        new Thread(new ReadResponse()).start();
        while(true) {
            if (createIntent == true) {
                createResultIntent(wrapperHandler);
                createIntent=false;
                break;
            }
        }


    }
    private void B_BackClicked() {

        Intent new_intent = new Intent(getApplicationContext(), PreprocessingRequestActivity.class);
        startActivity(new_intent);
        finish();
    }

    class B_BackClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            B_BackClicked();
        }
    }
    public void createResultIntent(WrapperOuterClass.Wrapper response){

        int numberOfResults = response.getPreprocResponse().getResultsList().size();
        LinearLayout layoutPrepresults = findViewById(R.id.scrollablePrepResults);
        LayoutInflater inflater = LayoutInflater.from(this);

        for ( int i = 0; i< numberOfResults; i++){
            FingerprintOuterClass.Fingerprint fingerprint = response.getPreprocResponse().getResults(i);
            Bitmap fingerprintView = BitmapHelper.FingerprintToBitmap(fingerprint);

            View view = inflater.inflate(R.layout.prepresult, layoutPrepresults, false);
            result1 = view.findViewById(R.id.img_prepResults);
            result1.setImageBitmap(fingerprintView);
            layoutPrepresults.addView(view);

        }
    }

    public void showToast(final String toast) {

        runOnUiThread(() -> Toast.makeText(this, toast, Toast.LENGTH_LONG).show());

    }

    public class ReadResponse implements Runnable {

        @Override
        public void run() {
            Socket clientSocket = SocketHandler.getSocket();
            try {

                DataInputStream is = new DataInputStream(clientSocket.getInputStream());
                byte[] buffer = new byte[1024];
                byte[] temp = null;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int numbytesread = 0;
                WrapperOuterClass.Wrapper response = null;
                while (true) {
                    numbytesread = is.read(buffer, 0, buffer.length);
                    byteArrayOutputStream.write(buffer, 0, numbytesread);
                    temp = byteArrayOutputStream.toByteArray();

                    try {
                        response = WrapperOuterClass.Wrapper.parseFrom(temp);
                        showToast("" + response.getPreprocResponse().getResultsList().size());//povie kolko tam je vysledkov
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
                wrapperHandler = response;
                createIntent = true;


            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}

