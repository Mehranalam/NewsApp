package com.mehran.newsapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class Favorite_Fragment extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> url = new ArrayList<>();
    ArrayList<String> imageurl = new ArrayList<>();

    private Executor executor;

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
        Cursor cursor = handler.readData();

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                String item = cursor.getString(cursor.getColumnIndexOrThrow("Title"));
                title.add(item);
                String itemImageUrl = cursor.getString(cursor.getColumnIndexOrThrow("ImageUrl"));
                imageurl.add(itemImageUrl);
                String itemUrl = cursor.getString(cursor.getColumnIndexOrThrow("Url"));
                url.add(itemUrl);
            } while (cursor.moveToNext());

            cursor.close();

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
