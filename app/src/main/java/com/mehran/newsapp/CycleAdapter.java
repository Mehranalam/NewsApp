package com.mehran.newsapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.Executor;


public class CycleAdapter extends RecyclerView.Adapter<CycleAdapter.myCycleAdapter> {

    private Context context;
    private ArrayList<String> urlImage = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();
    private ArrayList<String> authors = new ArrayList<>();
    public ArrayList<String> url = new ArrayList<>();
    private Executor executor;


    public CycleAdapter(Context context
            , ArrayList<String> urlImage
            , ArrayList<String> titles
            , ArrayList<String> descriptions
            , ArrayList<String> authors
            , ArrayList<String> url) {

        this.authors = authors;
        this.context = context;
        this.descriptions = descriptions;
        this.titles = titles;
        this.urlImage = urlImage;
        this.url = url;

    }

    public class myCycleAdapter extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView description;
        public TextView title;
        public TextView author;
        public ShimmerFrameLayout shimmerFrameLayout;
        public ImageView like;

        public myCycleAdapter(@NonNull View itemView) {
            super(itemView);

            like = itemView.findViewById(R.id.like);
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
        int newPosition = position;

        holder.shimmerFrameLayout.startShimmer();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                holder.shimmerFrameLayout.hideShimmer();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, 2000);
        Picasso.with(context).load(urlImage.get(position)).into(holder.imageView);
        holder.author.setText(authors.get(position));
        holder.title.setText(titles.get(position));
        holder.description.setText(descriptions.get(position));
        executor = new Executor() {
            @Override
            public void execute(Runnable runnable) {
                runnable.run();
            }
        };
        DBHandler dbHandler = new DBHandler(context, executor);

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.like.setImageResource(R.drawable.ic_baseline_favorite_24);
                dbHandler.addData(titles.get(newPosition), url.get(newPosition), urlImage.get(newPosition));

                TransferData.isTransferData = true;
                Toast.makeText(context, "this Articles saved", Toast.LENGTH_SHORT)
                        .show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return authors.size();
    }


}
