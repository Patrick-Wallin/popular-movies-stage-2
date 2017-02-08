package com.example.piwal.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by piwal on 1/24/2017.
 */

public class PopularMovieAdapterViewHolder extends RecyclerView.ViewHolder {
    final ImageView mPosterImageView;
    final TextView movieTitleTextView;

    public PopularMovieAdapterViewHolder(View itemView) {
        super(itemView);

        mPosterImageView = (ImageView) itemView.findViewById(R.id.poster_image_view);
        movieTitleTextView = (TextView) itemView.findViewById(R.id.movie_title_text_view);

    }
}
