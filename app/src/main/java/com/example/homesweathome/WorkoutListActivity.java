package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorkoutListActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);
        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setBackgroundColor(Color.GRAY);

        Button leg_day_btn = findViewById(R.id.leg_day_btn);
        leg_day_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutListActivity.this, EditWorkoutActivity.class);
                startActivity(intent);
            }
        });
    }

    public void switchToAddWorkout(View view) {
        Intent intent = new Intent(this, AddWorkoutActivity.class);
        startActivity(intent);
    }

    public void backToMainHome(View view) {
        finish();
    }
}