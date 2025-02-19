package com.example.qrkodrestapidoga_moriczpeterakos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends BaseAdapter {
    private final Context context;
    private final List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false);
        }

        TextView directorTextView = convertView.findViewById(R.id.directorTextView);
        TextView durationTextView = convertView.findViewById(R.id.durationTextView);
        TextView ratingTextView = convertView.findViewById(R.id.ratingTextView);
        TextView categoryTextView = convertView.findViewById(R.id.categoryTextView);


        Movie movie = movies.get(position);
        directorTextView.setText(movie.getDirector());
        durationTextView.setText(formatDuration(movie.getDuration()));
        ratingTextView.setText(String.valueOf(movie.getRating()));
        categoryTextView.setText(movie.getCategory());

        return convertView;
    }

    private String formatDuration(int duration){
        int hours = duration / 60;
        int minutes =duration % 60;
        return hours + " óra " + minutes + " perc ";
    }

}
