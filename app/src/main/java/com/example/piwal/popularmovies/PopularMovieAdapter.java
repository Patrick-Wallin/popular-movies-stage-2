package com.example.piwal.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.piwal.popularmovies.data.MovieData;
import com.example.piwal.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piwal on 1/24/2017.
 */

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapterViewHolder> {
    private ArrayList<MovieData> mMovies;
    private Context mContext;
    private int SORT = NetworkUtils.SORT_MOST_POPULAR;
    private FrameLayout mFrameLayout;

    public PopularMovieAdapter(ArrayList<MovieData> movies, Context context, FrameLayout frameLayout) {
        mMovies = movies;
        mContext = context;
        mFrameLayout = frameLayout;
    }

    @Override
    public PopularMovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int layoutId;

        layoutId = R.layout.grid_item;

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId,viewGroup,false);

        return new PopularMovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PopularMovieAdapterViewHolder holder, int position) {
        if(position >= 0 && !mMovies.isEmpty() && mMovies.size() > position) {
            final MovieData movie = mMovies.get(position);

            holder.movieTitleTextView.setText(movie.getMovieOriginalTitle());

            String posterPath = movie.getMoviePosterPath().trim();

            if(posterPath.isEmpty()) {
                holder.mPosterImageView.setImageResource(R.drawable.default_image);
            }else {
                StringBuilder imagePath = new StringBuilder();
                imagePath.append(mContext.getString(R.string.url_themoviedb_base_image));
                if(!imagePath.toString().endsWith("/"))
                    imagePath.append("/");
                imagePath.append(mContext.getString(R.string.poster_size));
                imagePath.append(posterPath);
                Picasso.with(mContext).load(imagePath.toString()).into(holder.mPosterImageView);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mContext.getResources().getBoolean(R.bool.isPhone)) {
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra(mContext.getString(R.string.intent_object_movie_data), movie);
                        mContext.startActivity(intent);
                    }else {
                        Bundle movieDetails = new Bundle();
                        movieDetails.putParcelable(mContext.getResources().getString(R.string.intent_object_movie_data), movie);
                        TopDetailActivityFragment topDetailActivityFragment = new TopDetailActivityFragment();
                        topDetailActivityFragment.setArguments(movieDetails);
                        ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.activity_detail_container, topDetailActivityFragment).commit();

                    }

                }
            });


            if(position == 0 &&  !mContext.getResources().getBoolean(R.bool.isPhone)) {
                if(((FragmentActivity)mContext).getSupportFragmentManager().findFragmentById(R.id.activity_detail_container) == null) {
                    Bundle movieDetails = new Bundle();
                    movieDetails.putParcelable(mContext.getResources().getString(R.string.intent_object_movie_data), movie);
                    TopDetailActivityFragment topDetailActivityFragment = new TopDetailActivityFragment();
                    topDetailActivityFragment.setArguments(movieDetails);

                    ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.activity_detail_container, topDetailActivityFragment).commit();

                }

            }


        }
    }

    @Override
    public int getItemCount() {
        if(mMovies != null)
            return mMovies.size();
        else
            return 0;
    }

    public void setMovieData(List<MovieData> movieData, int sort) {
        SORT = sort;
        mMovies = (ArrayList)movieData;
        notifyDataSetChanged();
    }

    public void setMovieData(Cursor cursorData) {
        mMovies.clear();
        if(cursorData != null) {
            for(cursorData.moveToFirst(); !cursorData.isAfterLast(); cursorData.moveToNext()) {
                mMovies.add(new MovieData(cursorData));
            }
        }
        notifyDataSetChanged();
    }

}
