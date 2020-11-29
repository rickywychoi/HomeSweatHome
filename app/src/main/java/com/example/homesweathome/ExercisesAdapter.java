package com.example.homesweathome;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homesweathome.firebase.access.ExerciseManager;
import com.example.homesweathome.model.Exercise;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> {
    private List<Exercise> exerciseList;

    // [START declare_firebase]
    ExerciseManager exerciseManager;
    // [END declare_firebase]

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    public ExercisesAdapter(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final View view = holder.view;

        // [START initialize_firebase]
        // Initialize Firebase Realtime Database
        exerciseManager = new ExerciseManager();
        // [END initialize_firebase]

        Exercise exercise = exerciseList.get(position);

        TextView exerciseTitleTv = view.findViewById(R.id.exercise_title);
        TextView exerciseDetailsTv = view.findViewById(R.id.exercise_details);
        Button editBtn = view.findViewById(R.id.exercise_edit_btn);
        Button deleteBtn = view.findViewById(R.id.exercise_delete_btn);
        Button saveBtn = view.findViewById(R.id.exercise_edit_save_btn);

        String exerciseTitle = exercise.getExerciseTitle();
        String details = "";

        if (exercise.isRepBased()) {
            details += ("reps: " + exercise.getRepetitions() + " sets: " + exercise.getSets());
        } else {
            details += ("minutes: " + exercise.getMinutes() + " seconds: " + exercise.getSeconds());
        }

        exerciseTitleTv.setText(exerciseTitle);
        exerciseDetailsTv.setText(details);

        LinearLayout notEditView = view.findViewById(R.id.exercise_not_edit_view);
        LinearLayout editView = view.findViewById(R.id.exercise_edit_view);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editView.setVisibility(View.VISIBLE);
                notEditView.setVisibility(View.GONE);
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseManager.delete(exercise).addOnCompleteListener(task -> notifyItemRemoved(position));
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editView.setVisibility(View.GONE);
                notEditView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
