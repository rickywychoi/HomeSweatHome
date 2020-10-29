package com.example.homesweathome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainHomeActivity extends AppCompatActivity {
    // Button and DatabaseReference for firebase connection test
    Button firebaseTestBtn;
    DatabaseReference database;
    //

    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setBackgroundColor(Color.GRAY);

        //
        // Test if firebase is properly connected
        //
        database = FirebaseDatabase.getInstance().getReference("test");
        firebaseTestBtn = (Button) findViewById(R.id.firebaseBtn);
        firebaseTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = database.push().getKey();
                Task setValueTask = database.child(id).setValue("This is Test - " + new Date());
                setValueTask.addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(MainHomeActivity.this,
                                "Successfully connected to firebase", Toast.LENGTH_SHORT).show();
                    }
                });
                setValueTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainHomeActivity.this,
                                "Something went wrong...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //
        // end of firebase test
        //
    }

    public void switchToWorkoutList(View view) {
        Intent intent = new Intent(MainHomeActivity.this, WorkoutListActivity.class);
        startActivity(intent);
    }

    public void switchToWorkoutPlay(View view) {
        WorkoutPlayActivity.setTime(15 * 60);
        Intent intent = new Intent(MainHomeActivity.this, CountDownBeforeWorkoutActivity.class);
        startActivity(intent);
    }
}