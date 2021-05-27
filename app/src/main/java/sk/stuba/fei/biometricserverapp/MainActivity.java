package sk.stuba.fei.biometricserverapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity{
    Button b_connect;
    TextView tW_aboutConnection;
    TextInputEditText tI_hostname, tI_port;
    TextView tw_hostname;
    AutofillValue ip= AutofillValue.forText("147.175.106.8");
    AutofillValue port = AutofillValue.forText("55556");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_connect = new Button(this);
        b_connect = (Button) findViewById(R.id.b_connect);
        tW_aboutConnection = (TextView) findViewById(R.id.tW_aboutConnection);
        tI_hostname = (TextInputEditText) findViewById(R.id.tI_hostname);
        tI_hostname.autofill(ip);
        tI_port = (TextInputEditText) findViewById(R.id.tI_port);
        tI_port.autofill(port);
        tw_hostname = findViewById(R.id.tw_hostname);

        b_connect.setOnClickListener(new ConnectButtonClick());
    }

    private void connectButtonClicked() {
        Intent intent = new Intent(getApplicationContext(), ConnectionStatusActivity.class);

        String mHostname = tI_hostname.getText().toString();
        String mPort = tI_port.getText().toString();
        //Checking if the Entries are empty
        if(!(mHostname.isEmpty()) && !(mPort.isEmpty())) {
            intent.putExtra("Hostname", mHostname);
            intent.putExtra("Port", mPort);

            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),"Text Entries Missing",Toast.LENGTH_SHORT).show();
        }
    }

    class ConnectButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            connectButtonClicked();
        }
    }

}




