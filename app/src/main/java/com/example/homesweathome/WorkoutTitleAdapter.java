package com.example.homesweathome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homesweathome.model.Workout;

import org.w3c.dom.Text;

import java.util.List;

public class WorkoutTitleAdapter extends RecyclerView.Adapter<WorkoutTitleAdapter.ViewHolder>{
    private List<Workout> workoutList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    public WorkoutTitleAdapter(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.play_workout_title, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final View view = holder.view;
        Context ctx = view.getContext();

        TextView workoutDayTitle;
        ImageView checkmarkImg;

        workoutDayTitle = view.findViewById(R.id.workout_day_title);
        checkmarkImg = view.findViewById(R.id.checkmark);

        Workout workout = workoutList.get(position);

        String workoutTitle = workout.getName();

        workoutDayTitle.setText(workoutTitle);

    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

}
