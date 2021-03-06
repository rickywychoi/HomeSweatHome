package com.example.homesweathome.firebase.access;

import com.example.homesweathome.model.Exercise;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExerciseManager {

    private static final String EXERCISE_REF = "exercises";
    private DatabaseReference database;

    public ExerciseManager() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    public Task<Void> add(Exercise toAdd) {
        String id = toAdd.toString();
        return database.child(EXERCISE_REF).child(id).setValue(toAdd);
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

    public Task<Void> delete(Exercise toDelete) {
        String id = toDelete.toString();
        return database.child(EXERCISE_REF).child(id).removeValue();
    }
}
