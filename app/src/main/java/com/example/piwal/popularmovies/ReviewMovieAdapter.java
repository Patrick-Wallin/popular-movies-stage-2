package com.example.piwal.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.piwal.popularmovies.data.ReviewData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piwal on 2/2/2017.
 */

public class ReviewMovieAdapter extends RecyclerView.Adapter<ReviewMovieAdapterViewHolder> {
    private ArrayList<ReviewData> mReviewData;
    private Context mContext;

    public ReviewMovieAdapter(ArrayList<ReviewData> review, Context context) {
        mReviewData = review;
        mContext = context;
    }

    public void setReviewData(List<ReviewData> reviewData) {
        mReviewData = (ArrayList)reviewData;
        notifyDataSetChanged();
    }

    @Override
    public ReviewMovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;

        layoutId = R.layout.list_item_review;

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);

        return new ReviewMovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewMovieAdapterViewHolder holder, int position) {
        if(position >= 0 && !mReviewData.isEmpty() && mReviewData.size() > position) {
            final ReviewData reviewData = mReviewData.get(position);

            holder.mReviewsTextView.setText(reviewData.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mReviewData.size();
    }
}
