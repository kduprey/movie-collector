package com.example.moviecollector;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplaySearchItems extends AppCompatActivity {

    private DatabaseManager db;

    private ListView listView;

    private ArrayList<Movie> movies = new ArrayList<Movie>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_search_items);

        String query = getIntent().getStringExtra("query").toLowerCase();

        listView = (ListView) findViewById(R.id.search_list);

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

            if (itemTitle.toLowerCase().contains(query) || itemDirector.toLowerCase().contains(query) || itemActors.toLowerCase().contains(query)) {
                movies.add(new Movie(itemID, itemTitle, itemDirector, itemActors, itemReview, itemYear, itemRating, itemFavourite));
            }


        } while (cursor.moveToNext());
        cursor.close();


        MovieListAdapter adapter = new MovieListAdapter(this, R.layout.movie_item, movies);
        listView.setAdapter(adapter);

    }
}