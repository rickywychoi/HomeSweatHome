package com.example.homesweathome;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class WorkoutAdapter extends ArrayAdapter<Workout> {
    private Activity context;
    private List<Workout> workoutList;

    public WorkoutAdapter(Activity context, List<Workout> workoutList) {
        super(context, R.layout.activity_new_workout_list, workoutList);
        this.context = context;
        this.workoutList = workoutList;
    }

    public WorkoutAdapter(Context context, int resource, List<Workout> objects, Activity context1, List<Workout> workoutList) {
        super(context, resource, objects);
        this.context = context1;
        this.workoutList = workoutList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_new_workout_list, null, true);

        TextView workoutName = listViewItem.findViewById(R.id.textViewWorkoutName);
        TextView workoutDetails = listViewItem.findViewById(R.id.textViewWorkoutDetails);

        Workout work = workoutList.get(position);
        workoutName.setText(work.getWorkoutTitle());
        if(work.sets >= 1) {
            workoutDetails.setText("Sets: " + work.sets + " Repetitions: " + work.repetitions);
        }
        if(work.seconds >= 1 || work.minutes >= 1) {
            workoutDetails.setText("Minutes: " + work.minutes + " Seconds: " + work.seconds);
        }

        return listViewItem;
    }

}
