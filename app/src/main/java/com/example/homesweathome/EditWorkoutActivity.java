package com.example.homesweathome;

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

import com.example.homesweathome.model.Exercise;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditWorkoutActivity extends AppCompatActivity {
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

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
    List<Exercise> WorkoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_edit_workout);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

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
        WorkoutList = new ArrayList<Exercise>();

//        lvStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Workout work = WorkoutList.get(position);
//
//                showUpdateDialog(work.getWorkoutTitle(),
//                        work.getRepetitions(),
//                        work.getSets(),
//                        work.getMinutes(), work.getSeconds());
//
//                return false;
//            }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseWorkouts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                WorkoutList.clear();
                for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()) {
                    Exercise work = studentSnapshot.getValue(Exercise.class);
                    WorkoutList.add(work);
                }
//
//                WorkoutAdapter adapter = new WorkoutAdapter(EditWorkoutActivity.this, WorkoutList);
//                lvStudents.setAdapter(adapter);
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


//    private void updateStudent(String workoutTitle, Integer rep, Integer set, Integer min, Integer sec) {
//        DatabaseReference dbRef = databaseWorkouts.child(workoutTitle);
//
//        Workout work = new Workout(workoutTitle,rep,set,min, sec);
//
//        Task setValueTask = dbRef.setValue(work);
//
//        setValueTask.addOnSuccessListener(new OnSuccessListener() {
//            @Override
//            public void onSuccess(Object o) {
//                Toast.makeText(EditWorkoutActivity.this,
//                        "Workout Updated.",Toast.LENGTH_LONG).show();
//            }
//        });
//
//        setValueTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(EditWorkoutActivity.this,
//                        "Something went wrong.\n" + e.toString(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

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
        Exercise student = new Exercise(mAuth.getUid(), workoutName, reps, sets, minutes, seconds);

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

//    private void showUpdateDialog(final String workoutName, Integer rep, Integer set, Integer minute, Integer sec) {
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//
//        LayoutInflater inflater = getLayoutInflater();
//
//        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
//        dialogBuilder.setView(dialogView);
//
//        final EditText editTextExcerciseName = dialogView.findViewById(R.id.editTextExcerciseName);
//        editTextExcerciseName.setText(firstName);
//
//        final EditText editTextLastName = dialogView.findViewById(R.id.editTextLastName);
//        editTextLastName.setText(lastName);
//
//        final Spinner spinnerSchool = dialogView.findViewById(R.id.spinnerSchool);
//        spinnerSchool.setSelection(((ArrayAdapter<String>)spinnerSchool.getAdapter()).getPosition(school));
//
//        final Button btnUpdate = dialogView.findViewById(R.id.btnUpdate);
//
//        dialogBuilder.setTitle("Update Student " + firstName + " " + lastName);
//
//        final AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String firstName = editTextExcerciseName.getText().toString().trim();
//                String lastName = editTextLastName.getText().toString().trim();
//                String school = spinnerSchool.getSelectedItem().toString().trim();
//
//                if (TextUtils.isEmpty(firstName)) {
//                    editTextExcerciseName.setError("First Name is required");
//                    return;
//                } else if (TextUtils.isEmpty(lastName)) {
//                    editTextLastName.setError("Last Name is required");
//                    return;
//                }
//
//                updateStudent(studentId, firstName, lastName, school);
//
//                alertDialog.dismiss();
//            }
//        });
//
//        final Button btnDelete = dialogView.findViewById(R.id.btnDelete);
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteStudent(studentId);
//
//                alertDialog.dismiss();
//            }
//        });
//
//    }



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