package com.example.homesweathome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homesweathome.firebase.access.WorkoutManager;
import com.example.homesweathome.model.Workout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

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
    boolean atLeastOneChecked = false;
    List<DayOfWeek> dayOfWeekList;

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

        dayOfWeekList = new ArrayList<>();

        addWorkoutEditText = findViewById(R.id.add_workout_edit_text);
        addWorkoutBtn = findViewById(R.id.add_workout_btn);

        addWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workoutName = addWorkoutEditText.getText().toString();
                if (validateTitleInput(workoutName) && validateDaysChecked()) {
                    workout = new Workout(mAuth.getUid(), workoutName, dayOfWeekList);
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

    boolean validateTitleInput(String workoutName) {
        if (workoutName.length() == 0) {
            Toast.makeText(this, "Workout title cannot be empty.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    boolean validateDaysChecked() {
        if (!atLeastOneChecked) {
            Toast.makeText(this, "You need to check at least one day of week", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void cleanup() {
        addWorkoutEditText.setText("");
        workout = null;
        dayOfWeekList.clear();
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        boolean isMonChecked = false;
        boolean isTueChecked = false;
        boolean isWedChecked = false;
        boolean isThuChecked = false;
        boolean isFriChecked = false;
        boolean isSatChecked = false;
        boolean isSunChecked = false;

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.workout_monday:
                if (checked) {
                    dayOfWeekList.add(DayOfWeek.MONDAY);
                    isMonChecked = checked;
                } else {
                    dayOfWeekList.remove(DayOfWeek.MONDAY);
                    isMonChecked = !checked;
                }
                break;
            case R.id.workout_tuesday:
                if (checked) {
                    dayOfWeekList.add(DayOfWeek.TUESDAY);
                    isTueChecked = checked;
                } else {
                    dayOfWeekList.remove(DayOfWeek.TUESDAY);
                    isTueChecked = !checked;
                }
                break;
            case R.id.workout_wednesday:
                if (checked) {
                    dayOfWeekList.add(DayOfWeek.WEDNESDAY);
                    isWedChecked = checked;
                } else {
                    dayOfWeekList.remove(DayOfWeek.WEDNESDAY);
                    isWedChecked = !checked;
                }
                break;
            case R.id.workout_thursday:
                if (checked) {
                    dayOfWeekList.add(DayOfWeek.THURSDAY);
                    isThuChecked = checked;
                } else {
                    dayOfWeekList.remove(DayOfWeek.THURSDAY);
                    isThuChecked = !checked;
                }
                break;
            case R.id.workout_friday:
                if (checked) {
                    dayOfWeekList.add(DayOfWeek.FRIDAY);
                    isFriChecked = checked;

                } else {
                    dayOfWeekList.remove(DayOfWeek.FRIDAY);
                    isFriChecked = !checked;
                }
                break;
            case R.id.workout_saturday:
                if (checked) {
                    dayOfWeekList.add(DayOfWeek.SATURDAY);
                    isSatChecked = checked;
                } else {
                    dayOfWeekList.remove(DayOfWeek.SATURDAY);
                    isSatChecked = !checked;
                }
                break;
            case R.id.workout_sunday:
                if (checked) {
                    dayOfWeekList.add(DayOfWeek.SUNDAY);
                    isSunChecked = checked;
                } else {
                    dayOfWeekList.remove(DayOfWeek.SUNDAY);
                    isSunChecked = !checked;
                }
                break;
        }
        atLeastOneChecked = isMonChecked || isTueChecked || isWedChecked || isThuChecked
                || isFriChecked || isSatChecked || isSunChecked;
    }
}