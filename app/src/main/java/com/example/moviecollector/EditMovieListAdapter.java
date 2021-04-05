package com.example.moviecollector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EditMovieListAdapter extends ArrayAdapter<Movie> {

    private Context context;

    private int resource;

    private DatabaseManager db;

    public EditMovieListAdapter(@NonNull Context context, int resource, @NonNull List<Movie> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        db = new DatabaseManager(context);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String title = getItem(position).getTitle();
        String director = getItem(position).getDirector();
        String actors = getItem(position).getActors();
        String review = getItem(position).getReview();
        int rating = getItem(position).getRating();
        int year = getItem(position).getYear();
        boolean favourite = getItem(position).isFavourite();
        int id = getItem(position).getId();

        Movie m = new Movie(id, title, director, actors, review, year, rating, favourite);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView movieTitle = (TextView) convertView.findViewById(R.id.movie_title_label);
        TextView movieDirector = (TextView) convertView.findViewById(R.id.movie_director_label);
        TextView movieActors = (TextView) convertView.findViewById(R.id.movie_actors_label);
        TextView movieReview = (TextView) convertView.findViewById(R.id.movie_review_label);
        TextView movieRating = (TextView) convertView.findViewById(R.id.movie_rating_label);
        TextView movieYear = (TextView) convertView.findViewById(R.id.movie_year_label);
        TextView favouriteLabel = (TextView) convertView.findViewById(R.id.favourite_label);

        movieTitle.setText(title);
        movieDirector.setText(director);
        movieActors.setText("Actors: " + actors);
        movieReview.setText("Review: " + review);
        movieRating.setText("Rating: " + Integer.toString(rating) + "/10");
        movieYear.setText("Year: " + Integer.toString(year));

        if (favourite)
            favouriteLabel.setText("Favourite");
        else
            favouriteLabel.setText("Not a Favourite");

        return convertView;
    }

}
