package com.example.homesweathome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class WorkoutListActivity extends AppCompatActivity {

    private final String firestoreKey = "Workouts";
    private TextView Regtext;
    private ListView RegListView;

    private FirebaseFirestore db;
    private RegimenAdapter RegimenAdapter;
    private ArrayList<Regimen> RegimenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);
        Regtext = (TextView) findViewById(R.id.regimenName);
        RegListView = (ListView) findViewById(R.id.lvRegimen);
        Regtext.setText("Saved Workouts");
        db = FirebaseFirestore.getInstance();
        RegimenList = new ArrayList<Regimen>();
        RegimenAdapter = new RegimenAdapter(this, RegimenList);

        RegListView.setAdapter(RegimenAdapter);

        db.collection(firestoreKey).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

            }


        });
    }
    public void switchToEditWorkout (View view){
        Intent intent = new Intent(this, EditWorkoutActivity.class);
        startActivity(intent);
    }


    public void backToMainHome (View view){
        finish();
    }
}