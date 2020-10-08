package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class WorkoutList extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);
        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setBackgroundColor(Color.GRAY);
    }

    public void switchToEditWorkout(View view) {
        Intent intent = new Intent(this, EditWorkout.class);
        startActivity(intent);
    }

    public void backToMainHome(View view) {
        finish();
    }
}