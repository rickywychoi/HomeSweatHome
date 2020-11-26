package com.example.homesweathome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homesweathome.model.Exercise;

import java.util.List;

public class ExerciseListAdaptor extends RecyclerView.Adapter<ExerciseListAdaptor.ViewHolder> {
    private List<Exercise> exerciseList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    public ExerciseListAdaptor(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.play_exercise_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final View view = holder.view;
        Context ctx = view.getContext();

        Exercise exercise = exerciseList.get(position);

        TextView exerciseTitle;
        FrameLayout frame;
        TextView exerciseType;
        TextView exerciseDuration;
        Button nextExercise;

        exerciseTitle = view.findViewById(R.id.exercise_list_title);
        frame = view.findViewById(R.id.timer_fragment_container);
        exerciseType = view.findViewById(R.id.exercise_type);
        exerciseDuration = view.findViewById(R.id.duration);

        String exTitle = exercise.getExerciseTitle();
        exerciseTitle.setText(exTitle);

        String exType = "";

        if (!exercise.isRepBased()) {
            exType = "Elapsed Time";
            exerciseType.setText(exType);
        } else if (exercise.isRepBased()){
            exType = "Elapsed Repetitions";
            exerciseType.setText(exType);
        }

        Integer rep;
        Integer set;
        Integer minute;
        Integer second;
        if (!exercise.isRepBased()) {
            minute = exercise.getMinutes();
            second = exercise.getSeconds();
            exerciseDuration.setText(minute + ":" + second);
        } else if (exercise.isRepBased()){
            rep = exercise.getRepetitions();
            set = exercise.getSets();
            exerciseDuration.setText("Sets: " + set + "   " + "Reps: " + rep);
        }




    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
