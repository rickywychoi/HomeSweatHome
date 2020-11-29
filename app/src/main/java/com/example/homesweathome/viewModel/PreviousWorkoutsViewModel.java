package com.example.homesweathome.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.homesweathome.firebase.FirebaseQueryLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PreviousWorkoutsViewModel extends ViewModel {
    private static final DatabaseReference PREVWORK_REF =
            FirebaseDatabase.getInstance().getReference("PreviousWorkouts");

    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(PREVWORK_REF);

    @NonNull
    public LiveData<DataSnapshot> getDataSnapshotLiveData() {
        return liveData;
    }
}
