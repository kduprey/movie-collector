package com.example.moviecollector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void searchQuery(View v) {
        EditText queryInput = (EditText) findViewById(R.id.search_input);
        Intent intent = new Intent(SearchActivity.this, DisplaySearchItems.class);
        intent.putExtra("query", queryInput.getText().toString());
        startActivity(intent);
    }
}