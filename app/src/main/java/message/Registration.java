package message;

import android.graphics.Bitmap;

public class Registration {
    private final int operation = 1;
    private final Bitmap bm;
    private final  String name;

    public Bitmap getBm() {
        return bm;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "operation=" + operation +
                ", bm=" + bm +
                ", name='" + name + '\'' +
                '}';
    }

    public Registration(int operation, Bitmap bm, String name) {

        this.bm = bm;
        this.name = name;
    }

    public static class Builder{
        private final int operation;
        private final Bitmap bm;
        private final  String name;

        public Builder( final int op,
               final Bitmap bm, final  String name)
        {
            this.operation = op;
            this.bm = bm;
            this.name = name;
        }

        public Registration build()
        {
            return new Registration(operation, bm,name);
        }
    }
}
