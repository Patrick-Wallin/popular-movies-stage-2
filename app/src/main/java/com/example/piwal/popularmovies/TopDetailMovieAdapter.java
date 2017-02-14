package com.example.piwal.popularmovies;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.piwal.popularmovies.data.MovieContract;
import com.example.piwal.popularmovies.data.MovieData;
import com.squareup.picasso.Picasso;

/**
 * Created by piwal on 2/8/2017.

 */

public class TopDetailMovieAdapter extends RecyclerView.Adapter<TopDetailMovieAdapterViewHolder> {
    private MovieData mMovieData;
    private Context mContext;

    public TopDetailMovieAdapter(MovieData movie, Context context) {
        mMovieData = movie;
        mContext = context;
    }

    public void setMovieData(MovieData movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }

    @Override
    public TopDetailMovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;

        layoutId = R.layout.fragment_detail_top;

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);

        return new TopDetailMovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopDetailMovieAdapterViewHolder holder, int position) {
        if(mMovieData != null) {
            StringBuilder imagePath = new StringBuilder();
            imagePath.append(mContext.getString(R.string.url_themoviedb_base_image));
            if (!imagePath.toString().endsWith("/"))
                imagePath.append("/");
            imagePath.append(mContext.getString(R.string.backdrop_size));
            imagePath.append(mMovieData.getBackDrop());
            Picasso.with(mContext)
                    .load(imagePath.toString())
                    .into(holder.mBackdropImageView);

            imagePath.setLength(0);
            imagePath.append(mContext.getString(R.string.url_themoviedb_base_image));
            if (!imagePath.toString().endsWith("/"))
                imagePath.append("/");
            imagePath.append(mContext.getString(R.string.poster_size_detail));
            imagePath.append(mMovieData.getMoviePosterPath());
            Picasso.with(mContext)
                    .load(imagePath.toString())
                    .into(holder.mPosterImageView);

            holder.mTitleTextView.setText(mMovieData.getMovieOriginalTitle());
            holder.mReleaseDateTextView.setText(mMovieData.getReleaseDate());
            holder.mRatingTextView.setText(mMovieData.getUserRating() + "/" + mContext.getString(R.string.full_rating_point));
            holder.mRatingBar.setRating((float) Float.valueOf(mMovieData.getUserRating()));

            if(hasThisMovieBeenFavorite(mMovieData.getId())) {
                holder.mFavoriteImageButton.setTag("Y");
                holder.mFavoriteImageButton.setBackgroundResource(R.drawable.ic_favorite_star);
            }else {
                holder.mFavoriteImageButton.setTag("N");
                holder.mFavoriteImageButton.setBackgroundResource(R.drawable.ic_unfavorite_star);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra(mContext.getString(R.string.intent_object_movie_data), mMovieData);
                    mContext.startActivity(intent);
                }
            });
        }

    }

    private boolean hasThisMovieBeenFavorite(String movieID) {
        boolean bFavorite = false;

        Cursor movieCursor = mContext.getContentResolver().query(
                MovieContract.MovieEntry.CONTENT_URI,
                new String[] {MovieContract.MovieEntry.COLUMN_MOVIE_ID},
                MovieContract.MovieEntry.COLUMN_MOVIE_ID + " = " + movieID,
                null,
                null);

        if(movieCursor != null) {
            if(movieCursor.moveToFirst())
                bFavorite = true;
        }

        return bFavorite;
    }
    @Override
    public int getItemCount() {
        return (mMovieData != null ? 1 : 0);
    }
}
