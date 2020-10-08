package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WorkoutPause extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_pause);
    }

    public void onClickSkipThisWorkout(View view) {

    }

    public void onClickResumeWorkout(View view) { finish(); }

    public void onClickQuit(View view) {
        Intent i = new Intent(WorkoutPause.this, MainHome.class);
        startActivity(i);
    }
}