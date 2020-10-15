package com.example.homesweathome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class WorkoutPlayActivity extends AppCompatActivity {
    private static int seconds = 0;
    private boolean running = true;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_play);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    public static void setTime(int exerciseTimeInSeconds) { seconds = exerciseTimeInSeconds; }

    private void runTimer() {
        final TextView tvDuration = findViewById(R.id.duration);
        final android.os.Handler handler = new android.os.Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                tvDuration.setText(time);

                if (running) { seconds--; }

                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) { running = true; }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", wasRunning);
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