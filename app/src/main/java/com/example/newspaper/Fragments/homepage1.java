package com.example.newspaper.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newspaper.R;

public class homepage1 extends Fragment {

    TextView foryou;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page, null);
//        foryou = view.findViewById(R.id.foryou);


//        foryou.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (getActivity() instanceof com.example.newspaper.Fragment) {
//                    ((com.example.newspaper.Fragment) getActivity()).foryou1();
//                }
//            }
//        });
        return view;
    }


}
