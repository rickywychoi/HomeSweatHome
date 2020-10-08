package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

public class CountDownBeforeWorkout extends AppCompatActivity {
    private static int TIME_OUT = 7200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_before_workout);

        final TextView cd_ready, cd_three, cd_two, cd_one;

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(CountDownBeforeWorkout.this, WorkoutPlay.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);

        cd_ready = findViewById(R.id.count_down_ready);
        cd_three = findViewById(R.id.count_down_three);
        cd_two = findViewById(R.id.count_down_two);
        cd_one = findViewById(R.id.count_down_one);

        cd_three.setVisibility(View.GONE);
        cd_two.setVisibility(View.GONE);
        cd_one.setVisibility(View.GONE);

        AlphaAnimation fadeOut_ready = new AlphaAnimation( 1.0f , 0.0f ) ;
        cd_ready.startAnimation(fadeOut_ready);
        fadeOut_ready.setDuration(1800);
        fadeOut_ready.setFillAfter(true);
        fadeOut_ready.setStartOffset(1200);

        cd_three.postDelayed(new Runnable() {
            @Override
            public void run() {
                cd_three.setVisibility(View.VISIBLE);
            }
        }, 3600);

        AlphaAnimation fadeOut_three = new AlphaAnimation( 1.0f , 0.0f ) ;
        cd_three.startAnimation(fadeOut_three);
        fadeOut_three.setDuration(1200);
        fadeOut_three.setFillAfter(true);
        fadeOut_three.setStartOffset(3600);

        cd_two.postDelayed(new Runnable() {
            @Override
            public void run() {
                cd_two.setVisibility(View.VISIBLE);
            }
        }, 4800);

        AlphaAnimation fadeOut_two = new AlphaAnimation( 1.0f , 0.0f ) ;
        cd_two.startAnimation(fadeOut_two);
        fadeOut_two.setDuration(1200);
        fadeOut_two.setFillAfter(true);
        fadeOut_two.setStartOffset(4800);

        cd_one.postDelayed(new Runnable() {
            @Override
            public void run() {
                cd_one.setVisibility(View.VISIBLE);
            }
        }, 6000);

        AlphaAnimation fadeOut_one = new AlphaAnimation( 1.0f , 0.0f ) ;
        cd_one.startAnimation(fadeOut_one);
        fadeOut_one.setDuration(1200);
        fadeOut_one.setFillAfter(true);
        fadeOut_one.setStartOffset(6000);
    }
}