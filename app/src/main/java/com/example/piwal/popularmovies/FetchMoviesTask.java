package com.example.piwal.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.piwal.popularmovies.data.MovieData;
import com.example.piwal.popularmovies.utilities.NetworkUtils;
import com.example.piwal.popularmovies.utilities.OpenMoviesJsonUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by piwal on 1/25/2017.
 */

public class FetchMoviesTask extends AsyncTask<Void, Void, List<MovieData>> {
    private int SORT = NetworkUtils.SORT_MOST_POPULAR;
    private PopularMovieAdapter mPopularMovieAdapter;
    private Context mContext;
    private View mRootView;
    private int mPosition;

    public FetchMoviesTask(int sort, PopularMovieAdapter popularMovieAdapter, Context context, View rootView, int position) {
        if(sort == NetworkUtils.SORT_MOST_POPULAR || sort == NetworkUtils.SORT_HIGHEST_RATED) {
            SORT = sort;
        }
        mPopularMovieAdapter = popularMovieAdapter;
        mContext = context;
        mRootView = rootView;
        mPosition = position;
    }

    @Override
    protected List<MovieData> doInBackground(Void... params) {
        NetworkUtils networkUtils = new NetworkUtils(mContext);
        URL movieRequestURL = networkUtils.buildUrl(SORT);

        if (movieRequestURL != null) {
            try {
                String jsonMoviesResponse = networkUtils.getResponseFromHttpUrl(movieRequestURL);
                return OpenMoviesJsonUtils.getSimpleMovieDataFromJson(jsonMoviesResponse);
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
        ProgressBar progressBar = (ProgressBar)mRootView.findViewById(R.id.pb_loading_indicator);
        RecyclerView recyclerView = (RecyclerView)mRootView.findViewById(R.id.movies_recycler_view);
        TextView errorMessageTextView = (TextView)mRootView.findViewById(R.id.error_message);
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        errorMessageTextView.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onPostExecute(List<MovieData> movieData) {
        ProgressBar progressBar = (ProgressBar) mRootView.findViewById(R.id.pb_loading_indicator);
        RecyclerView recyclerView = (RecyclerView) mRootView.findViewById(R.id.movies_recycler_view);
        TextView errorMessageTextView = (TextView)mRootView.findViewById(R.id.error_message);

        if(movieData != null) {
            mPopularMovieAdapter.setMovieData(movieData,SORT);
            if(movieData.size() > 0) {
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                errorMessageTextView.setVisibility(View.INVISIBLE);
                if(mPosition != -1)
                    ((GridLayoutManager)recyclerView.getLayoutManager()).scrollToPosition(mPosition);
            }else {
                recyclerView.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                errorMessageTextView.setText(mContext.getString(R.string.error_message_no_data));
                errorMessageTextView.setVisibility(View.VISIBLE);
            }
        }else {
            recyclerView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            errorMessageTextView.setText(mContext.getString(R.string.error_message_no_internet_connection));
            errorMessageTextView.setVisibility(View.VISIBLE);
        }
    }
}

