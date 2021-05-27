package sk.stuba.fei.biometricserverapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.List;

import OpenFinger.Level2OuterClass;
import OpenFinger.WrapperOuterClass;
import handlers.SocketHandler;
import handlers.SyncImageHandler;

public class ExtractionResultActivity extends AppCompatActivity {

    ImageView imgFingerprint;
    Boolean flag= false;
    WrapperOuterClass.Wrapper wrapper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extraction_result);

        imgFingerprint = findViewById(R.id.imageView_fingerprint4);

        new Thread(new ReadResponse()).start();

        while(true){
            if (flag == true){
                drawVectors(wrapper, SyncImageHandler.getBmp());
            }
            flag= false;
            break;
        }
    }

    public void drawVectors(WrapperOuterClass.Wrapper response, Bitmap fingerprint){
        Canvas canvas;
        Bitmap copy = null;
        if (!fingerprint.isMutable()){
            copy = fingerprint.copy(Bitmap.Config.ARGB_8888,true);
            canvas = new Canvas(copy);
        }else {
            canvas = new Canvas(fingerprint);
        }
        Paint p = new Paint();
        p.setColor(Color.RED);

        List<Level2OuterClass.Level2> resultList =
                response.getExtractResponse().getLevel2Vector().getLevel2VectorList();
        for (Level2OuterClass.Level2 vectors : resultList){
            canvas.drawCircle(vectors.getX(),vectors.getY(), 1,p);
        }
        imgFingerprint.setImageDrawable(new BitmapDrawable(getResources(),copy));
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
                flag =true;
                wrapper = response;





            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}