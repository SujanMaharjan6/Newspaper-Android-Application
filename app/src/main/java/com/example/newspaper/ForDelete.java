package com.example.newspaper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ForDelete extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView user, email, gender, dob;
    String id;
    Button delete, update;
    ImageView imageView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.for_delete);
        databaseHelper = new DatabaseHelper(this);
        id = getIntent().getStringExtra("id");
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        user = findViewById(R.id.userdelete);
        dob = findViewById(R.id.DoBdelete);
        email = findViewById(R.id.emaildelete);
        gender = findViewById(R.id.gemderdelete);
        delete = findViewById(R.id.buttondelete);
        update = findViewById(R.id.buttonupdate);
        imageView = findViewById(R.id.image4);
        linearLayout = findViewById(R.id.home2);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForDelete.this, signup.class);
                intent.putExtra("id", Integer.parseInt(id));
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertdialogue();
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForDelete.this, Fragment.class));
            }
        });
    }

    public void display() {
        Collect collect = databaseHelper.getUser(id);
        user.setText(collect.Username);
        dob.setText(collect.DoB);
        email.setText(collect.Email);
        gender.setText(collect.Gender);
        if (collect.Image != null) {
            imageView.setImageBitmap(signup.getBitmap(collect.Image));
        }
    }

    public void showAlertdialogue() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete?");
        alert.setMessage("Are you sure");
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.delete(id);
                finish();
            }
        });
        alert.setNegativeButton("Cancel", null);
        alert.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        display();
    }


}