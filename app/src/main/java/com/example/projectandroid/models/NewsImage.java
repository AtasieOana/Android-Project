package com.example.projectandroid.models;

import android.graphics.Bitmap;

import java.util.Date;

public class NewsImage {

    int id;
    int news_id;
    Bitmap image;

    public NewsImage(int id, int news_id, Bitmap image) {
        this.id = id;
        this.news_id = news_id;
        this.image = image;
    }

    public NewsImage(int news_id, Bitmap image) {
        this.news_id = news_id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "NewsImage{" +
                "id=" + id +
                ", news_id=" + news_id +
                ", image=" + image +
                '}';
    }
}
