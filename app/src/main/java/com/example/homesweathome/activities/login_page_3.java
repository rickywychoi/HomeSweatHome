package com.example.homesweathome.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.homesweathome.R;

public class login_page_3 extends AppCompatActivity {
    private static int TIME_OUT = 3000;  // Will move onto MainHome after 3 sec

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_3);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(login_page_3.this, MainHomeActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}