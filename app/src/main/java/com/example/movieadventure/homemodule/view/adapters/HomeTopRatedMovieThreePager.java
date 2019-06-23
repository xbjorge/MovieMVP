package com.example.movieadventure.homemodule.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieadventure.R;
import com.example.movieadventure.common.KeyDirectory;
import com.example.movieadventure.common.pojos.TopRatedMovie;

import java.util.List;

public class HomeTopRatedMovieThreePager extends RecyclerView.Adapter<HomeTopRatedMovieThreePager.ViewHolder> {

    private Context context;
    private List<TopRatedMovie> listResultTopRated;
    MovieItemClickListener movieItemClickListener;

    public HomeTopRatedMovieThreePager(Context context, List<TopRatedMovie> listResultTopRated, MovieItemClickListener movieItemClickListener) {
        this.context = context;
        this.listResultTopRated = listResultTopRated;
        this.movieItemClickListener = movieItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_pager_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final RequestOptions options = new RequestOptions()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(KeyDirectory.getUrlBaseImage()+listResultTopRated.get(i).getPosterPath())
                .apply(options)
                .into(viewHolder.moviePoster1);

    }

    @Override
    public int getItemCount() {
        return listResultTopRated.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView moviePoster1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePoster1 = itemView.findViewById(R.id.imgMovie1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListener.onMovieClickListener(listResultTopRated.get(getAdapterPosition()), moviePoster1 );
                }
            });
        }
    }
}
