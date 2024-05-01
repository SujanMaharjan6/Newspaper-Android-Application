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

import org.w3c.dom.Text;

public class foryou extends Fragment {
    TextView today;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.for_you, null);
//        today = view.findViewById(R.id.todayspick);

//        today.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(getActivity() instanceof com.example.newspaper.Fragment)
//                {
//                    ((com.example.newspaper.Fragment)getActivity()).todays();
//                }
//            }
//        });
        return view;
    }
}
