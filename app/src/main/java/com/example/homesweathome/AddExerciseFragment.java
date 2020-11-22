package com.example.homesweathome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.homesweathome.model.Exercise;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddExerciseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddExerciseFragment extends Fragment {
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private EditText editTextExcerciseName;
    private Button buttonAddWorkoutreps;
    private Button buttonAddWorkouttime;
    private NumberPicker setChoice;
    private NumberPicker repChoice;
    private NumberPicker minChoice;
    private NumberPicker secChoice;
    private LinearLayout repLayout;
    private LinearLayout timeLayout;

    public AddExerciseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddExerciseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddExerciseFragment newInstance() {
        AddExerciseFragment fragment = new AddExerciseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_exercise, container, false);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        timeLayout = view.findViewById(R.id.timeLayout);
        repLayout = view.findViewById(R.id.repLayout);
        setChoice = view.findViewById(R.id.new_sets);
        setChoice.setMinValue(0);
        setChoice.setMaxValue(10);
        repChoice = view.findViewById(R.id.repetitions);
        repChoice.setMinValue(0);
        repChoice.setMaxValue(50);
        minChoice = view.findViewById(R.id.minutes);
        minChoice.setMinValue(0);
        minChoice.setMaxValue(60);
        secChoice = view.findViewById(R.id.seconds);
        secChoice.setMinValue(0);
        secChoice.setMaxValue(59);

        editTextExcerciseName = view.findViewById(R.id.editTextExcerciseName);
        buttonAddWorkoutreps = view.findViewById(R.id.buttonAddWorkoutReps);
        buttonAddWorkouttime = view.findViewById(R.id.buttonAddWorkouttime);

        buttonAddWorkoutreps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercise();
            }
        });

        buttonAddWorkouttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercise();
            }
        });

        return view;
    }

    private void addExercise() {
        String exerciseName = editTextExcerciseName.getText().toString().trim();
        Integer sets = setChoice.getValue();
        Integer reps = repChoice.getValue();
        Integer minutes = repChoice.getValue();
        Integer seconds = repChoice.getValue();

        if (TextUtils.isEmpty(exerciseName)) {
            Toast.makeText(getActivity(), "Exercise name cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }


//        String id = databaseWorkouts.push().getKey();
//        Exercise student = new Exercise(mAuth.getUid(), exerciseName, reps, sets, minutes, seconds);
//
//        Task setValueTask = databaseWorkouts.child(id).setValue(student);
//
//        setValueTask.addOnSuccessListener(new OnSuccessListener() {
//            @Override
//            public void onSuccess(Object o) {
//                Toast.makeText(EditExerciseActivity.this,"Workout successfully added.",Toast.LENGTH_LONG).show();
//
//                editTextExcerciseName.setText("");
//                setChoice.setValue(0);
//                repChoice.setValue(0);
//                minChoice.setValue(0);
//                secChoice.setValue(0);
//            }
//        });
//
//        setValueTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(EditExerciseActivity.this,
//                        "something went wrong.\n" + e.toString(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}