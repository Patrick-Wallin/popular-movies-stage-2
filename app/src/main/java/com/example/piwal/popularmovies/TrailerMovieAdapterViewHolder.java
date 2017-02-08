package com.example.piwal.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by piwal on 2/2/2017.
 */

public class TrailerMovieAdapterViewHolder extends RecyclerView.ViewHolder {
    final ImageView mThumbnailImageView;

    public TrailerMovieAdapterViewHolder(View itemView) {
        super(itemView);

        mThumbnailImageView = (ImageView) itemView.findViewById(R.id.trailer_thumbnail_image_view);
    }
}
