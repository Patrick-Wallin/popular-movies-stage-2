package com.example.piwal.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.example.piwal.popularmovies.data.ReviewData;
import com.example.piwal.popularmovies.utilities.NetworkUtils;
import com.example.piwal.popularmovies.utilities.OpenMoviesJsonUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by piwal on 2/2/2017.
 */

public class FetchReviewsTask extends AsyncTask<Void,Void,List<ReviewData>> {
    private ReviewMovieAdapter mReviewMovieAdapter;
    private Context mContext;
    private View mRootView;
    private int mPosition;
    private String movieId;

    public FetchReviewsTask(String id, ReviewMovieAdapter reviewMovieAdapter, Context context, View rootView, int position) {
        mReviewMovieAdapter = reviewMovieAdapter;
        mContext = context;
        mRootView = rootView;
        mPosition = position;
        movieId = id;
    }

    @Override
    protected List<ReviewData> doInBackground(Void... params) {
        NetworkUtils networkUtils = new NetworkUtils(mContext);
        URL reviewRequestURL = networkUtils.buildReviewUrl(movieId);

        if(reviewRequestURL != null) {
            try {
                String jsonReviewsResponse = networkUtils.getResponseFromHttpUrl(reviewRequestURL);
                return OpenMoviesJsonUtils.getSimpleReviewDataFromJson(jsonReviewsResponse);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<ReviewData> reviewData) {
        if(reviewData != null) {
            mReviewMovieAdapter.setReviewData(reviewData);
            if(reviewData.size() > 0) {
                //if(mPosition != -1)
                //  ((GridLayoutManager)recyclerView.getLayoutManager()).scrollToPosition(mPosition);
            }else {
                //recyclerView.setVisibility(View.INVISIBLE);
                //progressBar.setVisibility(View.INVISIBLE);
                //errorMessageTextView.setText(mContext.getString(R.string.error_message_no_data));
                //errorMessageTextView.setVisibility(View.VISIBLE);
            }
        }else {
            //recyclerView.setVisibility(View.INVISIBLE);
            //progressBar.setVisibility(View.INVISIBLE);
            //errorMessageTextView.setText(mContext.getString(R.string.error_message_no_internet_connection));
            //errorMessageTextView.setVisibility(View.VISIBLE);
        }

    }
}
