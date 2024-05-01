package com.example.newspaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView user5, email5, gender5, dob5;
    Button delete5;
    ImageView imageView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        user5 = findViewById(R.id.userdelete1);
        dob5 = findViewById(R.id.DoBdelete1);
        email5 = findViewById(R.id.emaildelete1);
        gender5 = findViewById(R.id.gemderdelete1);
        delete5 = findViewById(R.id.buttondelete1);
        imageView5 = findViewById(R.id.image41);
        sharedPreferences = getSharedPreferences("forLog", 0);
        user5.setText(sharedPreferences.getString("username", ""));
        dob5.setText(sharedPreferences.getString("dob", ""));
        email5.setText(sharedPreferences.getString("email", ""));
        gender5.setText(sharedPreferences.getString("gender", ""));
    }
}