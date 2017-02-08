package com.example.piwal.popularmovies;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.example.piwal.popularmovies.data.MovieContract;
import com.example.piwal.popularmovies.data.MovieData;
import com.example.piwal.popularmovies.data.ReviewData;
import com.example.piwal.popularmovies.data.TrailerData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by piwal on 2/2/2017.
 */

public class DetailMovieAdapter extends RecyclerView.Adapter<DetailMovieAdapterViewHolder> {
    private TrailerMovieAdapter mTrailerMovieAdapter;
    private ReviewMovieAdapter mReviewMovieAdapter;

    private MovieData mMovieData;
    private ArrayList<TrailerData> mTrailerData;
    private ArrayList<ReviewData> mReviewData;

    private Context mContext;

    public DetailMovieAdapter(MovieData movie, ArrayList<TrailerData> trailer, ArrayList<ReviewData> review, Context context) {
        mMovieData = movie;
        mTrailerData = trailer;
        mReviewData = review;
        mContext = context;
        mTrailerMovieAdapter = new TrailerMovieAdapter(trailer,context);
        mReviewMovieAdapter = new ReviewMovieAdapter(review,context);
    }

    public void setMovieData(MovieData movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }

    public void setTrailerData(ArrayList<TrailerData> trailerData) {
        mTrailerData = trailerData;
        notifyDataSetChanged();
    }

    public void setReviewData(ArrayList<ReviewData> reviewData) {
        mReviewData = reviewData;
        notifyDataSetChanged();
    }

    @Override
    public DetailMovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;

        layoutId = R.layout.fragment_detail;

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);

        return new DetailMovieAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final DetailMovieAdapterViewHolder holder, int position) {
        if(mMovieData != null) {
            StringBuilder imagePath = new StringBuilder();
            imagePath.append(mContext.getString(R.string.url_themoviedb_base_image));
            if(!imagePath.toString().endsWith("/"))
                imagePath.append("/");
            imagePath.append(mContext.getString(R.string.backdrop_size));
            imagePath.append(mMovieData.getBackDrop());
            Picasso.with(mContext)
                    .load(imagePath.toString())
                    .into(holder.mBackdropImageView);

            imagePath.setLength(0);
            imagePath.append(mContext.getString(R.string.url_themoviedb_base_image));
            if(!imagePath.toString().endsWith("/"))
                imagePath.append("/");
            imagePath.append(mContext.getString(R.string.poster_size_detail));
            imagePath.append(mMovieData.getMoviePosterPath());
            Picasso.with(mContext)
                    .load(imagePath.toString())
                    .into(holder.mPosterImageView);

            holder.mTitleTextView.setText(mMovieData.getMovieOriginalTitle());
            holder.mReleaseDateTextView.setText(mMovieData.getReleaseDate());
            holder.mRatingTextView.setText(mMovieData.getUserRating()+"/"+mContext.getString(R.string.full_rating_point));
            holder.mOverviewTextView.setText(mMovieData.getOverview());
            holder.mRatingBar.setRating((float)Float.valueOf(mMovieData.getUserRating()));

            if(hasThisMovieBeenFavorite(mMovieData.getId())) {
                holder.mFavoriteImageButton.setTag("Y");
                holder.mFavoriteImageButton.setBackgroundResource(R.drawable.ic_favorite_star);
            }else {
                holder.mFavoriteImageButton.setTag("N");
                holder.mFavoriteImageButton.setBackgroundResource(R.drawable.ic_unfavorite_star);
            }

            holder.mFavoriteImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.mFavoriteImageButton.getTag().toString().equalsIgnoreCase("N")) {
                        ContentValues contentValues = mMovieData.getMovieContentValues();

                        Uri uri = mContext.getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, contentValues);
                        if(uri != null) {
                            holder.mFavoriteImageButton.setBackgroundResource(R.drawable.ic_favorite_star);
                            holder.mFavoriteImageButton.setTag("Y");
                        }
                    }else {
                        ContentValues contentValues = mMovieData.getMovieContentValues();

                        int numberOfDeletedRecords = mContext.getContentResolver().delete(MovieContract.MovieEntry.CONTENT_URI,
                                MovieContract.MovieEntry.COLUMN_MOVIE_ID + " = ?", new String[] {mMovieData.getId()});

                        if(numberOfDeletedRecords > 0) {
                            holder.mFavoriteImageButton.setBackgroundResource(R.drawable.ic_unfavorite_star);
                            holder.mFavoriteImageButton.setTag("N");
                        }


                    }

                }
            });

            if(mTrailerData != null) {
                if (mTrailerMovieAdapter == null) {
                    mTrailerMovieAdapter = new TrailerMovieAdapter(mTrailerData,mContext);
                }else {
                    mTrailerMovieAdapter.setTrailerData(mTrailerData);
                }
                holder.mTrailerRecyclerView.setAdapter(mTrailerMovieAdapter);

                loadTrailerData(mMovieData.getId());
            }
            if(mReviewData != null) {
                if (mReviewMovieAdapter == null) {
                    mReviewMovieAdapter = new ReviewMovieAdapter(mReviewData,mContext);
                }else {
                    mReviewMovieAdapter.setReviewData(mReviewData);
                }
                holder.mReviewRecyclerView.setAdapter(mReviewMovieAdapter);

                loadReviewData(mMovieData.getId());
            }

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

    private void loadTrailerData(String id) {
        new FetchTrailersTask(id, mTrailerMovieAdapter, mContext, null, 1).execute();
    }

    private void loadReviewData(String id) {
        new FetchReviewsTask(id, mReviewMovieAdapter, mContext, null, 1).execute();
    }

    @Override
    public int getItemCount() {
        return (mMovieData != null ? 1 : 0);
    }
}
