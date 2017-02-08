package com.example.piwal.popularmovies;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.piwal.popularmovies.data.TrailerData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piwal on 2/2/2017.
 */

public class TrailerMovieAdapter extends RecyclerView.Adapter<TrailerMovieAdapterViewHolder> {
    private ArrayList<TrailerData> mTrailerData;
    private Context mContext;

    public TrailerMovieAdapter(ArrayList<TrailerData> trailer, Context context) {
        mTrailerData = trailer;
        mContext = context;
    }

    public void setTrailerData(List<TrailerData> trailerData) {
        mTrailerData = (ArrayList)trailerData;
        notifyDataSetChanged();
    }

    @Override
    public TrailerMovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;

        layoutId = R.layout.grid_item_trailer;

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);

        return new TrailerMovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerMovieAdapterViewHolder holder, int position) {
        if(position >= 0 && !mTrailerData.isEmpty() && mTrailerData.size() > position) {
            final TrailerData trailerData = mTrailerData.get(position);

            StringBuilder imagePath = new StringBuilder();
            imagePath.append(mContext.getString(R.string.url_youtube_image));
            if(!imagePath.toString().endsWith("/"))
                imagePath.append("/");
            imagePath.append(trailerData.getSource());
            imagePath.append("/");
            imagePath.append(mContext.getString(R.string.youtube_first_image));

            Picasso.with(mContext).setLoggingEnabled(true);
            Picasso.with(mContext)
                    .load(imagePath.toString())
                    .into(holder.mThumbnailImageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uriAppString = "vnd.youtube:" + trailerData.getSource();
                    String uriWebString = mContext.getString(R.string.url_youtube_launch) + trailerData.getSource();
                    Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriAppString));
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriWebString));
                    try {
                        mContext.startActivity(appIntent);
                    }catch(ActivityNotFoundException ex) {
                        mContext.startActivity(webIntent);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mTrailerData.size();
    }
}
