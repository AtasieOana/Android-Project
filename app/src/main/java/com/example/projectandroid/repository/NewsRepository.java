package com.example.projectandroid.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectandroid.database.AppDatabase;
import com.example.projectandroid.models.News;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsRepository {

    private final AppDatabase appDatabase;
    private final SQLiteDatabase db;

    public NewsRepository(Context context) {
        appDatabase = new AppDatabase(context);
        db = appDatabase.getReadableDatabase();

    }

    private String dateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                java.util.Locale.getDefault());
        return dateFormat.format(date);
    }


    private Date stringToDate(String string) throws ParseException{
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                java.util.Locale.getDefault()).parse(string);

    }
    public void insertNews(News news) {
        ContentValues values = new ContentValues();
        values.put("user_id", news.getUserId());
        values.put("content", news.getContent());
        values.put("title", news.getTitle());
        values.put("created_at", dateToString(news.getCreatedAt()));

        db.insert("NEWS_TABLE", null, values);
    }

    public News getNewsById(int id) throws ParseException {
        Cursor cursor = db.query("NEWS_TABLE", new String[] { "id", "user_id",
                        "title", "content", "created_at" }, "id = ?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return new News(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3), stringToDate(cursor.getString(4)));
    }


    public List<News> getAllNews() throws ParseException {
        List<News> newsList = new ArrayList<News>();
        String selectQuery = "SELECT  * FROM NEWS_TABLE";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                News news = new News();
                news.setId(Integer.parseInt(cursor.getString(0)));
                news.setUserId(Integer.parseInt(cursor.getString(1)));
                news.setTitle(cursor.getString(2));
                news.setContent(cursor.getString(3));
                news.setCreatedAt(stringToDate(cursor.getString(4)));

                newsList.add(news);
            } while (cursor.moveToNext());
        }
        return newsList;
    }

    public int updateNews(News news) {
        ContentValues values = new ContentValues();
        values.put("user_id", news.getUserId());
        values.put("content", news.getContent());
        values.put("title", news.getTitle());
        values.put("created_at", dateToString(news.getCreatedAt()));

        return db.update("NEWS_TABLE", values, "id = ?",
                new String[] { String.valueOf(news.getId()) });
    }

    public void deleteNews(News news) { db.delete("NEWS_TABLE", "id = ?",
                new String[] { String.valueOf(news.getId()) });
        db.close();
    }

}
