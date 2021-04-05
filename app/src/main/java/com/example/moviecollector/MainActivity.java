package com.example.moviecollector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addMovieActivity(View view) {
        Intent intent = new Intent(MainActivity.this, AddMovie.class);
        startActivity(intent);
    }

    public void displayMovieActivity(View view) {
        Intent intent = new Intent(MainActivity.this, DisplayMovies.class);
        startActivity(intent);
    }

    public void displayFavouritesActivity(View view) {
        Intent intent = new Intent(MainActivity.this, DisplayFavourites.class);
        startActivity(intent);
    }

    public void displayEditMoviesActivity(View view) {
        Intent intent = new Intent(MainActivity.this, EditMovies.class);
        startActivity(intent);
    }

    public void displaySearchMoviesActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    public void displayRatingsMoviesActivity(View view) {
        Intent intent = new Intent(MainActivity.this, RatingsActivity.class);
        startActivity(intent);
    }
}