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

import com.example.homesweathome.firebase.access.ExerciseManager;
import com.example.homesweathome.firebase.access.WorkoutManager;
import com.example.homesweathome.model.Exercise;
import com.example.homesweathome.model.Workout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.time.DayOfWeek;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddExerciseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddExerciseFragment extends Fragment {
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_firebase]
    ExerciseManager exerciseManager;
    // [END declare_firebase]

    private EditText editTextExcerciseName;
    private Button openRepBasedBtn;
    private Button openTimeBasedBtn;
    private Button buttonAddWorkoutreps;
    private Button buttonAddWorkouttime;
    private NumberPicker setChoice;
    private NumberPicker repChoice;
    private NumberPicker minChoice;
    private NumberPicker secChoice;
    private LinearLayout repLayout;
    private LinearLayout timeLayout;

    private Workout workout;
    private List<DayOfWeek> dayOfWeekList;
    private String workoutTitle;

    private boolean isRepBased;

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

        // [START initialize_firebase]
        // Initialize Firebase Realtime Database
        exerciseManager = new ExerciseManager();
        // [END initialize_firebase]

        workout = ((EditWorkoutActivity) getActivity()).getWorkout();
        dayOfWeekList = workout.getDayOfWeekList();
        workoutTitle = workout.getName();

        timeLayout = view.findViewById(R.id.timeLayout);
        repLayout = view.findViewById(R.id.repLayout);
        setChoice = view.findViewById(R.id.sets);
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

        editTextExcerciseName = (EditText) view.findViewById(R.id.editTextExcerciseName);
        openRepBasedBtn = (Button) view.findViewById(R.id.open_rep_based_btn);
        openTimeBasedBtn = (Button) view.findViewById(R.id.open_time_based_btn);
        buttonAddWorkoutreps = (Button) view.findViewById(R.id.buttonAddWorkoutReps);
        buttonAddWorkouttime = (Button) view.findViewById(R.id.buttonAddWorkouttime);
        repLayout = (LinearLayout) view.findViewById(R.id.repLayout);
        timeLayout = (LinearLayout) view.findViewById(R.id.timeLayout);

        openRepBasedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repLayout.setVisibility(View.VISIBLE);
                timeLayout.setVisibility(View.GONE);
                minChoice.setValue(0);
                secChoice.setValue(0);
                isRepBased = true;
            }
        });

        openTimeBasedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repLayout.setVisibility(View.GONE);
                timeLayout.setVisibility(View.VISIBLE);
                repChoice.setValue(0);
                setChoice.setValue(0);
                isRepBased = false;
            }
        });

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
        Integer minutes = minChoice.getValue();
        Integer seconds = secChoice.getValue();

        if (TextUtils.isEmpty(exerciseName)) {
            Toast.makeText(getActivity(), "Exercise name cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        Exercise exercise = new Exercise(mAuth.getUid(), workoutTitle, exerciseName, reps, sets, minutes, seconds, isRepBased);
        exerciseManager.add(exercise).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getActivity(), "Successfully added exercise.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Failed to add exercise.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}