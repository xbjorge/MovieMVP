package com.example.movieadventure.homemodule.homemodule.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieadventure.R;
import com.example.movieadventure.homemodule.common.KeyDirectory;
import com.example.movieadventure.homemodule.common.pojos.Result;

import java.util.List;

public class HomeSliderMovieAdapter extends PagerAdapter {

    private Context mContext;
    private List<Result> resultList;
    private ImageView sliderHomeTrailer;
    private ImageView sliderHomeMovie;
    private TextView sliderHomeNameMovie;
    private FloatingActionButton sliderHomePlay;

    public HomeSliderMovieAdapter(Context mContext, List<Result> resultList) {
        this.mContext = mContext;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View homeSliderLayout = inflater.inflate(R.layout.home_slider_item, null);

        sliderHomeTrailer = homeSliderLayout.findViewById(R.id.imv_home_Trailer);
        sliderHomeMovie = homeSliderLayout.findViewById(R.id.imv_home_Movie);
        sliderHomeNameMovie= homeSliderLayout.findViewById(R.id.txt_home_movieName);
        sliderHomePlay = homeSliderLayout.findViewById(R.id.floatingActionButton);

        final RequestOptions options = new RequestOptions()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext)
                .load(KeyDirectory.getUrlBasePath()+resultList.get(position).getPosterPath())
                .apply(options)
                .into(sliderHomeMovie);
        Glide.with(mContext)
                .load(R.drawable.bigsick)
                .apply(options)
                .into(sliderHomeTrailer);

        sliderHomeNameMovie.setText(resultList.get(position).getTitle());

        container.addView(homeSliderLayout);
        return homeSliderLayout;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View)object);
    }
}
