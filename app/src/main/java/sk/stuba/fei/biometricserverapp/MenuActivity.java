package sk.stuba.fei.biometricserverapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity{
    private Button b_register;
    private Button b_verificate, b_preprocRequest, b_readme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        b_register = findViewById(R.id.b_registration);
        b_verificate = findViewById(R.id.b_verification);
        b_preprocRequest = findViewById(R.id.b_preprocRequest);
        b_readme = findViewById(R.id.b_extract);


        b_register.setOnClickListener(new RegisterButtonClick());
        b_verificate.setOnClickListener(new VerificateButtonClick());
        b_preprocRequest.setOnClickListener(new PreprocessingButtonClick());
        b_readme.setOnClickListener(new ReadMeButtonClick());

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

    private void verificateButtonClicked() {
        Intent new_intent = new Intent(getApplicationContext(), VerificationActivity.class);
        startActivity(new_intent);
        finish();
    }

    class VerificateButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            verificateButtonClicked();
        }
    }

    private void preprocessingButtonClicked() {
        Intent new_intent = new Intent(getApplicationContext(), PreprocessingRequestActivity.class);
        startActivity(new_intent);
        finish();
    }

    class PreprocessingButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            preprocessingButtonClicked();
        }
    }

    private void readMeButtonClicked() {
        Intent new_intent = new Intent(getApplicationContext(), ExtractionRequestActivity.class);
        startActivity(new_intent);
        finish();

    }

    class ReadMeButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            readMeButtonClicked();
        }
    }
}