package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.homesweathome.model.Workout;
import com.example.homesweathome.viewModel.WorkoutViewModel;
import com.example.homesweathome.viewModel.WorkoutsViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class WorkoutListActivity extends AppCompatActivity {
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private List<Workout> workoutList;

    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);
        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setBackgroundColor(Color.GRAY);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        workoutList = new ArrayList<>();

        final RecyclerView workoutRecycler = findViewById(R.id.workouts_recycler_view);

        WorkoutsViewModel viewModel = new ViewModelProvider(this).get(WorkoutsViewModel.class);
        LiveData<DataSnapshot> liveData = viewModel.getDataSnapshotLiveData();

        liveData.observe(this, dataSnapshot -> {
            workoutList.clear();
            for (DataSnapshot workoutSnapshot : dataSnapshot.getChildren()) {
                Workout workout = workoutSnapshot.getValue(Workout.class);

                if (workout.getUid().equals(mAuth.getUid())) {
                    workoutList.add(workout);
                }

                workoutRecycler.setAdapter(new WorkoutsAdapter(workoutList));
                GridLayoutManager glm = new GridLayoutManager(getApplication(), 1);
                workoutRecycler.setLayoutManager(glm);
                }
        });

    }

    public void switchToAddWorkout(View view) {
        Intent intent = new Intent(this, AddWorkoutActivity.class);
        startActivity(intent);
    }

    public void backToMainHome(View view) {
        finish();
    }
}