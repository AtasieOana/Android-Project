package com.example.projectandroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "news_app_database";

    public AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE USER_TABLE ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT, name TEXT)";
        String CREATE_NEWS_TABLE = "CREATE TABLE NEWS_TABLE ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, user_id TEXT, title TEXT, content TEXT, created_at DATE)";
        String CREATE_PHOTO_TABLE = "CREATE TABLE PHOTO_TABLE ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, news_id TEXT, photo BLOB)";
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_NEWS_TABLE);
        db.execSQL(CREATE_PHOTO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER_TABLE");
        db.execSQL("DROP TABLE IF EXISTS NEWS_TABLE");
        db.execSQL("DROP TABLE IF EXISTS PHOTO_TABLE");
        onCreate(db);
    }

}