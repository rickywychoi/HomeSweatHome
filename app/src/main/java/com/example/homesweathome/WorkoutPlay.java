package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class WorkoutPlay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_play);

        ImageButton pauseButton = findViewById(R.id.pause_button);
    }

    public void onClickPauseWorkout(View view) {
        Intent i = new Intent(WorkoutPlay.this, WorkoutPause.class);
        startActivity(i);
    }

    public void workoutDone(View view) {
        Intent i = new Intent(WorkoutPlay.this, WorkoutDone.class);
        startActivity(i);
    }
}