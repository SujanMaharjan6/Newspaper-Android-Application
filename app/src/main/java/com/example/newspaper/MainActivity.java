package com.example.newspaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button login, signup;
    EditText Username, Password;
    DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox = findViewById(R.id.checkbox);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        Username = findViewById(R.id.userName6);
        Password = findViewById(R.id.password6);
        databaseHelper = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("forLog", 0);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (sharedPreferences.getBoolean("rememberMe", false)) {
            startActivity(new Intent(MainActivity.this, Fragment.class));
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uservalue = Username.getText().toString();
                String passvalue = Password.getText().toString();
                if (databaseHelper.login(uservalue, passvalue)) {


                    if (checkBox.isChecked()) {
                        sharedPreferences.edit().putBoolean("rememberMe", true).apply();
                    }
                    startActivity(new Intent(MainActivity.this, Fragment.class));
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect" + uservalue, Toast.LENGTH_LONG).show();
                }
                Collect collect = databaseHelper.login1(uservalue, passvalue);
                sharedPreferences.edit().putString("username", collect.Username).apply();
                sharedPreferences.edit().putString("password", collect.Password).apply();
                sharedPreferences.edit().putString("id", collect.Id).apply();
                sharedPreferences.edit().putString("email", collect.Email).apply();
                sharedPreferences.edit().putString("dob", collect.DoB).apply();
                sharedPreferences.edit().putString("gender", collect.Gender).apply();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, signup.class));
            }
        });
    }


}