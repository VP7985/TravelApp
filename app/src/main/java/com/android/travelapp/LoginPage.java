package com.android.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginPage extends AppCompatActivity {
    TextView btnSignUp;
    ImageView img;
    TextView txtTitle, txtSub;
    TextInputLayout txtUser, txtPass;
    LinearLayout txtSignUp;
    Button btnLogin, btnForgotPass;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
        setContentView(R.layout.activity_login_page);

        img = findViewById(R.id.img_logo);
        txtTitle = findViewById(R.id.tv_titleLogin);
        txtSub = findViewById(R.id.subtitleLogin);
        txtUser = findViewById(R.id.username_login);
        txtPass = findViewById(R.id.password_login);
        btnLogin = findViewById(R.id.btn_login);
        btnForgotPass = findViewById(R.id.btn_forgotPass);
        btnSignUp = findViewById(R.id.btn_signUp);
        txtSignUp = findViewById(R.id.ll_signup);

        preferences = getSharedPreferences("userInfo", 0);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, SignUp.class);
                startActivity(intent);
            }
        });

        txtPass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    String userValue = txtUser.getEditText().getText().toString();
                    String emailValue = txtUser.getEditText().getText().toString();
                    String passValue = txtPass.getEditText().getText().toString();

                    String loginUser = preferences.getString("user","");
                    String emailUser = preferences.getString("email","");
                    String loginPass = preferences.getString("pass","");

                    if (userValue.equals(loginUser) && passValue.equals(loginPass) || emailValue.equals(emailUser) && passValue.equals(loginPass)){
                        Intent intent = new Intent(LoginPage.this, Dashboard.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginPage.this, "Login", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(LoginPage.this, "Username or Password doesn't match", Toast.LENGTH_LONG).show();
                    }
                }
                return false;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userValue = txtUser.getEditText().getText().toString();
                String emailValue = txtUser.getEditText().getText().toString();
                String passValue = txtPass.getEditText().getText().toString();

                String loginUser = preferences.getString("user","");
                String emailUser = preferences.getString("email","");
                String loginPass = preferences.getString("pass","");

                if (userValue.equals(loginUser) && passValue.equals(loginPass) || emailValue.equals(emailUser) && passValue.equals(loginPass)){
                    Intent intent = new Intent(LoginPage.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(LoginPage.this, "Login", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(LoginPage.this, "Username or Password doesn't match", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(LoginPage.this, ForgotPass.class);
                startActivity(intent);
            }
        });
    }
}

