package com.example.homesweathome.firebase.access;

import com.example.homesweathome.model.Workout;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WorkoutManager {
    private static final String WORKOUT_REF = "workouts";
    private DatabaseReference database;

    public WorkoutManager() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    public Task<Void> add(Workout toAdd) {
        String id =  toAdd.toString();
        return database.child(WORKOUT_REF).child(id).setValue(toAdd);
    }

//    public Task<Void> merge(Workout toMerge) {
//        String id = toMerge.toString();
//        HashMap<String, Object> updates = new HashMap<>();
//        updates.put("condition", toMerge.getCondition());
//        updates.put("systolic", toMerge.getSystolic());
//        updates.put("diastolic", toMerge.getDiastolic());
//        return database.child(WORKOUT_REF).child(id).updateChildren(updates);
//    }
//
//    public Task<Void> delete(Record toDelete) {
//        String id =  toDelete.getRecordFor() + "-" + toDelete.getDate().getTime();
//        return database.child(WORKOUT_REF).child(id).removeValue();
//    }
}
