package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.TextView;

import com.example.homesweathome.model.Workout;

import java.time.DayOfWeek;
import java.util.List;

public class EditWorkoutActivity extends AppCompatActivity {
    private TextView workoutTitleTv;
    private TextView workoutDaysTv;
    private String workoutTitle;

    private Workout workout;
    private List<DayOfWeek> dayOfWeekList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        workout = getIntent().getParcelableExtra("workout");

        dayOfWeekList = workout.getDayOfWeekList();
        workoutTitle = workout.getName();

        workoutTitleTv = findViewById(R.id.workout_edit_title);
        workoutDaysTv = findViewById(R.id.workout_edit_days);

        workoutTitleTv.setText(workoutTitle);
        workoutDaysTv.setText(getDaysOfWeekText(dayOfWeekList));
    }

    public Workout getWorkout() {
        workout = getIntent().getParcelableExtra("workout");
        return workout;
    }

    public static String getDaysOfWeekText(List<DayOfWeek> daysList) {
        String result = "Days: ";
        for (DayOfWeek d : daysList) {
            String day = d.toString().toLowerCase().substring(0, 3);
            String formatted = day.substring(0, 1).toUpperCase() + day.substring(1, 3);
            result += (formatted + ", ");
        }
        return result.substring(0, result.length() - 2);
    }
}