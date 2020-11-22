package com.example.homesweathome.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.homesweathome.firebase.FirebaseQueryLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WorkoutViewModel extends ViewModel {
    private static final DatabaseReference WORKOUTS_REF = FirebaseDatabase.getInstance().getReference("workouts");;

    private FirebaseQueryLiveData liveData;

    public WorkoutViewModel() {
    }

    @NonNull
    public LiveData<DataSnapshot> getDataSnapshotLiveData(String id) {
        if (liveData == null) {
            liveData = new FirebaseQueryLiveData(WORKOUTS_REF.child(id));
        }
        return liveData;
    }
}
