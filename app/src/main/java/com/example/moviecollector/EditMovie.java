package com.example.moviecollector;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EditMovie extends AppCompatActivity {

    private DatabaseManager db;

    private ArrayList<Movie> movies = new ArrayList<Movie>();

    private TextView titleInput, directorInput, actorsInput, yearInput, reviewInput;
    private RatingBar ratingInput;
    private ToggleButton favouriteToggle;
    private Button saveChangesBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_movie);
        int data = Integer.parseInt(getIntent().getStringExtra("id"));

        titleInput = (EditText) findViewById(R.id.input_movie_title);
        directorInput = (EditText) findViewById(R.id.input_movie_director);
        actorsInput = (EditText) findViewById(R.id.input_actors);
        yearInput = (EditText) findViewById(R.id.input_movie_year);
        reviewInput = (EditText) findViewById(R.id.review_input);
        ratingInput = (RatingBar) findViewById(R.id.rating_input_bar);
        favouriteToggle = (ToggleButton) findViewById(R.id.favourite_toggle_btn);
        saveChangesBtn = (Button) findViewById(R.id.save_data_btn);

        db = new DatabaseManager(this);
        db.open();

        Cursor cursor = db.fetch();

        do {
            int itemID = cursor.getInt(0);
            String itemTitle = cursor.getString(1);
            int itemYear = cursor.getInt(2);
            String itemDirector = cursor.getString(3);
            String itemActors = cursor.getString(4);
            int itemRating = cursor.getInt(5);
            String itemReview = cursor.getString(6);
            boolean itemFavourite = cursor.getInt(7) != 0;

            movies.add(new Movie(itemID, itemTitle, itemDirector, itemActors, itemReview, itemYear, itemRating, itemFavourite));


        } while (cursor.moveToNext());
        cursor.close();

        Movie oldMovieInfo = movies.get(data);

        System.out.println("Selected: " + data);

        titleInput.setText(oldMovieInfo.getTitle());
        directorInput.setText(oldMovieInfo.getDirector());
        actorsInput.setText(oldMovieInfo.getActors());
        yearInput.setText(Integer.toString(oldMovieInfo.getYear()));
        reviewInput.setText(oldMovieInfo.getReview());
        ratingInput.setNumStars(5);
        ratingInput.setStepSize((float) 0.5);
        ratingInput.setRating(oldMovieInfo.getRating());
        favouriteToggle.setChecked(oldMovieInfo.isFavourite());
        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditMovie.this, MainActivity.class);

                String newTitle = titleInput.getText().toString();
                String newDirector = directorInput.getText().toString();
                String newActors = actorsInput.getText().toString();
                String newReview = actorsInput.getText().toString();
                int newYear = Integer.parseInt(yearInput.getText().toString());
                float newRating = ratingInput.getRating() * 2;
                System.out.println("New rating: " + newRating);
                boolean newFavourite = favouriteToggle.isChecked();

                System.out.println("Updated: " + (data + 1));

                db.update((data + 1), newTitle, newYear, newDirector, newActors, (int) newRating, newReview, newFavourite);

                Toast.makeText(getApplicationContext(), "Updated movie", Toast.LENGTH_SHORT).show();

                startActivity(intent);

            }
        });


    }


}
