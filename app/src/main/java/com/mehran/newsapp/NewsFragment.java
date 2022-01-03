package com.mehran.newsapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImageView like;

    private ArrayList<String> urlImage = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();
    private ArrayList<String> authors = new ArrayList<>();

    public NewsFragment(ArrayList<String> urlImage
            ,ArrayList<String> titles
            ,ArrayList<String> descriptions
            ,ArrayList<String> authors) {

        this.urlImage = urlImage;
        this.titles = titles;
        this.descriptions = descriptions;
        this.authors = authors;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        like = view.findViewById(R.id.like);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new CycleAdapter(view.getContext(), urlImage
                , titles, descriptions, authors));

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : ADD FAVORITE
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_fragment_layout, container, false);

    }

    // TODO : ADD NEW WORK
}
