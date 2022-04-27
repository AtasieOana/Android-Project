package com.example.projectandroid.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.projectandroid.database.AppDatabase;
import com.example.projectandroid.models.News;
import com.example.projectandroid.models.NewsImage;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsImageRepository {

    private final SQLiteDatabase db;

    public byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public NewsImageRepository(Context context) {
        AppDatabase appDatabase = new AppDatabase(context);
        db = appDatabase.getReadableDatabase();
    }

    public int insertPhoto(NewsImage newsImage) {
        byte[] image = getBytes(newsImage.getImage());
        ContentValues values = new ContentValues();
        values.put("news_id",  newsImage.getNews_id());
        values.put("photo",   image);

        return (int) db.insert("PHOTO_TABLE", null, values);
    }

    public NewsImage getPhotoByNewsId(int newsId) {
        Cursor cursor = db.query("PHOTO_TABLE", new String[] { "id", "news_id",
                        "photo"}, "news_id = ?",
                new String[] { String.valueOf(newsId) }, null, null, null, null);

        if(cursor.getCount()<=0){
            return null;
        }

        cursor.moveToFirst();

        byte[] image = cursor.getBlob(2);

        NewsImage newsImage = new NewsImage(cursor.getInt(0), cursor.getInt(1), getImage(image));
        cursor.close();
        return newsImage;
    }

}
