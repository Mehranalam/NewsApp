package com.mehran.newsapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {

    private ArrayList<String> favoriteImage;
    private ArrayList<String> title;
    private ArrayList<String> goToUrl;

    private Context context;
    private int actulePosition;

    private Executor executor = new Executor() {
        @Override
        public void execute(Runnable runnable) {
            runnable.run();
        }
    };

    class FavoriteHolder extends RecyclerView.ViewHolder {

        public ImageView favoriteImage;
        public TextView title;
        public Button button;
        public ImageView deleteItemFromList;

        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);

            deleteItemFromList = itemView.findViewById(R.id.deleteFavoriteItem);
            favoriteImage = itemView.findViewById(R.id.favoriteImage);
            title = itemView.findViewById(R.id.favoriteTitle);
            button = itemView.findViewById(R.id.buttonUrl);
        }
    }

    // List<String> favoriteImage ,
    //                           List<String> title

    public FavoriteAdapter(Context context ,
                           ArrayList<String> title,
                           ArrayList<String> favoriteImage,
                           ArrayList<String> goToUrl) {

        this.context = context;
        this.title = title;
        this.favoriteImage = favoriteImage;
        this.goToUrl = goToUrl;
    }

    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_items_layout ,parent ,false);

        // View view = LayoutInflater.from(parent.getContext())
        // .inflate(R.Layout.EXAMPLE ,parent ,flase);


        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder holder, int position) {
        int localPositionVar = position;
        Picasso.with(context).load(favoriteImage.get(position)).into(holder.favoriteImage);
        holder.title.setText(title.get(position));
        DBHandler dbHandler = new DBHandler(context ,executor);

        holder.deleteItemFromList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.deleteData(title.get(localPositionVar) ,goToUrl.get(localPositionVar) ,favoriteImage.get(localPositionVar));
                removeItem(holder.getAdapterPosition());
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = goToUrl.get(localPositionVar);
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();

                customTabsIntent.launchUrl(context , Uri.parse(url));
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public void removeItem(int position) {
        title.remove(position);
        favoriteImage.remove(position);
        goToUrl.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position ,title.size());
        notifyItemRangeChanged(position ,favoriteImage.size());
        notifyItemRangeChanged(position ,goToUrl.size());
    }
}


