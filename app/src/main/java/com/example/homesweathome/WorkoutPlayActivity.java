package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class WorkoutPlayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_play);

        ImageButton pauseButton = findViewById(R.id.pause_button);
    }

    public void onClickPauseWorkout(View view) {
        Intent i = new Intent(WorkoutPlayActivity.this, WorkoutPauseActivity.class);
        startActivity(i);
    }

    public void workoutDone(View view) {
        Intent i = new Intent(WorkoutPlayActivity.this, WorkoutDoneActivity.class);
        startActivity(i);
    }
}