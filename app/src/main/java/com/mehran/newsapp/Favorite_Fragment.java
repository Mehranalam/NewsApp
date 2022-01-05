package com.mehran.newsapp;

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

import java.util.List;

public class Favorite_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private String title;
    private String url;
    private String imageurl;
    private Bundle bundle;

    public Favorite_Fragment() {
        super(R.layout.favorite_fragment_layout);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bundle = getArguments();
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DBHandler handler = new DBHandler(getContext());
        if (!handler.isEmptyFavoriteTable()) {

            recyclerView = view.findViewById(R.id.favoriteRecycleView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new FavoriteAdapter(getContext()));
            TransferData.transferData.clear();
        } else {
            Toast.makeText(getContext(), "favorite has empty", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    // TODO : SOMTHINGS
}
