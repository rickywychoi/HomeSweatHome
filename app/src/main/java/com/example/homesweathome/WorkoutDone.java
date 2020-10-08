package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WorkoutDone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_done);
    }

    public void onClickShare(View view) {
        Intent i = new Intent(WorkoutDone.this, ShareWithFriends.class);
        startActivity(i);
    }
}