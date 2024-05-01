package com.example.newspaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Retrieve_Database extends AppCompatActivity {

    LinearLayout container;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve__database);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        container = findViewById(R.id.container);
        databaseHelper = new DatabaseHelper(this);
        populate();

    }

    public void populate() {
        container.removeAllViews();
        ArrayList<Collect> list = databaseHelper.getAllUsers();
        for (int i = 0; i < list.size(); i++) {
            final Collect collect = list.get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.list_of_users, null);
            ImageView imageView = view.findViewById(R.id.image2);
            TextView username5 = view.findViewById(R.id.username1);
            TextView gender5 = view.findViewById(R.id.gender1);
            TextView email5 = view.findViewById(R.id.email1);
            TextView dob5 = view.findViewById(R.id.date1);
            username5.setText(collect.Username);
            gender5.setText(collect.Gender);
            email5.setText(collect.Email);
            dob5.setText(collect.DoB);
            if (collect.Image != null) {
                imageView.setImageBitmap(signup.getBitmap(collect.Image));
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Retrieve_Database.this, ForDelete.class);
                    intent.putExtra("id", collect.Id);
                    startActivity(intent);
                }
            });
            container.addView(view);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        populate();
    }
}