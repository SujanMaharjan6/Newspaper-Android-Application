package com.example.newspaper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class signup extends AppCompatActivity {
    TextView username, email, date, password, signin;
    ImageView image;
    RadioGroup gender;
    Button register;
    DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;
    int id;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        databaseHelper = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("forLog", 0);
        register = findViewById(R.id.signup1);
        username = findViewById(R.id.user);
        email = findViewById(R.id.email);
        date = findViewById(R.id.dob);
        password = findViewById(R.id.pass);
        gender = findViewById(R.id.radio1);
        signin = findViewById(R.id.signin);
        image = findViewById(R.id.image1);

        id = getIntent().getIntExtra("id", 0);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (id != 0) {
            Collect collect = databaseHelper.getUser(id + "");
            username.setText(collect.Username);
            email.setText(collect.Email);
            date.setText(collect.DoB);
            password.setText(collect.Password);
            if (collect.Gender.equals("Male")) {
                ((RadioButton) findViewById(R.id.male1)).setChecked(true);
            } else {
                ((RadioButton) findViewById(R.id.female1)).setChecked(true);
            }
            if (collect.Image != null) {
                image.setImageBitmap(signup.getBitmap(collect.Image));
            }

            register.setText("Update");

        }
//        else
//        {
//            startActivity(new Intent(signup.this, MainActivity.class));
//        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty((EditText) username) && isEmail((EditText) email) && isEmpty((EditText) date) && isEmpty((EditText) password)) {


                    String user = username.getText().toString();
                    String emaill = email.getText().toString();
                    String datee = date.getText().toString();
                    String passwordd = password.getText().toString();
                    RadioButton btn = findViewById(gender.getCheckedRadioButtonId());
                    String gend = btn.getText().toString();


                    ContentValues contentValues = new ContentValues();
                    contentValues.put("UserName", user);
                    contentValues.put("Email", emaill);
                    contentValues.put("DoB", datee);
                    contentValues.put("Password", passwordd);
                    contentValues.put("Gender", gend);
                    if (bitmap != null) {
                        contentValues.put("Image", getBlob(bitmap));
                    }


                    if (id == 0) {
                        databaseHelper.Insert(contentValues);
                        finish();


                    } else {
                        databaseHelper.updateUser(contentValues, String.valueOf(id));
                        Toast.makeText(signup.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putBoolean("rememberMe", false).apply();
                startActivity(new Intent(signup.this, MainActivity.class));
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 102);
            }
        });
    }

    Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 102 && resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bitmap);
        }
    }

    public byte[] getBlob(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bytes = bos.toByteArray();
        return bytes;
    }

    public static Bitmap getBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public boolean isEmpty(EditText view) {
        String value = view.getText().toString();
        if (value.length() > 0) {
            return true;
        } else {
            view.setError("Enter Value");
            view.requestFocus();
            return false;
        }
    }

    public boolean isEmail(EditText view) {
        String value = view.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            return true;
        } else {
            view.setError("Enter valid email");
            view.requestFocus();
            return false;
        }
    }
}

