package com.example.piwal.popularmovies;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by piwal on 1/28/2017.
 */

public class DetailActivityFragment extends Fragment {
    private RecyclerView mRecyclerView;

    private DetailMovieAdapter mDetailMovieAdapter;

    private ImageView mBackDropImageView;
    private ImageView mMoviePosterImageView;

    private TextView mTitleTextView;
    private TextView mReleaseDateTextView;
    private TextView mRatingTextView;
    private TextView mOverviewTextView;
    private RatingBar mRatingBar;

    private MovieData mMovie;

    public DetailActivityFragment() {}

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

        if (mDetailMovieAdapter == null) {
            mDetailMovieAdapter = new DetailMovieAdapter(mMovie, new ArrayList<TrailerData>(),
                    new ArrayList<ReviewData>(), getActivity());
        }else
            mDetailMovieAdapter.setMovieData(mMovie);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.setAdapter(mDetailMovieAdapter);

        return rootView;
    }



}
