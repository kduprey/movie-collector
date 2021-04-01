package com.example.moviecollector;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseStarter extends SQLiteOpenHelper {


//    Table Name
    public static final String TABLE_NAME = "MOVIE_COLLECTION";

//    Table columns
    public static final String ID = "id";
    public static final String TITLE = "movie";
    public static final String YEAR = "year";
    public static final String DIRECTOR = "director";
    public static final String ACTORS = "actors";
    public static final String RATING = "rating";
    public static final String REVIEW = "review";

//    Database Information
    public static final String DATABASE_NAME = "MOVIE_COLLECTION.DB";

//    Database version
    public static final int DATABASE_VERSION = 1;

//    Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
        + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + TITLE + " TEXT NOT NULL, "
        + YEAR + " INTEGER NOT NULL, "
        + DIRECTOR + " TEXT NOT NULL, "
        + ACTORS + " TEXT NOT NULL, "
        + RATING + " INTEGER NOT NULL, "
        + REVIEW + " TEXT NOT NULL);";

    public DatabaseStarter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }
}
