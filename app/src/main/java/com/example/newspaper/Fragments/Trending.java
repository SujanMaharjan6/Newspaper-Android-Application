package com.example.newspaper.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.newspaper.Collect;
import com.example.newspaper.R;
import com.example.newspaper.adapter.adapter;
import com.example.newspaper.adapter.adapter1;
import com.example.newspaper.databasecollect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Trending extends Fragment {
    ListView listView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.trending, null);
        listView = view.findViewById(R.id.trendingpage);


        Log.i("response", "response");
        getDatabase();
        return view;
    }


    public void getDatabase() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "http://10.0.2.2/news/selectbyname.php";
//    String url ="http://10.0.2.2/news/selectbyname.php";
        Log.i("response", "response" + 1);
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.i("response", "response" + response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            ArrayList<databasecollect> list = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                databasecollect data = new databasecollect();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                data.id = jsonObject.getString("id");
                                data.text10 = jsonObject.getString("heading");
                                data.text12 = jsonObject.getString("subheading");
                                data.img = jsonObject.getString("image");
//                            data.img = jsonObject.("image");

                                list.add(data);
                            }

                            listView.setAdapter(new adapter(getActivity(), list));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}