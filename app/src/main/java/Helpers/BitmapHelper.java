package Helpers;

import android.graphics.Bitmap;
import android.graphics.Color;

import OpenFinger.FingerprintOuterClass;

public class BitmapHelper {

    public static byte[] BitmapToBytes(Bitmap fingerprint){
        int countbytes = fingerprint.getByteCount();
        int[] pixels = new int[countbytes];
        byte[] pixelsdata = new byte[countbytes];
        fingerprint.getPixels(pixels,
                0,fingerprint.getWidth(),0,0,
                fingerprint.getWidth(),fingerprint.getHeight());

        for (int i = 0; i< pixels.length ;i++){
            pixelsdata[i] = (byte) Color.red(pixels[i]);
        }
        return pixelsdata;
    }

    public static Bitmap FingerprintToBitmap(FingerprintOuterClass.Fingerprint fingerprint){
        int dataSize = fingerprint.getData().size();
        byte[] bitmapdata = new byte[dataSize];
        System.arraycopy(fingerprint.getData().toByteArray(), 0, bitmapdata, 0, dataSize); //skopiruje bajty

        Bitmap bmp = Bitmap.createBitmap(fingerprint.getWidth(), fingerprint.getHeight(), Bitmap.Config.ARGB_8888);//vytvori mapu, ktora sa da menit
        for (int i = 0; i < fingerprint.getHeight(); i++) {
            for (int j = 0; j < fingerprint.getWidth(); j++) {
                bmp.setPixel(j, i, Color.argb(255,
                        bitmapdata[i * fingerprint.getWidth() + j]
                        , bitmapdata[i * fingerprint.getWidth() + j]
                        , bitmapdata[i * fingerprint.getWidth() + j]));
            }
        }
        return bmp;

    }
}
