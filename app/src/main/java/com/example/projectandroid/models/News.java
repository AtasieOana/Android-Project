package com.example.projectandroid.models;

import java.util.Date;

public class News {

    int id;
    int user_id;
    String title;
    String content;
    Date created_at;

    // constructors
    public News() {
    }

    public News(int id, int user_id, String title, String content, Date created_at) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(Date created_at){
        this.created_at = created_at;
    }

    // getters
    public int getId() {
        return this.id;
    }

    public int getUserId() {
        return this.user_id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public Date getCreatedAt() {
        return this.created_at;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
