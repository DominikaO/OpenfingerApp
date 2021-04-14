package sk.stuba.fei.biometricserverapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    private Button b_register;
    private Button b_find;
    private Button b_servsett;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        b_register = findViewById(R.id.b_registerme);
        b_find = findViewById(R.id.b_find);
        b_servsett = findViewById(R.id.b_servsett);

        b_register.setOnClickListener(new RegisterButtonClick());
        b_find.setOnClickListener(new FindButtonClick());
        b_servsett.setOnClickListener(new SettingsButtonClick());
    }

    private void registerButtonClicked() {
        Intent new_intent = new Intent(getApplicationContext(), Registration_Activity.class);
        startActivity(new_intent);
        finish();
    }

    class RegisterButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            registerButtonClicked();
        }
    }

    private void findButtonClicked() {
        Intent new_intent = new Intent(getApplicationContext(), SendingActivity.class);
        startActivity(new_intent);
        finish();
    }

    class FindButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            findButtonClicked();
        }
    }

    private void settingsButtonClicked() {
        Intent new_intent = new Intent(getApplicationContext(), SendingActivity.class);
        startActivity(new_intent);
        finish();
    }

    class SettingsButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            settingsButtonClicked();
        }
    }
}