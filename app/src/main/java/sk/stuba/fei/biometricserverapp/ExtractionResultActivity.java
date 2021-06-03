package sk.stuba.fei.biometricserverapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.Socket;

import Helpers.BitmapHelper;
import OpenFinger.FingerprintOuterClass;
import OpenFinger.Level2OuterClass;
import OpenFinger.Level2VectorOuterClass;
import OpenFinger.WrapperOuterClass;
import handlers.SocketHandler;

public class ExtractionResultActivity extends AppCompatActivity {

    ImageView imgFingerprint;
    Boolean flag= false;
    WrapperOuterClass.Wrapper wrapper;
    TextView tv_level2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extraction_result);

        imgFingerprint = findViewById(R.id.imageView_fingerprint4);
        tv_level2 = findViewById(R.id.tv_Level2Vector);

        new Thread(new ReadResponse()).start();

        while(true){
            if (flag == true){
                setResponseIntent(wrapper);
                flag= false;
                break;
            }

        }
    }

    public void setResponseIntent(WrapperOuterClass.Wrapper response){
        FingerprintOuterClass.Fingerprint fingerprintView = response.getExtractResponse().getExtractionImage();
        Level2VectorOuterClass.Level2Vector vector = response.getExtractResponse().getLevel2Vector();

        Bitmap bmpView =BitmapHelper.FingerprintToBitmap(fingerprintView);
        imgFingerprint.setImageBitmap(bmpView);

        StringBuilder level2response =new StringBuilder();
        for(Level2OuterClass.Level2 level2: vector.getLevel2VectorList()){
            level2response.append(level2.toString());
        }
        //tv_level2.setText(level2response.toString());

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

                wrapper = response;
                flag =true;





            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}