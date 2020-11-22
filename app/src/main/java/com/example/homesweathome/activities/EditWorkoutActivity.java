package com.example.homesweathome.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.homesweathome.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditWorkoutActivity extends AppCompatActivity {

    EditText editTextExcerciseName;
    Button buttonAddWorkoutreps;
    Button buttonAddWorkouttime;
    NumberPicker setChoice;
    NumberPicker repChoice;
    NumberPicker minChoice;
    NumberPicker secChoice;
    LinearLayout repLayout;
    LinearLayout timeLayout;


    DatabaseReference databaseWorkouts;

    ListView lvStudents;
    List<Workout> WorkoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_edit_workout);

        timeLayout = findViewById(R.id.timeLayout);
        repLayout = findViewById(R.id.repLayout);
        databaseWorkouts = FirebaseDatabase.getInstance().getReference("Exercises");
        setChoice = findViewById(R.id.sets);
        setChoice.setMinValue(0);
        setChoice.setMaxValue(10);
        repChoice = findViewById(R.id.repetitions);
        repChoice.setMinValue(0);
        repChoice.setMaxValue(50);
        minChoice = findViewById(R.id.minutes);
        minChoice.setMinValue(0);
        minChoice.setMaxValue(60);
        secChoice = findViewById(R.id.seconds);
        secChoice.setMinValue(0);
        secChoice.setMaxValue(59);


        editTextExcerciseName = findViewById(R.id.editTextExcerciseName);
        buttonAddWorkoutreps = findViewById(R.id.buttonAddWorkoutReps);
        buttonAddWorkouttime = findViewById(R.id.buttonAddWorkouttime);

        buttonAddWorkoutreps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });

        lvStudents = findViewById(R.id.lvStudents);
        WorkoutList = new ArrayList<Workout>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseWorkouts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                WorkoutList.clear();
                for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()) {
                    Workout work = studentSnapshot.getValue(Workout.class);
                    WorkoutList.add(work);
                }

                WorkoutAdapter adapter = new WorkoutAdapter(EditWorkoutActivity.this, WorkoutList);
                lvStudents.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    private void deleteStudent(String id) {
        DatabaseReference dbRef = databaseWorkouts.child(id);

        Task setRemoveTask = dbRef.removeValue();
        setRemoveTask.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(EditWorkoutActivity.this,
                        "Student Deleted.",Toast.LENGTH_LONG).show();
            }
        });

        setRemoveTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditWorkoutActivity.this,
                        "Something went wrong.\n" + e.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addStudent() {
        String workoutName = editTextExcerciseName.getText().toString().trim();
        Integer sets = setChoice.getValue();
        Integer reps = repChoice.getValue();
        Integer minutes = repChoice.getValue();
        Integer seconds = repChoice.getValue();

        if (TextUtils.isEmpty(workoutName)) {
            Toast.makeText(this, "You must enter a first name.", Toast.LENGTH_LONG).show();
            return;
        }


        String id = databaseWorkouts.push().getKey();
        Workout student = new Workout(workoutName, reps, sets, minutes, seconds);

        Task setValueTask = databaseWorkouts.child(id).setValue(student);

        setValueTask.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(EditWorkoutActivity.this,"Workout successfully added.",Toast.LENGTH_LONG).show();

                editTextExcerciseName.setText("");
                setChoice.setValue(0);
                repChoice.setValue(0);
                minChoice.setValue(0);
                secChoice.setValue(0);
            }
        });

        setValueTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditWorkoutActivity.this,
                        "something went wrong.\n" + e.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showReps(View view) {
        timeLayout.setVisibility(view.GONE);
        repLayout.setVisibility(view.VISIBLE);
        secChoice.setValue(0);
        minChoice.setValue(0);
    }
    public void showTime(View view) {
        repLayout.setVisibility(view.GONE);
        timeLayout.setVisibility(view.VISIBLE);
        repChoice.setValue(0);
        setChoice.setValue(0);
    }
}