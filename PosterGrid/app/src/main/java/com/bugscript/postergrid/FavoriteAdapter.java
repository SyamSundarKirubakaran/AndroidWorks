package com.bugscript.postergrid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by syamsundark on 16/12/17.
 */

public class FavoriteAdapter extends BaseAdapter {
    private Context mContext;


    public FavoriteAdapter(Context c) {
        mContext = c;
    }


    public int getCount() {
        return MoviesDB.fav_poster.length;
    }


    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            imageView = (ImageView) inflater.inflate(R.layout.movie_poster, parent, false);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext)
                .load("https://image.tmdb.org/t/p/w500"+MoviesDB.fav_poster[position])
                .into(imageView);

        return imageView;
    }
}
