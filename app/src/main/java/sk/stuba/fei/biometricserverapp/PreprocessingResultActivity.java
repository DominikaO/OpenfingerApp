package sk.stuba.fei.biometricserverapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.List;

import Helpers.BitmapHelper;
import OpenFinger.FingerprintOuterClass;
import OpenFinger.WrapperOuterClass;
import handlers.SocketHandler;
import handlers.SyncPrepRequestHandler;

public class PreprocessingResultActivity extends Activity {

    private ImageView imageResult, back;
    private TextView tv_info;
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

        List<Boolean> list = SyncPrepRequestHandler.getCheckBoxes();

        for ( int i = 0; i< numberOfResults; i++){
            //if checkbox was checked in request show result
            if (SyncPrepRequestHandler.getCheckBoxes().get(i).booleanValue()) {

                FingerprintOuterClass.Fingerprint fingerprint = response.getPreprocResponse().getResults(i).getFingerprint();
                String preprocInfo = response.getPreprocResponse().getResults(i).getInfo();
                Bitmap fingerprintView;
                fingerprintView = BitmapHelper.FingerprintToBitmap(fingerprint);
                View view = inflater.inflate(R.layout.prepresult, layoutPrepresults, false);
                imageResult = view.findViewById(R.id.img_prepResults);
                tv_info = view.findViewById(R.id.tv_infoResult);
                imageResult.setImageBitmap(fingerprintView);
                tv_info.setText(preprocInfo);
                layoutPrepresults.addView(view);
            }
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

