package com.example.tcashapps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.tcashapps.R;
import com.example.tcashapps.SessionManagement;

public class SplashScreenActivity extends AppCompatActivity {
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sessionManagement = new SessionManagement(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sessionManagement.getToken().isEmpty()){
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, 2000);
    }
}
