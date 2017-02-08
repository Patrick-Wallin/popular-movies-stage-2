package com.example.piwal.popularmovies;

import android.media.Image;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by piwal on 2/2/2017.
 */

public class DetailMovieAdapterViewHolder extends RecyclerView.ViewHolder {
    final ImageView mBackdropImageView;
    final ImageView mPosterImageView;
    final TextView mTitleTextView;
    final TextView mReleaseDateTextView;
    final TextView mRatingTextView;
    final TextView mOverviewTextView;

    final RatingBar mRatingBar;

    final ImageButton mFavoriteImageButton;

    final RecyclerView mTrailerRecyclerView;
    final RecyclerView mReviewRecyclerView;


    public DetailMovieAdapterViewHolder(View itemView) {
        super(itemView);

        mBackdropImageView = (ImageView) itemView.findViewById(R.id.backdrop_image_view);
        mPosterImageView = (ImageView) itemView.findViewById(R.id.detail_poster_image_view);

        mTitleTextView = (TextView) itemView.findViewById(R.id.title_text_view);
        mReleaseDateTextView = (TextView) itemView.findViewById(R.id.release_date_text_view);
        mRatingTextView = (TextView) itemView.findViewById(R.id.rating_text_view);
        mOverviewTextView = (TextView) itemView.findViewById(R.id.overview_text_view);

        mRatingBar = (RatingBar) itemView.findViewById(R.id.rating_rating_bar);

        mFavoriteImageButton = (ImageButton) itemView.findViewById(R.id.favorite_image_button);

        mTrailerRecyclerView = (RecyclerView) itemView.findViewById(R.id.trailers_recycler_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(itemView.getContext(), 1);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        gridLayoutManager.setSpanCount(1);

        mTrailerRecyclerView.setLayoutManager(gridLayoutManager);

        mReviewRecyclerView = (RecyclerView) itemView.findViewById(R.id.reviews_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.VERTICAL,false);

        mReviewRecyclerView.setLayoutManager(linearLayoutManager);

    }
}
