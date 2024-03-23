package com.android.travelapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.SplashScreen;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 3000;

    Animation topAnim, bottomAnim;
    ImageView img;
    TextView txtNameApp, txtSubNameApp, txtCopyright;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
        setContentView(R.layout.activity_main);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        img = findViewById(R.id.img_logo);
        txtNameApp = findViewById(R.id.tv_nameApp);
        txtSubNameApp = findViewById(R.id.tv_sub_nameApp);
        txtCopyright = findViewById(R.id.tv_copyright);

        img.setAnimation(topAnim);
        txtNameApp.setAnimation(bottomAnim);
        txtSubNameApp.setAnimation(bottomAnim);
        txtCopyright.setAnimation(bottomAnim);

        new Handler().postDelayed((Runnable) () -> {
            Intent intent = new Intent(MainActivity.this, LoginPage.class);
            startActivity(intent);
            finish();

        }, SPLASH_SCREEN);
    }
}
