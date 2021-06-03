package com.example.yourpantry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText mTextUsername;
    private EditText mTextPassword;
    private Button mButtonLogin;
    private TextView mTextViewAttempts;
    private TextView mTextViewRegister;

    boolean isValid = false;
    private int counter = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextUsername = findViewById(R.id.editText_username);
        mTextPassword = findViewById(R.id.editText_password);
        mTextViewRegister = findViewById(R.id.textView_register);
        mTextViewAttempts = findViewById(R.id.textView_Attempts);
        mButtonLogin = findViewById(R.id.btn_login);

        mButtonLogin.setOnClickListener(v -> {
            String inputName = mTextUsername.getText().toString();
            String inputPassword = mTextPassword.getText().toString();

            if (inputName.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please Enter Your Details", Toast.LENGTH_SHORT).show();
            } else {

                isValid = validate(inputName, inputPassword);

                if (!isValid) {

                    counter--;
                    Toast.makeText(MainActivity.this, "Incorrect username or password entered", Toast.LENGTH_SHORT).show();

                    mTextViewAttempts.setText("No. of attempts remaining:" + counter);

                    if (counter == 0) {
                        mButtonLogin.setEnabled(false);
                    }
                } else {

                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);



                }
            }
        });

        mTextViewRegister.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    private boolean validate(String name, String password) {

        if (RegisterActivity.credentials != null) {
            return name.equals(RegisterActivity.credentials.getUsername()) && password.equals(RegisterActivity.credentials.getPassword());
        }
            return false;

        }

}