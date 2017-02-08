package com.example.piwal.popularmovies.utilities;

/**
 * Created by piwal on 1/24/2017.
 */

import android.content.Context;
import android.net.Uri;

import com.example.piwal.popularmovies.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {
    private Context mContext;

    public final static int SORT_MOST_POPULAR = 0;
    public final static int SORT_HIGHEST_RATED = 1;
    public final static int SORT_FAVORITES = 2;

    public NetworkUtils(Context context) {
        mContext = context;
    }

    public URL buildUrl(int sort) {
        Uri builtUri = Uri.parse(mContext.getString(R.string.url_themoviedb_base)
                + (sort == SORT_MOST_POPULAR ? mContext.getString(R.string.sort_by_popular) : mContext.getString(R.string.sort_by_highest_rated))).buildUpon()
                .appendQueryParameter(mContext.getString(R.string.param_api_key), mContext.getString(R.string.api_key))
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public URL buildTrailerUrl(String id) {
        String urlString = mContext.getString(R.string.url_themoviedb_base_trailer);
        urlString = String.format(urlString,id);
        Uri builtUri = Uri.parse(urlString).buildUpon()
                .appendQueryParameter(mContext.getString(R.string.param_api_key), mContext.getString(R.string.api_key))
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public URL buildReviewUrl(String id) {
        String urlString = mContext.getString(R.string.url_themoviedb_base_review);
        urlString = String.format(urlString,id);
        Uri builtUri = Uri.parse(urlString).buildUpon()
                .appendQueryParameter(mContext.getString(R.string.param_api_key), mContext.getString(R.string.api_key))
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * Method Name: getResponseFromHttpUrl
     * Param:       URL
     * Return:      String get JSON values
     */
    public String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
