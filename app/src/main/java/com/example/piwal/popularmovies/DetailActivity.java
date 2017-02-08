package com.example.piwal.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.piwal.popularmovies.data.MovieData;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("");

        if(savedInstanceState == null) {
            Intent intent = getIntent();
            if (intent.hasExtra(getString(R.string.intent_object_movie_data))) {
                MovieData data = getIntent().getParcelableExtra(getString(R.string.intent_object_movie_data));
                Bundle movieDetails = new Bundle();
                movieDetails.putParcelable(getString(R.string.intent_object_movie_data), data);
                DetailActivityFragment detailActivityFragment = new DetailActivityFragment();
                detailActivityFragment.setArguments(movieDetails);
                getSupportFragmentManager().beginTransaction().add(R.id.activity_detail_container, detailActivityFragment).commit();
            }
        }
    }

}
