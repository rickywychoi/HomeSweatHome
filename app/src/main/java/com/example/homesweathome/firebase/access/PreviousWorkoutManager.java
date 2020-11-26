package com.example.homesweathome.firebase.access;

import com.example.homesweathome.model.PreviousWorkout;
import com.example.homesweathome.model.Workout;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PreviousWorkoutManager {
    private static final String PREVWORK_REF = "PreviousWorkouts";
    private DatabaseReference database;

    public PreviousWorkoutManager() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    public Task<Void> add(PreviousWorkout toAdd) {
        String id = toAdd.toString();
        return database.child(PREVWORK_REF).child(id).setValue(toAdd);
    }
}
