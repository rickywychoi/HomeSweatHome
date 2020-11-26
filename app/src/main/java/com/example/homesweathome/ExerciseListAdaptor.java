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
import java.util.Locale;

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
        TextView exerciseWorkoutTitle;
        FrameLayout frame;
        TextView exerciseType;
        TextView exerciseDuration;
        Button exercisePrmptBtn;

        exerciseTitle = view.findViewById(R.id.exercise_list_title);
        exerciseWorkoutTitle = view.findViewById(R.id.exercise_workout_title);
        frame = view.findViewById(R.id.timer_fragment_container);
        exerciseType = view.findViewById(R.id.exercise_type);
        exerciseDuration = view.findViewById(R.id.duration);
        exercisePrmptBtn = view.findViewById(R.id.exercise_prompt_btn);

        String exTitle = exercise.getExerciseTitle();
        exerciseTitle.setText(exTitle);

        String workoutTitle = "- " + exercise.getWorkoutTitle() + " -";
        exerciseWorkoutTitle.setText(workoutTitle);

        String exType = "";

        if (!exercise.isRepBased()) {
            exType = "Elapsed Time";
            exerciseType.setText(exType);
            exercisePrmptBtn.setText("Start");
        } else if (exercise.isRepBased()){
            exType = "Repetitions";
            exerciseType.setText(exType);
            exercisePrmptBtn.setText("Next Set");
        }

        Integer rep = 0;
        Integer set = 0;
        Integer minute = 0;
        Integer second = 0;
        if (!exercise.isRepBased()) {
            minute = exercise.getMinutes();
            second = exercise.getSeconds();
            String time = String.format(Locale.getDefault(),
                    "%02d:%02d", minute, second);
            exerciseDuration.setText(time);
        } else if (exercise.isRepBased()){
            rep = exercise.getRepetitions();
            set = exercise.getSets();
            exerciseDuration.setText("Sets: " + set + "   " + "Reps: " + rep);
        }

        Integer finalMinute = minute;
        Integer finalSecond = second;

        exercisePrmptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!exercise.isRepBased()) {
                    int seconds = finalMinute * 60 + finalSecond;
//                    runTimer(seconds, exerciseDuration);
                } else {

                }
            }
        });
    }

//    private void runTimer(int seconds, TextView tvDuration) {
//        final android.os.Handler handler = new android.os.Handler();
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                int hours = seconds/3600;
//                int minutes = (seconds%3600)/60;
//                int secs = seconds%60;
//                String time = String.format(Locale.getDefault(),
//                        "%d:%02d:%02d", hours, minutes, secs);
//                tvDuration.setText(time);
//
//                if (running) { seconds--; }
//
//                handler.postDelayed(this, 1000);
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
