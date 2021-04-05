package com.example.moviecollector;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class ShowResults extends AppCompatActivity {

    private final String API_KEY = "k_lalihdzv";

    private final String BASE_URL = "https://imdb-api.com/en/API/SearchMovie/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);

        String movieTitle = getIntent().getStringExtra("movieTitle");
        String movieYear = getIntent().getStringExtra("movieYear");



        final String searchQuery = movieTitle + movieYear;

        Uri finalURI = Uri.parse(BASE_URL).buildUpon().appendPath(API_KEY).appendPath(searchQuery).build();


        try {
            URL reqURL = new URL(finalURI.toString());

            new myAsyncTask().execute(reqURL);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String convertIsToString(InputStream stream, int len)
            throws IOException, UnsupportedEncodingException {

        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    private class myAsyncTask extends AsyncTask<URL, Integer, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Loading data...", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected Bitmap doInBackground(URL... urls) {
            URL reqURL = null;
            try {
                reqURL = new URL(urls.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) reqURL.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                int resCode = connection.getResponseCode();


                String resultString = null;
                StringBuilder builder = new StringBuilder();
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line + "\n");
                }
                if (builder.length() == 0) {
                    return null;
                }
                resultString = builder.toString();

                JSONObject object = new JSONObject(resultString);

                JSONArray results = (JSONArray) object.get("results");

                ArrayList<MovieRatings> movieInfo = new ArrayList<MovieRatings>();

                ArrayList<String> links = new ArrayList<String>();

                for (int i = 0; i < results.length(); i++) {
                    JSONObject obj = results.getJSONObject(i);

                    movieInfo.add(new MovieRatings(obj.get("id").toString(), obj.get("title").toString(), obj.get("image").toString()));
                    Uri finalURI = Uri.parse(BASE_URL).buildUpon().appendPath(API_KEY).appendPath(obj.get("id").toString()).build();

                }



            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}