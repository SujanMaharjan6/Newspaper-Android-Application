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


import com.bumptech.glide.Glide;
import com.example.newspaper.Collect;
import com.example.newspaper.ForDelete;
import com.example.newspaper.R;
import com.example.newspaper.Retrieve_Database;
import com.example.newspaper.databasecollect;
import com.example.newspaper.signup;

import java.util.ArrayList;

public class adapter extends ArrayAdapter<databasecollect> {
    Context context;

    public adapter(@NonNull Context context, ArrayList<databasecollect> list) {
        super(context, 0, list);

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final databasecollect data = getItem(position);
        View view = LayoutInflater.from(context).inflate(R.layout.trendingiterate, null);
        TextView textView = view.findViewById(R.id.text44);
        TextView textView1 = view.findViewById(R.id.text45);
        ImageView imageView = view.findViewById(R.id.image44);
        textView.setText(data.text10);
        textView1.setText(data.text12);
        Glide.with(context).load(data.img).into(imageView);

        return view;
    }
}
