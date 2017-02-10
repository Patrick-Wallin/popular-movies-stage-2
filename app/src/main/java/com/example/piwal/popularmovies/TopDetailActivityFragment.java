package com.example.piwal.popularmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.piwal.popularmovies.data.MovieData;
import com.example.piwal.popularmovies.data.ReviewData;
import com.example.piwal.popularmovies.data.TrailerData;

import java.util.ArrayList;

/**
 * Created by piwal on 2/8/2017.
 */

public class TopDetailActivityFragment extends Fragment {
    private RecyclerView mRecyclerView;

    private TopDetailMovieAdapter mTopDetailMovieAdapter;

    private ImageView mBackDropImageView;
    private ImageView mMoviePosterImageView;

    private TextView mTitleTextView;
    private TextView mReleaseDateTextView;
    private TextView mRatingTextView;
    private RatingBar mRatingBar;

    private MovieData mMovie;

    public TopDetailActivityFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey(getString(R.string.intent_object_movie_data))) {
            mMovie = getArguments().getParcelable(getString(R.string.intent_object_movie_data));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_detail_fragment,container,false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.detail_recycler_view);

        if (mTopDetailMovieAdapter == null) {
            mTopDetailMovieAdapter = new TopDetailMovieAdapter(mMovie, getActivity());
        }else
            mTopDetailMovieAdapter.setMovieData(mMovie);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.setAdapter(mTopDetailMovieAdapter);

        return rootView;
    }

}
