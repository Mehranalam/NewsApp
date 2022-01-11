package com.mehran.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {

    private JSONArray data;
    private BottomNavigationView bottomNavigationView;
    private Executor executor = new Executor() {
        @Override
        public void execute(Runnable runnable) {
            runnable.run();
        }
    };

    public ArrayList<String> titles = new ArrayList<>();
    public ArrayList<String> description = new ArrayList<>();
    public ArrayList<String> authors = new ArrayList<>();
    public ArrayList<String> urlToImage = new ArrayList<>();
    public ArrayList<String> url = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // build a thread pool ExecutorService j = Executors.newFixedThreadPool
        // 1) read
        // 2) write

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);


        DBHandler handler = new DBHandler(getApplicationContext() ,executor);


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
                                    url.add(data.getJSONObject(i).getString("url"));

                                } catch (JSONException e) {
                                    System.out.println("field for i cant load json file");
                                }

                                // TODO : fix this for list urlto image view and other

//                                RecyclerView.LayoutManager LayoutManager = new
//                                        LinearLayoutManager(NewsFragment, LinearLayoutManager.VERTICAL
//                                        , false);
//                                recyclerView.setLayoutManager(LayoutManager);
//                                recyclerView.setAdapter(new CycleAdapter(getApplicationContext(), urlToImage
//                                        , titles, description, authors));
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

    @Override
    protected void onStart() {
        super.onStart();
        NewsFragment newsFragment = new NewsFragment(urlToImage, titles, description, authors, url);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContiner, newsFragment, null)
                .commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.news) {
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContiner, newsFragment, null)
                            .commit();
                    return true;
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContiner, Favorite_Fragment.class, null)
                            .commit();
                    return true;
                }
            }
        });
    }
}