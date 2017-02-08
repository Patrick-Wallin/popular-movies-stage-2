package com.example.piwal.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.piwal.popularmovies.R;

/**
 * Created by piwal on 2/2/2017.
 */

public class ReviewMovieAdapterViewHolder extends RecyclerView.ViewHolder {
    final TextView mReviewsTextView;

    public ReviewMovieAdapterViewHolder(View itemView) {
        super(itemView);

        mReviewsTextView = (TextView) itemView.findViewById(R.id.review_text_view);
    }
}
