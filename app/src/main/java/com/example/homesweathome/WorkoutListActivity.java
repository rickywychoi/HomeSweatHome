package com.example.homesweathome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.homesweathome.model.Workout;
import com.example.homesweathome.viewModel.WorkoutsViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private List<Workout> workoutList;

    private DrawerLayout drawerLay;
    private ActionBarDrawerToggle toggle;

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);
        linearLayout = findViewById(R.id.linearLayout);

        drawerLay = (DrawerLayout) findViewById(R.id.nav_bar_3);
        toggle = new ActionBarDrawerToggle(this, drawerLay, R.string.navOpen, R.string.navClose);
        drawerLay.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view3);
        navigationView.setNavigationItemSelectedListener(this);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        workoutList = new ArrayList<>();

        final RecyclerView workoutRecycler = findViewById(R.id.workouts_recycler_view);

        WorkoutsViewModel viewModel = new ViewModelProvider(this).get(WorkoutsViewModel.class);
        LiveData<DataSnapshot> liveData = viewModel.getDataSnapshotLiveData();

        liveData.observe(this, dataSnapshot -> {
            workoutList.clear();
            for (DataSnapshot workoutSnapshot : dataSnapshot.getChildren()) {
                Workout workout = workoutSnapshot.getValue(Workout.class);

                if (workout.getUid().equals(mAuth.getUid())) {
                    workoutList.add(workout);
                }

            }
            workoutRecycler.setAdapter(new WorkoutsAdapter(workoutList));
            GridLayoutManager glm = new GridLayoutManager(getApplication(), 1);
            workoutRecycler.setLayoutManager(glm);
        });



    }

    public void switchToWorkoutHistory(View view) {
        Intent intent = new Intent(this, WorkoutHistoryActivity.class);
        startActivity(intent);
    }

    public void switchToAddWorkout(View view) {
        Intent intent = new Intent(this, AddWorkoutActivity.class);
        startActivity(intent);
    }

    public void backToMainHome(View view) {
        Intent intent = new Intent(this, MainHomeActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Intent intent = null;

        switch(id) {
            case R.id.start_workout:
                intent = new Intent(WorkoutListActivity.this, CountDownBeforeWorkoutActivity.class);
                break;
            case R.id.view_today_workout:
                intent = new Intent(WorkoutListActivity.this, MainHomeActivity.class);
                break;
            case R.id.view_all_workout:
                intent = new Intent(WorkoutListActivity.this, WorkoutListActivity.class);
                break;
            case R.id.sign_out_item:
                mAuth.signOut();
                Toast.makeText(WorkoutListActivity.this, "Successfully signed out", Toast.LENGTH_SHORT).show();
                intent = new Intent(WorkoutListActivity.this, login_page.class);
                break;
        }
        startActivity(intent);

        DrawerLayout drawer = findViewById(R.id.nav_bar_3);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

