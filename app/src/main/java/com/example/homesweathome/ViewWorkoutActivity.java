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
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homesweathome.model.Exercise;
import com.example.homesweathome.model.Workout;
import com.example.homesweathome.viewModel.ExercisesViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class ViewWorkoutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private TextView workoutTitleTv;
    private TextView workoutDaysTv;

    private Workout workout;
    private List<Exercise> exerciseList;
    private String workoutTitle;
    private List<DayOfWeek> dayOfWeekList;

    private DrawerLayout drawerLay;
    private ActionBarDrawerToggle toggle;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        exerciseList = new ArrayList<>();

        workout = getIntent().getParcelableExtra("workout");

        workoutTitle = workout.getName();
        dayOfWeekList = workout.getDayOfWeekList();

        workoutTitleTv = (TextView) findViewById(R.id.workout_view_title);
        workoutDaysTv = (TextView) findViewById(R.id.workout_view_days);

        workoutTitleTv.setText(workoutTitle);
        workoutDaysTv.setText(EditWorkoutActivity.getDaysOfWeekText(dayOfWeekList));

        linearLayout = findViewById(R.id.layoutLinear2);

        drawerLay = findViewById(R.id.nav_bar2);
        toggle = new ActionBarDrawerToggle(this, drawerLay, R.string.navOpen, R.string.navClose);
        drawerLay.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);

        final RecyclerView exerciseRecycler = findViewById(R.id.exercise_recycler_view);

        ExercisesViewModel viewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
        LiveData<DataSnapshot> liveData = viewModel.getDataSnapshotLiveData();

        liveData.observe(this, dataSnapshot -> {
            exerciseList.clear();
            for (DataSnapshot exerciseSnapshot : dataSnapshot.getChildren()) {
                Exercise exercise = exerciseSnapshot.getValue(Exercise.class);
                if (exercise.getUid().equals(mAuth.getUid())
                        && exercise.getWorkoutTitle().equals(workoutTitle)) {
                    exerciseList.add(exercise);
                }
            }
            exerciseRecycler.setAdapter(new ExercisesAdapter(exerciseList));
            GridLayoutManager glm = new GridLayoutManager(getApplication(), 1);
            exerciseRecycler.setLayoutManager(glm);
        });
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
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Intent intent = null;

        switch(id) {
            case R.id.start_workout:
                intent = new Intent(ViewWorkoutActivity.this, CountDownBeforeWorkoutActivity.class);
                break;
            case R.id.view_today_workout:
                intent = new Intent(ViewWorkoutActivity.this, MainHomeActivity.class);
                break;
            case R.id.view_all_workout:
                intent = new Intent(ViewWorkoutActivity.this, WorkoutListActivity.class);
                break;
            case R.id.sign_out_item:
                mAuth.signOut();
                Toast.makeText(ViewWorkoutActivity.this, "Successfully signed out", Toast.LENGTH_SHORT).show();
                intent = new Intent(ViewWorkoutActivity.this, login_page.class);
                break;
        }
        startActivity(intent);

        DrawerLayout drawer = findViewById(R.id.nav_bar2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}