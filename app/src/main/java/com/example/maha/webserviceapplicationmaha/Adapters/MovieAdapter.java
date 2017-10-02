package com.example.maha.webserviceapplicationmaha.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.maha.webserviceapplicationmaha.DataModel.MovieModel;
import com.example.maha.webserviceapplicationmaha.R;

/**
 * Created by Maha on 02/10/2017.
 */

public class MovieAdapter extends ArrayAdapter<MovieModel> {
    public MovieAdapter(@NonNull Context context, @NonNull MovieModel[] objects) {
        super(context, 0,  objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


if (convertView==null){

    LayoutInflater.from(getContext()).inflate(R.layout.movie_row, parent, false);
}
       MovieModel movieModel= getItem(position);



        return convertView;
    }
}
