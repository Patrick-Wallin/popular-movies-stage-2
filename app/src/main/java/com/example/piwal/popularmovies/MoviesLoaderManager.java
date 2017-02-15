package com.example.piwal.popularmovies;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.example.piwal.popularmovies.data.MovieContract;

/**
 * Created by piwal on 2/5/2017.
 */

public class MoviesLoaderManager implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final int ID_MOVIE_LOADER = 100;

    private Context mContext;
    private PopularMovieAdapter mPopularMovieAdapter;

    public MoviesLoaderManager(Context context, PopularMovieAdapter popularMovieAdapter) {
        mContext = context;
        mPopularMovieAdapter = popularMovieAdapter;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch(id) {
            case ID_MOVIE_LOADER:
                return new CursorLoader(mContext, MovieContract.MovieEntry.CONTENT_URI,null,null,null,null);

            default:
                break;
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mPopularMovieAdapter.setMovieData(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // ?
    }
}
