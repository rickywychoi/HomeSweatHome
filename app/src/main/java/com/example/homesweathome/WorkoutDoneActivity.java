package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WorkoutDoneActivity extends AppCompatActivity {
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_done);
        myDialog = new Dialog(this);

    }

    public void onClickShare(View view) {

    }
}