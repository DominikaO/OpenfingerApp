package message.request;

import android.graphics.Bitmap;

public class RegistrationRequest {
    private final int requestId;
    private final Bitmap fingetprint;
    private final  String login;

    public int getRequestId() {
        return requestId;
    }

    public Bitmap getFingetprint() {
        return fingetprint;
    }

    public String getLogin() {
        return login;
    }

    public RegistrationRequest(int operation, String name, final Bitmap bm) {
        this.requestId =operation;
        this.fingetprint = bm;
        this.login = name;
    }

    public static class Builder{
        private final int requestId;
        private final  String login;
        private final Bitmap fingerprint;


        public Builder(final int id,
               final String login, final Bitmap finger)
        {
            this.requestId =id;
            this.login = login;
            this.fingerprint = finger;
        }

        public RegistrationRequest build()
        {
            return new RegistrationRequest(requestId, login,fingerprint);
        }
    }
}
