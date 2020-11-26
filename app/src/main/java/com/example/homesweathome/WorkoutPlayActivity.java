package com.example.homesweathome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.homesweathome.model.Exercise;
import com.example.homesweathome.model.Workout;
import com.example.homesweathome.viewModel.ExercisesViewModel;
import com.example.homesweathome.viewModel.WorkoutsViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WorkoutPlayActivity extends AppCompatActivity {
    private static int seconds = 0;
    private boolean running = true;
    private boolean wasRunning;

    private FirebaseAuth mAuth;

    private List<String> workoutTitleList;
    private List<Exercise> exerciseList;
    private List<Workout> workoutList;
    private LocalDate currentDate;

    private Workout workout;
    private String workoutTitle;

    private TextView workoutTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_play);

        mAuth = FirebaseAuth.getInstance();

        workoutList = new ArrayList<>();
        exerciseList = new ArrayList<>();

        currentDate = LocalDate.now();


        final RecyclerView workoutRecycler = findViewById(R.id.workout_play_recycler);
        final RecyclerView exerciseRecycler = findViewById(R.id.exercise_play_recycler);

        WorkoutsViewModel viewModel = new ViewModelProvider(this).get(WorkoutsViewModel.class);
        LiveData<DataSnapshot> liveData = viewModel.getDataSnapshotLiveData();

        liveData.observe(this, dataSnapshot -> {
            workoutList.clear();
            for (DataSnapshot workoutSnapshot : dataSnapshot.getChildren()) {
                Workout workout = workoutSnapshot.getValue(Workout.class);
                if (workout.getUid().equals(mAuth.getUid())) {
                    if (workout.getDayOfWeekList().contains(currentDate.getDayOfWeek())) {
                        workoutList.add(workout);
                    }
                }
            }

            workoutRecycler.setAdapter(new WorkoutTitleAdapter(workoutList));
            GridLayoutManager glm = new GridLayoutManager(getApplication(), 1);
            workoutRecycler.setLayoutManager(glm);

        });

        ExercisesViewModel viewExModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
        LiveData<DataSnapshot> liveExData = viewExModel.getDataSnapshotLiveData();

        liveExData.observe(this, dataSnapshot -> {
            exerciseList.clear();
                for (DataSnapshot exerciseSnapshot : dataSnapshot.getChildren()) {
                    Exercise exercise = exerciseSnapshot.getValue(Exercise.class);
                    if (exercise.getUid().equals(mAuth.getUid())) {
                        for (Workout w : workoutList) {
                            if (w.getName().equals(exercise.getWorkoutTitle())) {
                                exerciseList.add(exercise);
                            }
                        }
                    }
                }

            exerciseRecycler.setAdapter(new ExerciseListAdaptor(exerciseList));
            GridLayoutManager glmEx = new GridLayoutManager(getApplication(), 1);
            exerciseRecycler.setLayoutManager(glmEx);
        });

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

//        runTimer();
    }

    public static void setTime(int exerciseTimeInSeconds) { seconds = exerciseTimeInSeconds; }

    private void runTimer() {
        final TextView tvDuration = findViewById(R.id.duration);
        final android.os.Handler handler = new android.os.Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                tvDuration.setText(time);

                if (running) { seconds--; }

                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) { running = true; }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", wasRunning);
    }

    public void onClickPauseWorkout(View view) {
        Intent i = new Intent(WorkoutPlayActivity.this, WorkoutPauseActivity.class);
        startActivity(i);
    }

    public void workoutDone(View view) {
        Intent i = new Intent(WorkoutPlayActivity.this, WorkoutDoneActivity.class);
        startActivity(i);
    }
}