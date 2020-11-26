package com.example.homesweathome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.homesweathome.firebase.access.PreviousWorkoutManager;
import com.example.homesweathome.firebase.access.WorkoutManager;
import com.example.homesweathome.model.PreviousWorkout;
import com.example.homesweathome.model.Workout;
import com.example.homesweathome.viewModel.PreviousWorkoutsViewModel;
import com.example.homesweathome.viewModel.WorkoutsViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class WorkoutHistoryActivity extends AppCompatActivity {

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]


    PreviousWorkoutManager pmanager;

    private List<PreviousWorkout> prevworkoutList;

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_history);
        linearLayout = findViewById(R.id.prevlinearLayout);
        linearLayout.setBackgroundColor(Color.WHITE);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        prevworkoutList = new ArrayList<>();

        final RecyclerView workoutRecycler = findViewById(R.id.prevworkouts_recycler_view);

        PreviousWorkoutsViewModel viewModel = new ViewModelProvider(this).get(PreviousWorkoutsViewModel.class);
        LiveData<DataSnapshot> liveData = viewModel.getDataSnapshotLiveData();

        liveData.observe(this, dataSnapshot -> {
            prevworkoutList.clear();
            for (DataSnapshot workoutSnapshot : dataSnapshot.getChildren()) {
                PreviousWorkout workout = workoutSnapshot.getValue(PreviousWorkout.class);

                if (workout.getUid().equals(mAuth.getUid())) {
                    prevworkoutList.add(workout);
                }

            }
            workoutRecycler.setAdapter(new PreviousWorkoutAdapter(prevworkoutList));
            GridLayoutManager glm = new GridLayoutManager(getApplication(), 1);
            workoutRecycler.setLayoutManager(glm);
        });



        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START initialize_firebase]
        // Initialize Firebase Realtime Database
        pmanager = new PreviousWorkoutManager();
        // [END initialize_firebase]


        Button testPrevButton = findViewById(R.id.prevTest);

        testPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevworkoutName = "test";
                    PreviousWorkout prev = new PreviousWorkout(mAuth.getUid(), prevworkoutName);
                    pmanager.add(prev).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(
                                    WorkoutHistoryActivity.this,
                                    "Successfully added workout.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(
                                    WorkoutHistoryActivity.this,
                                    "Failed to add workout.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });




    }



    public void switchToAddWorkout(View view) {
        Intent intent = new Intent(this, AddWorkoutActivity.class);
        startActivity(intent);
    }

    public void backToMainHome(View view) {
        Intent intent = new Intent(this, MainHomeActivity.class);
        startActivity(intent);
    }
}