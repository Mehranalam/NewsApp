package com.mehran.newsapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Favorite_Fragment extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<String> title;
    ArrayList<String> url;
    ArrayList<String> imageurl;

    private Executor executor ;

    public Favorite_Fragment() {
        super(R.layout.favorite_fragment_layout);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        executor = new Executor() {
            @Override
            public void execute(Runnable runnable) {
                runnable.run();
            }
        };

        // TODO : bug is here not get data from while

        DBHandler handler = new DBHandler(getContext(), executor);
        title = new ArrayList<>(handler.readData(1));
        imageurl = new ArrayList<>(handler.readData(2));
        url = new ArrayList<>(handler.readData(3));

        if (title.size() != 0) {
            recyclerView = view.findViewById(R.id.favoriteRecycleView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new FavoriteAdapter(getContext(), title, imageurl, url));
        } else {
            Toast.makeText(getContext(), "favorite has empty", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    // TODO : SOMTHINGS
}
