package com.example.moviecollector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class RatingsActivity extends AppCompatActivity {

    private DatabaseManager db;

    private ListView listView;

    private ArrayList<Movie> movies = new ArrayList<Movie>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        listView = (ListView) findViewById(R.id.ratings_movies_list);

        db = new DatabaseManager(this);
        db.open();

        Cursor cursor = db.fetch();

        int count = 0;

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

            count++;
            System.out.println(count);
        } while (cursor.moveToNext());
        cursor.close();


        EditMovieListAdapter adapter = new EditMovieListAdapter(this, R.layout.edit_movie_item, movies);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("ID: " + id);
                Movie movieSelected = movies.get((int) id);
                Intent intent = new Intent(RatingsActivity.this, ShowResults.class);
                intent.putExtra("movieTitle", movieSelected.getTitle());
                intent.putExtra("movieYear", Integer.toString(movieSelected.getYear()));
                startActivity(intent);
            }
        });

    }
}