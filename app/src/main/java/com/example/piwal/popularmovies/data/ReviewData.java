package com.example.piwal.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by piwal on 2/2/2017.
 */

public class ReviewData implements Parcelable {
    private String mAuthor;
    private String mContent;

    /**
     * Method Name: ReviewData
     * Parameter:   JSONObject to be read and converted it into data in this class.
     * Return:      None
     */
    public ReviewData(JSONObject json) {
        if(json != null) {
            if(json.has("author")) {
                try {
                    setAuthor(json.get("author").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("content")) {
                try {
                    setContent(json.get("content").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }
    public String getAuthor() {
        return mAuthor;
    }

    public void setContent(String content) {
        mContent = content;
    }
    public String getContent() {
        return mContent;
    }

    protected ReviewData(Parcel in) {
        mAuthor = in.readString();
        mContent = in.readString();
    }

    public static final Creator<ReviewData> CREATOR = new Creator<ReviewData>() {
        @Override
        public ReviewData createFromParcel(Parcel in) {
            return new ReviewData(in);
        }

        @Override
        public ReviewData[] newArray(int size) {
            return new ReviewData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAuthor);
        dest.writeString(mContent);
    }
}
