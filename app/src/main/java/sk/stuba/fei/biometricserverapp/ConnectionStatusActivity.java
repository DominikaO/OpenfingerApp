package sk.stuba.fei.biometricserverapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.Socket;

public class ConnectionStatusActivity extends Activity {
    private TextView tw_hostname;
    private TextView tw_ip, tw_status;
    private Button b_continue;
    String TheHost, ThePort;
    Socket socket;



    public ConnectionStatusActivity() {
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_status);

        tw_hostname = findViewById(R.id.tw_hostname);
        tw_ip = findViewById(R.id.tw_port);
        tw_status = findViewById(R.id.tw_status);
        b_continue = new Button(this);
        b_continue=findViewById(R.id.b_continue);


        Intent getText = getIntent();
        TheHost = getText.getStringExtra("Hostname");
        ThePort = getText.getStringExtra("Port");

        tw_hostname.setText(TheHost);
        tw_ip.setText(ThePort);

        new ConnectingToServer().execute();
        b_continue.setOnClickListener(new ContinueButtonClick());

    }

//todo buttton back onclicklistener

    private void continueButtonClicked() {
        Intent new_intent = new Intent(getApplicationContext(), SendingActivity.class);
        startActivity(new_intent);
        finish();
    }

    class ContinueButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            continueButtonClicked();
        }
    }


    private class ConnectingToServer extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... voids) {


            try {
                //socket = new Socket("147.175.106.8", 22222);
                socket=new Socket(TheHost, Integer.parseInt(ThePort));
                SocketHandler.setSocket(socket);
                boolean isConn=socket.isConnected();
                String str= null;
                str= str.valueOf(isConn);
                if (str=="true") {
                    tw_status.setText("was sucessful");
                }
            } catch (IOException e) {
                tw_status.setText("failed");
            }


            return null;
        }



    }
}