package com.example.piwal.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by piwal on 2/2/2017.
 */

public class TrailerData implements Parcelable {
    private String mName;
    private String mSource;
    private String mType;

    /**
     * Method Name: TrailerData
     * Parameter:   JSONObject to be read and converted it into data in this class.
     * Return:      None
     */
    public TrailerData(JSONObject json) {
        if(json != null) {
            if(json.has("name")) {
                try {
                    setName(json.get("name").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("source")) {
                try {
                    setSource(json.get("source").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("type")) {
                try {
                    setType(json.get("type").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setName(String name) {
        mName = name;
    }
    public String getName() {
        return mName;
    }

    public void setSource(String source) {
        mSource = source;
    }
    public String getSource() {
        return mSource;
    }

    public void setType(String type) {
        mType = type;
    }
    public String getType() {
        return mType;
    }

    /**
     * Method Name: TrailerData
     * Parameter:   Parcel to be passed into data in this class.
     * Return:      None
     */
    protected TrailerData(Parcel in) {
        mName = in.readString();
        mSource = in.readString();
        mType = in.readString();
    }

    public static final Creator<TrailerData> CREATOR = new Creator<TrailerData>() {
        @Override
        public TrailerData createFromParcel(Parcel in) {
            return new TrailerData(in);
        }

        @Override
        public TrailerData[] newArray(int size) {
            return new TrailerData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mSource);
        dest.writeString(mType);
    }
}
