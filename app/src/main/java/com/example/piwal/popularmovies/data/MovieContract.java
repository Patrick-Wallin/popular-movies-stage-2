package com.example.piwal.popularmovies.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by piwal on 2/4/2017.
 */

public class MovieContract {
    public static final String SCHEME = "content://";
    public static final String CONTENT_AUTHORITY = "com.example.piwal.popularmovies";
    public static final Uri BASE_CONTENT_URI = Uri.parse(SCHEME + CONTENT_AUTHORITY);
    public static final String PATH_MOVIES = "movies";

    public static final class MovieEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String TABLE_NAME = PATH_MOVIES;

        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_MOVIE_TITLE = "original_title";
        public static final String COLUMN_MOVIE_POSTER_PATH = "poster_path";
        public static final String COLUMN_MOVIE_OVERVIEW = "overview";
        public static final String COLUMN_MOVIE_USER_RATING = "vote_average";
        public static final String COLUMN_MOVIE_POPULARITY = "popularity";
        public static final String COLUMN_MOVIE_RELEASE_DATE = "release_date";
        public static final String COLUMN_MOVIE_VOTE_COUNT = "vote_count";
        public static final String COLUMN_MOVIE_BACKDROP_PATH = "backdrop_path";

        public static final int COL_MOVIE_ID = 1;
        public static final int COL_MOVIE_TITLE = 2;
        public static final int COL_MOVIE_POSTER_PATH = 3;
        public static final int COL_MOVIE_OVERVIEW = 4;
        public static final int COL_MOVIE_USER_RATING = 5;
        public static final int COL_MOVIE_POPULARITY = 6;
        public static final int COL_MOVIE_RELEASE_DATE = 7;
        public static final int COL_MOVIE_VOTE_COUNT = 8;
        public static final int COL_MOVIE_BACKDROP_PATH = 9;

        /*
        public static final String[] MOVIE_COLUMNS = {
                "_ID",
                COLUMN_MOVIE_ID,
                COLUMN_MOVIE_TITLE,
                COLUMN_MOVIE_POSTER_PATH,
                COLUMN_MOVIE_OVERVIEW,
                COLUMN_MOVIE_USER_RATING,
                COLUMN_MOVIE_POPULARITY,
                COLUMN_MOVIE_RELEASE_DATE,
                COLUMN_MOVIE_VOTE_COUNT,
                COLUMN_MOVIE_BACKDROP_PATH
        };
        */
    }
}
