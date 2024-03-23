package com.android.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ForgotPass extends AppCompatActivity {
    TextInputLayout inpUser;
    Button btnFind;
    TextView btnSignUp;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        inpUser = findViewById(R.id.username_find);
        btnFind = findViewById(R.id.btn_find);
        btnSignUp = findViewById(R.id.btn_signUp);

        preferences = getSharedPreferences("userInfo", 0);

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userValue = inpUser.getEditText().getText().toString();
                String emailValue = inpUser.getEditText().getText().toString();

                String loginUser = preferences.getString("user","");
                String emailUser = preferences.getString("email","");

                if (userValue.equals(loginUser) || emailValue.equals(emailUser)){
                    Toast.makeText(ForgotPass.this, "Successfully Find Your Account", Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent = new Intent(ForgotPass.this, EditUser.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(ForgotPass.this, "Username or Email not Found", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPass.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}