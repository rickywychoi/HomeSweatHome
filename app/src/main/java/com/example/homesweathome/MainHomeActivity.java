package com.example.homesweathome;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homesweathome.model.Workout;
import com.example.homesweathome.viewModel.WorkoutsViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainHomeActivity extends AppCompatActivity {
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private DrawerLayout drawerLay;
    private ActionBarDrawerToggle toggle;

    private List<Workout> workoutList;
    private LocalDate currentDate;

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setBackgroundColor(Color.GRAY);
        drawerLay = (DrawerLayout) findViewById(R.id.navbar);
        toggle = new ActionBarDrawerToggle(this, drawerLay, R.string.navOpen, R.string.navClose);
        drawerLay.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        final RecyclerView dailyWorkoutRecycler = findViewById(R.id.daily_workout_recycler_view);

        WorkoutsViewModel viewModel = new ViewModelProvider(this).get(WorkoutsViewModel.class);
        LiveData<DataSnapshot> liveData = viewModel.getDataSnapshotLiveData();

        workoutList = new ArrayList<>();
        currentDate = LocalDate.now();

        liveData.observe(this, dataSnapshot -> {
            workoutList.clear();
            for (DataSnapshot workoutSnapshot : dataSnapshot.getChildren()) {
                Workout workout = workoutSnapshot.getValue(Workout.class);
                if (workout.getDayOfWeekList().contains(currentDate.getDayOfWeek())) {
                    workoutList.add(workout);
                }
            }
            dailyWorkoutRecycler.setAdapter(new DailyWorkoutsAdapter(workoutList));
            GridLayoutManager glm = new GridLayoutManager(getApplication(), 1);
            dailyWorkoutRecycler.setLayoutManager(glm);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        FirebaseUser user = mAuth.getCurrentUser();
        TextView accountNameItem = findViewById(R.id.account_name_item);
        TextView accountEmailItem = findViewById(R.id.account_email_item);
        if (user != null) {
            String welcomeStr = "Welcome, " + user.getUid();
            accountNameItem.setText(welcomeStr);
            accountEmailItem.setText(user.getEmail());
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.start_workout:
                Intent intent = new Intent(MainHomeActivity.this, WorkoutListActivity.class);
                startActivity(intent);
                drawerLay.closeDrawer(GravityCompat.START);
                return true;
//            case R.id.add_workout:
//                Intent intent1 = new Intent(MainHomeActivity.this, EditExerciseActivity.class);
//                startActivity(intent1);
//                drawerLay.closeDrawer(GravityCompat.START);
//                return true;
            case R.id.view_workout:
                Intent intent2 = new Intent(MainHomeActivity.this, AboutUs.class);
                startActivity(intent2);
                drawerLay.closeDrawer(GravityCompat.START);
                return true;
            case R.id.view_friends:
                Intent intent3 = new Intent(MainHomeActivity.this, ShareWithFriendsActivity.class);
                startActivity(intent3);
                drawerLay.closeDrawer(GravityCompat.START);
                return true;
            case R.id.sign_out_item:
                mAuth.signOut();
                Toast.makeText(MainHomeActivity.this, "Successfully signed out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainHomeActivity.this, login_page.class));
                drawerLay.closeDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startWorkout() {

    }

    public void viewFriends() {

    }

    public void addWorkout() {

    }

    public void viewWorkout() {
        
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