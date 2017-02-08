package com.example.piwal.popularmovies.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Movie;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by piwal on 1/25/2017.
 */

public class MovieData implements Parcelable {
    private String mId;
    private String mMoviePosterPath;
    private String mMovieOriginalTitle;
    private String mUserRating;
    private String mPopularity;
    private String mReleaseDate;
    private String mOverview;
    private String mVoteCount;
    private String mBackDrop;

    public MovieData(Cursor cursorData) {
        if(cursorData != null) {
            System.out.println("put values into this class");
            setId(String.valueOf(cursorData.getInt(MovieContract.MovieEntry.COL_MOVIE_ID)));
            setMoviePosterPath(cursorData.getString(MovieContract.MovieEntry.COL_MOVIE_POSTER_PATH));
            setMovieOriginalTitle(cursorData.getString(MovieContract.MovieEntry.COL_MOVIE_TITLE));
            setUserRating(String.valueOf(cursorData.getFloat(MovieContract.MovieEntry.COL_MOVIE_USER_RATING)));
            setPopularity(String.valueOf(cursorData.getFloat(MovieContract.MovieEntry.COL_MOVIE_POPULARITY)));
            setReleaseDate(cursorData.getString(MovieContract.MovieEntry.COL_MOVIE_RELEASE_DATE));
            setOverview(cursorData.getString(MovieContract.MovieEntry.COL_MOVIE_OVERVIEW));
            setVoteCount(String.valueOf(cursorData.getInt(MovieContract.MovieEntry.COL_MOVIE_VOTE_COUNT)));
            setBackDrop(cursorData.getString(MovieContract.MovieEntry.COL_MOVIE_BACKDROP_PATH));

            System.out.println("Movie Poster Path: " + getMoviePosterPath());
            System.out.println("Movie Title: " + getMovieOriginalTitle());
        }
    }
    /**
     * Method Name: MovieData
     * Parameter:   JSONObject to be read and converted it into data in this class.
     * Return:      None
     */
    public MovieData(JSONObject json) {
        if(json != null) {
            if(json.has("id")) {
                try {
                    setId(json.get("id").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("poster_path")) {
                try {
                    setMoviePosterPath(json.get("poster_path").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("original_title")) {
                try {
                    setMovieOriginalTitle(json.get("original_title").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("vote_average")) {
                try {
                    setUserRating(json.get("vote_average").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("popularity")) {
                try {
                    setPopularity(json.get("popularity").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("release_date")) {
                try {
                    setReleaseDate(json.get("release_date").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("overview")) {
                try {
                    setOverview(json.get("overview").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("vote_count")) {
                try {
                    setVoteCount(json.get("vote_count").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("backdrop_path")) {
                try {
                    setBackDrop(json.get("backdrop_path").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void setId(String id) {
        mId = id;
    }
    public String getId() {
        return mId;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        mMoviePosterPath = moviePosterPath;
    }
    public String getMoviePosterPath() {
        return mMoviePosterPath;
    }

    public void setMovieOriginalTitle(String movieOriginalTitle) {
        mMovieOriginalTitle = movieOriginalTitle;
    }
    public String getMovieOriginalTitle() {
        return mMovieOriginalTitle;
    }

    public void setUserRating(String userRating) {
        mUserRating = userRating;
    }
    public String getUserRating() {
        return mUserRating;
    }

    public void setPopularity(String popularity) {
        mPopularity = popularity;
    }
    public String getPopularity() {
        return mPopularity;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }
    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }
    public String getOverview() {
        return mOverview;
    }

    public void setVoteCount(String voteCount) {
        mVoteCount = voteCount;
    }
    public String getVoteCount() {
        return mVoteCount;
    }

    public void setBackDrop(String backDrop) {
        mBackDrop = backDrop;
    }
    public String getBackDrop() {
        return mBackDrop;
    }

    public ContentValues getMovieContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID,getId());
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_BACKDROP_PATH,getBackDrop());
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW, getOverview());
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_POPULARITY, getPopularity());
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_POSTER_PATH, getMoviePosterPath());
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE, getReleaseDate());
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE, getMovieOriginalTitle());
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_USER_RATING, getUserRating());
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_VOTE_COUNT, getVoteCount());

        return contentValues;
    }
    /**
     * Method Name: MovieData
     * Parameter:   Parcel to be passed into data in this class.
     * Return:      None
     */
    protected MovieData(Parcel in) {
        mId = in.readString();
        mMoviePosterPath = in.readString();
        mMovieOriginalTitle = in.readString();
        mUserRating = in.readString();
        mPopularity = in.readString();
        mReleaseDate = in.readString();
        mOverview = in.readString();
        mVoteCount = in.readString();
        mBackDrop = in.readString();
    }

    /**
     * Class:       Creator
     * Parameter:   MovieData
     * Return:      None
     */
    public static final Creator<MovieData> CREATOR = new Creator<MovieData>() {
        @Override
        public MovieData createFromParcel(Parcel in) {
            return new MovieData(in);
        }
        @Override
        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mMoviePosterPath);
        dest.writeString(mMovieOriginalTitle);
        dest.writeString(mUserRating);
        dest.writeString(mPopularity);
        dest.writeString(mReleaseDate);
        dest.writeString(mOverview);
        dest.writeString(mVoteCount);
        dest.writeString(mBackDrop);
    }
}
