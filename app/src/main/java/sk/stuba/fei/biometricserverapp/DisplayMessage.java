package sk.stuba.fei.biometricserverapp;

//public class DisplayMessage extends Activity implements View.OnClickListener {
    /*private TextView tw_hostname;
    private TextView tw_ip, tw_status;
    private Button b_continue;
    String TheHost, ThePort;
     Socket socket;

    public Socket getSocket() {
        return socket;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);

        tw_hostname = findViewById(R.id.tw_hostname);
        tw_ip = findViewById(R.id.tw_port);
        tw_status = findViewById(R.id.tw_status);
        b_continue = findViewById(R.id.b_continue);


        Intent getText = getIntent();
        TheHost = getText.getStringExtra("Hostname");
        ThePort = getText.getStringExtra("Port");

        tw_hostname.setText(TheHost);
        tw_ip.setText(ThePort);

        new Communication().execute();
        b_continue.setOnClickListener(this);
        
    }

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.b_continue:
                Intent new_intent = new Intent(getApplicationContext(), SendingActivity.class);
                startActivity(new_intent);
                finish();
            }
        }


    private class Communication extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... voids) {


            try {
                //socket = new Socket("147.175.106.8", 22222);
                socket=new Socket(TheHost, Integer.parseInt(ThePort));
            } catch (IOException e) {
                e.printStackTrace();
            }
            boolean isConn=socket.isConnected();
            String str= null;
            str= str.valueOf(isConn);
            if (str=="true") {
                tw_status.setText("was sucessful");
            }
            else {
                tw_status.setText("failed");
            }

            return null;
        }



    }*/

//}
