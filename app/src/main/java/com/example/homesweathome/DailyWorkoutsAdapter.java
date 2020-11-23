package com.example.homesweathome;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homesweathome.model.Workout;

import java.util.List;

public class DailyWorkoutsAdapter extends RecyclerView.Adapter<DailyWorkoutsAdapter.ViewHolder> {
    private List<Workout> workoutList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    public DailyWorkoutsAdapter(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_workout_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final View view = holder.view;
        Context ctx = view.getContext();

        Workout workout = workoutList.get(position);

        TextView workoutTitleTv = view.findViewById(R.id.daily_workout_title);
        Button viewBtn = view.findViewById(R.id.daily_workout_view_btn);

        String workoutTitle = workout.getName();

        workoutTitleTv.setText(workoutTitle);
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, ViewWorkoutActivity.class);
                i.putExtra("workout", workout);
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }
}
