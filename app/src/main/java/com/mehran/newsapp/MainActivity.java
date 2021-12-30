package com.mehran.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.media.Ringtone;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.card.MaterialCardView;

import org.json.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private JSONArray data;
    private RecyclerView recyclerView;

    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> description = new ArrayList<>();
    private ArrayList<String> authors = new ArrayList<>();
    private ArrayList<String> urlToImage = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);


        RequestQueue queue = Volley.newRequestQueue(this);
        String urlNewsApp = "https://saurav.tech/NewsAPI/everything/cnn.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlNewsApp,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject js = new JSONObject(response);
                            data = js.getJSONArray("articles");

                            for (int i = 0; i < data.length(); i++) {
                                try {
                                    urlToImage.add(data.getJSONObject(i).getString("urlToImage"));
                                    titles.add(data.getJSONObject(i).getString("title"));
                                    description.add(data.getJSONObject(i).getString("description"));
                                    authors.add(data.getJSONObject(i).getString("author"));

                                } catch (JSONException e) {
                                    System.out.println("field for i cant load json file");
                                }

                                // TODO : fix this for list urlto image view and other

                                RecyclerView.LayoutManager LayoutManager = new
                                        LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL
                                        , false);
                                recyclerView.setLayoutManager(LayoutManager);
                                recyclerView.setAdapter(new CycleAdapter(getApplicationContext(), urlToImage
                                        , titles, description, authors));
                            }

                        } catch (JSONException err) {
                            System.out.println("field for i cant load json file");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Parse data is field");
            }
        });

        queue.add(stringRequest);

    }
}