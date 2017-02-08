package com.example.piwal.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.example.piwal.popularmovies.data.TrailerData;
import com.example.piwal.popularmovies.utilities.NetworkUtils;
import com.example.piwal.popularmovies.utilities.OpenMoviesJsonUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piwal on 2/2/2017.
 */

public class FetchTrailersTask extends AsyncTask<Void, Void, List<TrailerData>> {
    private TrailerMovieAdapter mTrailerMovieAdapter;
    private Context mContext;
    private View mRootView;
    private int mPosition;
    private String movieId;

    public FetchTrailersTask(String id, TrailerMovieAdapter trailerMovieAdapter, Context context, View rootView, int position) {
        mTrailerMovieAdapter = trailerMovieAdapter;
        mContext = context;
        mRootView = rootView;
        mPosition = position;
        movieId = id;
    }

    @Override
    protected List<TrailerData> doInBackground(Void... params) {
        NetworkUtils networkUtils = new NetworkUtils(mContext);
        URL trailerRequestURL = networkUtils.buildTrailerUrl(movieId);

        if(trailerRequestURL != null) {
            try {
                String jsonTrailersResponse = networkUtils.getResponseFromHttpUrl(trailerRequestURL);
                return OpenMoviesJsonUtils.getSimpleTrailerDataFromJson(jsonTrailersResponse);
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
    protected void onPostExecute(List<TrailerData> trailerData) {
        if(trailerData != null) {
            mTrailerMovieAdapter.setTrailerData(trailerData);
            if(trailerData.size() > 0) {
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
