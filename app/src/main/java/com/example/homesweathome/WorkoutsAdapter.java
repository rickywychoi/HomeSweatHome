package com.example.homesweathome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homesweathome.firebase.access.WorkoutManager;
import com.example.homesweathome.model.Workout;

import java.time.DayOfWeek;
import java.util.List;

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.ViewHolder> {
    private List<Workout> workoutList;
    // [START declare_firebase]
    WorkoutManager workoutManager;
    // [END declare_firebase]

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    public WorkoutsAdapter(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final View view = holder.view;
        Context ctx = view.getContext();

        // [START initialize_firebase]
        // Initialize Firebase Realtime Database
        workoutManager = new WorkoutManager();
        // [END initialize_firebase]


        TextView workoutTitleTv;
        TextView workoutDaysTv;
        Button viewBtn;
        Button editBtn;
        Button deleteBtn;

        workoutTitleTv = view.findViewById(R.id.workout_title);
        workoutDaysTv = view.findViewById(R.id.workout_item_days);
        viewBtn = view.findViewById(R.id.workout_view_btn);
        editBtn = view.findViewById(R.id.workout_edit_btn);
        deleteBtn = view.findViewById(R.id.workout_delete_btn);

        Workout workout = workoutList.get(position);

        String workoutTitle = workout.getName();
        List<DayOfWeek> dayOfWeeks = workout.getDayOfWeekList();

        workoutTitleTv.setText(workoutTitle);
        workoutDaysTv.setText(getDaysOfWeekText(dayOfWeeks));
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, ViewWorkoutActivity.class);
                i.putExtra("workout", workout);
                ctx.startActivity(i);
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, EditWorkoutActivity.class);
                i.putExtra("workout", workout);
                ctx.startActivity(i);
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutManager.delete(workout).addOnCompleteListener(task -> notifyItemRemoved(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    String getDaysOfWeekText(List<DayOfWeek> daysList) {
        String result = "";
        for (DayOfWeek d : daysList) {
            String day = d.toString().toLowerCase().substring(0, 3);
            String formatted = day.substring(0, 1).toUpperCase() + day.substring(1, 3);
            result += (formatted + " ");
        }
        return result.substring(0, result.length() - 1);
    }
}
