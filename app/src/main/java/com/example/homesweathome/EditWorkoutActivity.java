package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditWorkoutActivity extends AppCompatActivity {

    ConstraintLayout workout2;
    ConstraintLayout workout3;
    ConstraintLayout workout4;
    ConstraintLayout workout5;
    int countActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);
        workout2 = findViewById(R.id.workoutTab2);
        workout3 = findViewById(R.id.workoutTab3);
        workout4 = findViewById(R.id.workoutTab4);
        workout5 = findViewById(R.id.workoutTab5);
        countActivity = 1;
    }

    public void backToWorkoutList(View view) {
        finish();
    }

    public void switchToEditWorkout(View view) {
    }

    public void addWorkout(View view) {
        countActivity++;
        switch (countActivity) {
            case 2:
                workout2.setVisibility(View.VISIBLE);
                break;
            case 3:
                workout3.setVisibility(View.VISIBLE);
                break;
            case 4:
                workout4.setVisibility(View.VISIBLE);
                break;
            case 5:
                workout5.setVisibility(View.VISIBLE);
                break;
        }

    }
}