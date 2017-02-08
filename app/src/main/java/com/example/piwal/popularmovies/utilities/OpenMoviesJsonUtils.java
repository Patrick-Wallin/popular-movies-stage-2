package com.example.piwal.popularmovies.utilities;

import com.example.piwal.popularmovies.data.MovieData;
import com.example.piwal.popularmovies.data.ReviewData;
import com.example.piwal.popularmovies.data.TrailerData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piwal on 1/25/2017.
 */

public class OpenMoviesJsonUtils {
    public static List<MovieData> getSimpleMovieDataFromJson(String moviesJson) {
        List<MovieData> parsedMovieData = new ArrayList<>();

        try {
            JSONObject jsonMovie = new JSONObject(moviesJson);

            if(jsonMovie != null) {
                if(jsonMovie.has("results")) {
                    if(jsonMovie.get("results") instanceof JSONArray) {
                        JSONArray arrayResults = jsonMovie.getJSONArray("results");

                        if(arrayResults != null && arrayResults.length() > 0) {
                            for(int i = 0; i < arrayResults.length(); i++) {
                                if(arrayResults.get(i) instanceof JSONObject) {
                                    MovieData data = new MovieData(arrayResults.getJSONObject(i));
                                    parsedMovieData.add(data);
                                }
                            }
                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return parsedMovieData;
    }
    public static List<TrailerData> getSimpleTrailerDataFromJson(String trailerJson) {
        List<TrailerData> parsedTrailerData = new ArrayList<>();

        try {
            JSONObject jsonTrailer = new JSONObject(trailerJson);

            if(jsonTrailer != null) {
                if(jsonTrailer.has("youtube")) {
                    if(jsonTrailer.get("youtube") instanceof JSONArray) {
                        JSONArray arrayResults = jsonTrailer.getJSONArray("youtube");

                        if(arrayResults != null && arrayResults.length() > 0) {
                            for(int i = 0; i < arrayResults.length(); i++) {
                                if(arrayResults.get(i) instanceof JSONObject) {
                                    TrailerData data = new TrailerData(arrayResults.getJSONObject(i));
                                    parsedTrailerData.add(data);
                                }
                            }
                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return parsedTrailerData;
    }
    public static List<ReviewData> getSimpleReviewDataFromJson(String reviewJson) {
        List<ReviewData> parsedReviewData = new ArrayList<>();

        try {
            JSONObject jsonReview = new JSONObject(reviewJson);

            if(jsonReview != null) {
                if(jsonReview.has("results")) {
                    if(jsonReview.get("results") instanceof JSONArray) {
                        JSONArray arrayResults = jsonReview.getJSONArray("results");

                        if(arrayResults != null && arrayResults.length() > 0) {
                            for(int i = 0; i < arrayResults.length(); i++) {
                                if(arrayResults.get(i) instanceof JSONObject) {
                                    ReviewData data = new ReviewData(arrayResults.getJSONObject(i));
                                    parsedReviewData.add(data);
                                }
                            }
                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return parsedReviewData;
    }

}
