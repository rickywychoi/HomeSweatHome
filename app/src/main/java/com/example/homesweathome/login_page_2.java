package com.example.homesweathome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login_page_2 extends AppCompatActivity {
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_2);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    public void loginSubmit(View view) {

        if(username.getText().toString().equals("admin") && password.getText().toString().equals("1234")) {

            Intent intent = new Intent(this, login_page_3.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }
    }
}