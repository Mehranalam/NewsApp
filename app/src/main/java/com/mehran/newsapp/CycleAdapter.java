package com.mehran.newsapp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CycleAdapter extends RecyclerView.Adapter<CycleAdapter.myCycleAdapter> {

    private Context context;
    private ArrayList<String> urlImage = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();
    private ArrayList<String> authors = new ArrayList<>();


    public CycleAdapter(Context context
            , ArrayList<String> urlImage
            , ArrayList<String> titles
            , ArrayList<String> descriptions
            , ArrayList<String> authors) {

        this.authors = authors;
        this.context = context;
        this.descriptions = descriptions;
        this.titles = titles;
        this.urlImage = urlImage;

    }

    public class myCycleAdapter extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView description;
        public TextView title;
        public TextView author;
        public ShimmerFrameLayout shimmerFrameLayout;

        public myCycleAdapter(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmereffect);
        }
    }

    @NonNull
    @Override
    public myCycleAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new myCycleAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myCycleAdapter holder, int position) {
        holder.shimmerFrameLayout.startShimmer();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                holder.shimmerFrameLayout.hideShimmer();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable ,3000);
        Picasso.with(context).load(urlImage.get(position)).into(holder.imageView);
        holder.author.setText(authors.get(position));
        holder.title.setText(titles.get(position));
        holder.description.setText(descriptions.get(position));

    }

    @Override
    public int getItemCount() {
        return authors.size();
    }


}
