package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainHome extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setBackgroundColor(Color.GRAY);
    }

    public void switchToWorkoutList(View view) {
        Intent intent = new Intent(MainHome.this, WorkoutList.class);
        startActivity(intent);
    }

    public void switchToWorkoutPlay(View view) {
        Intent intent = new Intent(MainHome.this, CountDownBeforeWorkout.class);
        startActivity(intent);
    }
}