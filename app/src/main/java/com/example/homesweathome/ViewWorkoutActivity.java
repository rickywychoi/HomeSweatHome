package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.homesweathome.model.Exercise;
import com.example.homesweathome.model.Workout;
import com.example.homesweathome.viewModel.ExercisesViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class ViewWorkoutActivity extends AppCompatActivity {
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private TextView workoutTitleTv;
    private TextView workoutDaysTv;

    private Workout workout;
    private List<Exercise> exerciseList;
    private String workoutTitle;
    private List<DayOfWeek> dayOfWeekList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        exerciseList = new ArrayList<>();

        workout = getIntent().getParcelableExtra("workout");

        workoutTitle = workout.getName();
        dayOfWeekList = workout.getDayOfWeekList();

        workoutTitleTv = (TextView) findViewById(R.id.workout_view_title);
        workoutDaysTv = (TextView) findViewById(R.id.workout_view_days);

        workoutTitleTv.setText(workoutTitle);
        workoutDaysTv.setText(EditWorkoutActivity.getDaysOfWeekText(dayOfWeekList));

        final RecyclerView exerciseRecycler = findViewById(R.id.exercise_recycler_view);

        ExercisesViewModel viewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
        LiveData<DataSnapshot> liveData = viewModel.getDataSnapshotLiveData();

        liveData.observe(this, dataSnapshot -> {
            exerciseList.clear();
            for (DataSnapshot exerciseSnapshot : dataSnapshot.getChildren()) {
                Exercise exercise = exerciseSnapshot.getValue(Exercise.class);
                if (exercise.getUid().equals(mAuth.getUid())
                        && exercise.getWorkoutTitle().equals(workoutTitle)) {
                    exerciseList.add(exercise);
                }
            }
            exerciseRecycler.setAdapter(new ExercisesAdapter(exerciseList));
            GridLayoutManager glm = new GridLayoutManager(getApplication(), 1);
            exerciseRecycler.setLayoutManager(glm);
        });
    }
}