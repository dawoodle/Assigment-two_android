package com.example.yourpantry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText mTextFirstName;
    private EditText mTextLastName;
    private EditText mTextUsername;
    private EditText mTextPassword;
    private EditText mTextCnfPassword;
    private Button mButtonRegister;

    public static Credentials credentials;
    public static UserDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mTextFirstName = (EditText)findViewById(R.id.editText_firstName);
        mTextLastName = (EditText)findViewById(R.id.editText_lastName);
        mTextUsername = (EditText)findViewById(R.id.editText_username);
        mTextPassword = (EditText)findViewById(R.id.editText_password);
        mTextCnfPassword = (EditText) findViewById(R.id.editText_cnf_password);
        mButtonRegister = (Button)findViewById(R.id.btn_register);
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Firstname= mTextFirstName.getText().toString();
                String Lastname= mTextLastName.getText().toString();
                String Username = mTextUsername.getText().toString();
                String Password = mTextPassword.getText().toString();
                String CnfPassword = mTextCnfPassword.getText().toString();

                if(validate(Username, Password,Firstname,Lastname,CnfPassword)){
                    credentials = new Credentials(Username, Password, CnfPassword);
                    userDetails = new UserDetails(Firstname,Lastname);
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    Toast.makeText(RegisterActivity.this,"Registration Successful", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

        private boolean validate(String Username, String Password, String Firstname, String Lastname, String CnfPassword){

        if(Username.isEmpty() || (Password.length() < 8) || Firstname.isEmpty() || Lastname.isEmpty() || CnfPassword.isEmpty()){
            Toast.makeText(this,"Please enter all the details, at least 8 characters are required", Toast.LENGTH_SHORT).show();
            return false;
        }
            return true;
    }
}