package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homesweathome.model.Workout;

public class AddWorkoutActivity extends AppCompatActivity {
    EditText addWorkoutEditText;
    Button addWorkoutBtn;

    Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        addWorkoutEditText = findViewById(R.id.add_workout_edit_text);
        addWorkoutBtn = findViewById(R.id.add_workout_btn);

        addWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workoutName = addWorkoutEditText.getText().toString();
                if (validateInput(workoutName)) {

                }
            }
        });

    }

    boolean validateInput(String workoutName) {
        if (workoutName.length() == 0) {
            Toast.makeText(this, "Workout title cannot be empty.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}