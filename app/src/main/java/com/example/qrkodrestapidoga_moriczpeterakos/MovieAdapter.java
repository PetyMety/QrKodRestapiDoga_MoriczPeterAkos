package com.example.qrkodrestapidoga_moriczpeterakos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {
    private final Context context;
    private final List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies){
        super(context, 0);
        this.context = context;
        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false);
        }

        TextView directorTextView = convertView.findViewById(R.id.directorTextView);
        TextView durationTextView = convertView.findViewById(R.id.durationTextView);
        TextView ratingTextView = convertView.findViewById(R.id.ratingTextView);
        TextView categoryTextView = convertView.findViewById(R.id.categoryTextView);

        directorTextView.setText(movie.getDirector());
        durationTextView.setText(movie.getDuration());
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
