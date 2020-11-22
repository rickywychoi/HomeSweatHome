package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.TextView;

public class EditWorkoutActivity extends AppCompatActivity {
    private TextView workoutTitleTv;
    private String workoutTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        Bundle extras = getIntent().getExtras();
        workoutTitle = extras.getString("WorkoutTitle");

        workoutTitleTv = findViewById(R.id.workout_edit_title);
        workoutTitleTv.setText(workoutTitle);
    }

    public String getWorkoutTitle() {
        Bundle extras = getIntent().getExtras();
        workoutTitle = extras.getString("WorkoutTitle");
        return workoutTitle;
    }
}