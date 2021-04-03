package com.example.moviecollector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private DatabaseStarter dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DatabaseManager(Context c) {
        context = c;
    }

    public DatabaseManager open() throws SQLException{
        dbHelper = new DatabaseStarter(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String title, int year, String director, String actors, int rating, String review, boolean favourite) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbHelper.TITLE, title);
        contentValue.put(dbHelper.YEAR, year);
        contentValue.put(dbHelper.DIRECTOR, director);
        contentValue.put(dbHelper.ACTORS, actors);
        contentValue.put(dbHelper.RATING, rating);
        contentValue.put(dbHelper.REVIEW, review);
        contentValue.put(dbHelper.FAVOURITE, favourite);
        database.insert(dbHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] {dbHelper.ID, dbHelper.TITLE, dbHelper.YEAR, dbHelper.DIRECTOR, dbHelper.ACTORS, dbHelper.RATING, dbHelper.REVIEW, dbHelper.FAVOURITE};
        Cursor cursor = database.query(dbHelper.TABLE_NAME, columns, null, null, null, null, dbHelper.TITLE + " ASC");

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long id, String title, int year, String director, String actors, int rating, String review, boolean favourite) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbHelper.TITLE, title);
        contentValue.put(dbHelper.YEAR, year);
        contentValue.put(dbHelper.DIRECTOR, director);
        contentValue.put(dbHelper.ACTORS, actors);
        contentValue.put(dbHelper.RATING, rating);
        contentValue.put(dbHelper.REVIEW, review);
        contentValue.put(dbHelper.FAVOURITE, favourite);

        int i = database.update(dbHelper.TABLE_NAME, contentValue, dbHelper.ID + " = " + id, null);
        return i;
    }

    public void delete(long id) {
        database.delete(dbHelper.TABLE_NAME, dbHelper.ID + "=" + id, null);
    }

}
