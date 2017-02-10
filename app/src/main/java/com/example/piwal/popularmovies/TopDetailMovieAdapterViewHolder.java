package com.example.piwal.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by piwal on 2/8/2017.
 */

public class TopDetailMovieAdapterViewHolder extends RecyclerView.ViewHolder {
    final ImageView mBackdropImageView;
    final ImageView mPosterImageView;
    final TextView mTitleTextView;
    final TextView mReleaseDateTextView;
    final TextView mRatingTextView;

    final RatingBar mRatingBar;

    public TopDetailMovieAdapterViewHolder(View itemView) {
        super(itemView);

        mBackdropImageView = (ImageView) itemView.findViewById(R.id.backdrop_image_view);
        mPosterImageView = (ImageView) itemView.findViewById(R.id.detail_poster_image_view);

        mTitleTextView = (TextView) itemView.findViewById(R.id.title_text_view);
        mReleaseDateTextView = (TextView) itemView.findViewById(R.id.release_date_text_view);
        mRatingTextView = (TextView) itemView.findViewById(R.id.rating_text_view);

        mRatingBar = (RatingBar) itemView.findViewById(R.id.rating_rating_bar);

    }


}
