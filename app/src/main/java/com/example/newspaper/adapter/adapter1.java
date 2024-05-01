package com.example.newspaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.newspaper.Collect;
import com.example.newspaper.ForDelete;
import com.example.newspaper.R;
import com.example.newspaper.Retrieve_Database;
import com.example.newspaper.databasecollect;
import com.example.newspaper.signup;

import java.util.ArrayList;

public class adapter1 extends ArrayAdapter<Collect> {
    Context context;
    public adapter1(@NonNull Context context, ArrayList<Collect> list) {
        super(context, 0, list);

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {




                final Collect collect = getItem(position);
                View view = LayoutInflater.from(context).inflate(R.layout.trendingiterate,null);
//                ImageView imageView = view.findViewById(R.id.image2);
//                TextView username5 = view.findViewById(R.id.username1);
//                TextView gender5 = view.findViewById(R.id.gender1);
//                TextView email5 = view.findViewById(R.id.email1);
//                TextView dob5 = view.findViewById(R.id.date1);
        TextView textView = view.findViewById(R.id.text44);
        TextView textView1 = view.findViewById(R.id.text45);
                textView.setText(collect.Username);
                textView1.setText(collect.Gender);
//                email5.setText(collect.Email);
//                dob5.setText(collect.DoB);
//                if(collect.Image != null)
//                {
//                    imageView.setImageBitmap(signup.getBitmap(collect.Image));
//                }






        return view;
    }
}
