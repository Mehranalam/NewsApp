package com.mehran.newsapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;

    private ArrayList<String> urlImage = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();
    private ArrayList<String> authors = new ArrayList<>();
    private ArrayList<String> url = new ArrayList<>();

    public NewsFragment(ArrayList<String> urlImage
            ,ArrayList<String> titles
            ,ArrayList<String> descriptions
            ,ArrayList<String> authors
            ,ArrayList<String> url ) {

        super(R.layout.news_fragment_layout);

        this.urlImage = urlImage;
        this.titles = titles;
        this.descriptions = descriptions;
        this.authors = authors;
        this.url = url;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new CycleAdapter(view.getContext(), urlImage
                , titles, descriptions, authors , url));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    // TODO : ADD NEW WORK
}
