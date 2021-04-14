package handlers;

import android.graphics.Bitmap;

public class ImageHandler {
    private Bitmap pic;

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }

    public ImageHandler(Bitmap pic) {
        this.pic = pic;
    }

    public ImageHandler() {
    }
}
