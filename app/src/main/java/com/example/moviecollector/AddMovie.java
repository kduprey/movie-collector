package com.example.moviecollector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class AddMovie extends AppCompatActivity {

    private EditText titleInput;
    private EditText directorInput;
    private EditText actorsInput;
    private EditText ratingInput;
    private EditText yearInput;
    private EditText reviewInput;
    private Button saveBtn;

    private DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        titleInput = (EditText) findViewById(R.id.input_movie_title);
        directorInput = (EditText) findViewById(R.id.input_movie_director);
        actorsInput = (EditText) findViewById(R.id.input_actors);
        ratingInput = (EditText) findViewById(R.id.rating_input);
        yearInput = (EditText) findViewById(R.id.input_movie_year);
        reviewInput = (EditText) findViewById(R.id.review_input);
        saveBtn = (Button) findViewById(R.id.save_data_btn);

        db = new DatabaseManager(this);
        db.open();


    }

    private boolean validateInput() {

        boolean valid = false;


        if (titleInput.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter the movie title", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            valid = true;
        }

        if (directorInput.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter the director", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            valid = true;
        }

        if (actorsInput.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter the movie actors", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            valid = true;
        }

        if (reviewInput.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter the movie review", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            valid = true;
        }

        try {
            int rating = Integer.parseInt(ratingInput.getText().toString());
            if (rating > 10 || rating < 1) {
                Toast.makeText(getApplicationContext(), "Enter a number between 1 and 10", Toast.LENGTH_SHORT).show();
                valid = false;
            } else
                valid = true;
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Please enter a number for the rating", Toast.LENGTH_SHORT).show();
            ratingInput.setText("");
            valid = false;
        }

        try {
            int year = Integer.parseInt(yearInput.getText().toString());
            if (year < 1895) {
                System.out.println(year);
                Toast.makeText(getApplicationContext(), "Enter a year that is newer than 1895", Toast.LENGTH_SHORT).show();
                yearInput.setText("");
                valid = false;
            } else
                valid = true;
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Please enter a number for the year", Toast.LENGTH_SHORT).show();
            yearInput.setText("");
            valid = false;
        }

        return valid;
    }

    public void saveToDatabase(View view) {
        if (validateInput()) {
            final String TITLE = titleInput.getText().toString();
            final String DIRECTOR = directorInput.getText().toString();
            final String ACTORS = actorsInput.getText().toString();
            final String REVIEW = reviewInput.getText().toString();
            final int RATING = Integer.parseInt(ratingInput.getText().toString());
            final int YEAR = Integer.parseInt(yearInput.getText().toString());

            db.insert(TITLE, YEAR, DIRECTOR, ACTORS, RATING, REVIEW);

            titleInput.setText("");
            directorInput.setText("");
            actorsInput.setText("");
            reviewInput.setText("");
            ratingInput.setText("");
            yearInput.setText("");

            Toast.makeText(getApplicationContext(), "Movie added!!", Toast.LENGTH_SHORT).show();


        }



    }


}