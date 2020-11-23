package com.example.homesweathome.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.homesweathome.firebase.FirebaseQueryLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExercisesViewModel extends ViewModel {
    private static final DatabaseReference EXERCISE_REF =
            FirebaseDatabase.getInstance().getReference("exercises");

    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(EXERCISE_REF);

    @NonNull
    public LiveData<DataSnapshot> getDataSnapshotLiveData() {
        return liveData;
    }
}
