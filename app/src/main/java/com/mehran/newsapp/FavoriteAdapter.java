package com.mehran.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {

    private List<String> favoriteImage;
    private List<String> title;
    private List<String> goToUrl;

    private Context context;

    class FavoriteHolder extends RecyclerView.ViewHolder {

        public ImageView favoriteImage;
        public TextView title;
        public TextView goToUrl;

        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);

            favoriteImage = itemView.findViewById(R.id.favoriteImage);
            title = itemView.findViewById(R.id.favoriteTitle);
            goToUrl = itemView.findViewById(R.id.favoriteOpen);
        }
    }

    // List<String> favoriteImage ,
    //                           List<String> title

    public FavoriteAdapter(Context context ) {

        this.context = context;
        this.favoriteImage = favoriteImage;
        this.title = title;
        this.goToUrl = goToUrl;
    }

    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_items_layout ,parent ,false);

        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder holder, int position) {
//        Picasso.with(context).load(favoriteImage.).into(holder.favoriteImage);
//        holder.title.setText("need");

    }

    @Override
    public int getItemCount() {
        return TransferData.count;
    }
}
