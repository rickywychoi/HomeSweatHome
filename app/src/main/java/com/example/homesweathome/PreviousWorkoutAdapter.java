package com.example.homesweathome;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homesweathome.model.PreviousWorkout;
import com.example.homesweathome.model.Workout;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

public class PreviousWorkoutAdapter extends RecyclerView.Adapter<PreviousWorkoutAdapter.ViewHolder> {
    private List<PreviousWorkout> prevWorks;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    public PreviousWorkoutAdapter(List<PreviousWorkout> workoutList) {
        this.prevWorks = workoutList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.previous_workout_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final View view = holder.view;
        Context ctx = view.getContext();

        PreviousWorkout Pworkout = prevWorks.get(position);

        TextView workoutTitleTv = view.findViewById(R.id.prevworkout_title);
        TextView workoutDate = view.findViewById(R.id.prevworkout_item_date);

        String workoutTitle = Pworkout.getPrevName();
        Date prevDate = Pworkout.getPlayedDate();

        workoutTitleTv.setText(workoutTitle);
        workoutDate.setText(prevDate.toString());

    }

    @Override
    public int getItemCount() {
        return prevWorks.size();
    }
}
