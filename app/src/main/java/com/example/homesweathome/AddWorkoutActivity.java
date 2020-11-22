package com.example.homesweathome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homesweathome.firebase.access.WorkoutManager;
import com.example.homesweathome.model.Workout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class AddWorkoutActivity extends AppCompatActivity {
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_firebase]
    WorkoutManager workoutManager;
    // [END declare_firebase]

    EditText addWorkoutEditText;
    Button addWorkoutBtn;

    Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START initialize_firebase]
        // Initialize Firebase Realtime Database
        workoutManager = new WorkoutManager();
        // [END initialize_firebase]

        addWorkoutEditText = findViewById(R.id.add_workout_edit_text);
        addWorkoutBtn = findViewById(R.id.add_workout_btn);

        addWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workoutName = addWorkoutEditText.getText().toString();
                if (validateInput(workoutName)) {
                    workout = new Workout(mAuth.getUid(), workoutName);
                    workoutManager.add(workout).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(
                                    AddWorkoutActivity.this,
                                    "Successfully added workout.",
                                    Toast.LENGTH_SHORT).show();
                            cleanup();
                            Intent i = new Intent(AddWorkoutActivity.this, WorkoutListActivity.class);
                            startActivity(i);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(
                                    AddWorkoutActivity.this,
                                    "Failed to add workout.",
                                    Toast.LENGTH_SHORT).show();
                            cleanup();
                        }
                    });
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
    private void cleanup() {
        addWorkoutEditText.setText("");
        workout = null;
    }
}