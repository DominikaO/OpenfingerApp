package handlers;

import android.graphics.Bitmap;

public class SyncImageHandler {
    private static Bitmap bmp;


    public static synchronized Bitmap getBmp(){
        return bmp;
    }

    public static synchronized void setBmp(Bitmap bitmap){
        SyncImageHandler.bmp =bitmap;
    }
}
