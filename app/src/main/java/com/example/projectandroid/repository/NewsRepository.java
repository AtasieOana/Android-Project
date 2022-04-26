package com.example.projectandroid.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.projectandroid.database.AppDatabase;
import com.example.projectandroid.models.News;
import com.example.projectandroid.models.User;
import com.example.projectandroid.newsApi.ApiResult;
import com.example.projectandroid.newsApi.ResultList;
import com.example.projectandroid.newsApi.RetrofitClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private final SQLiteDatabase db;
    private final UserRepository userRepository;

    public NewsRepository(Context context) {
        AppDatabase appDatabase = new AppDatabase(context);
        db = appDatabase.getReadableDatabase();
        userRepository = new UserRepository(context);
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
    public int insertNews(News news) {
        ContentValues values = new ContentValues();
        values.put("user_id", news.getUserId());
        values.put("content", news.getContent());
        values.put("title", news.getTitle());
        values.put("created_at", dateToString(news.getCreatedAt()));

        return (int) db.insert("NEWS_TABLE", null, values);
    }

    public News getNewsById(int id) throws ParseException {
        Cursor cursor = db.query("NEWS_TABLE", new String[] { "id", "user_id",
                        "title", "content", "created_at" }, "id = ?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if(cursor.getCount()<=0){
            return null;
        }
        cursor.moveToFirst();

        News news = new News(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3), stringToDate(cursor.getString(4)));

        cursor.close();
        return news;
    }

    public News getNewsByTitleAndContent(String title, String content) throws ParseException {
        Cursor cursor = db.query("NEWS_TABLE", new String[] { "id", "user_id",
                        "title", "content", "created_at" }, "title = ? and content =?",
                new String[] { title, content }, null, null, null, null);

        if(cursor.getCount()<=0){
            return null;
        }
        cursor.moveToFirst();

        News news = new News(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3), stringToDate(cursor.getString(4)));

        cursor.close();
        return news;
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
        cursor.close();
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

    public void addNews(){
        Call<ResultList> call = RetrofitClient.getInstance().getMyApi().getApiResult("asHLgk5KDd4HrII4v2mQ-YdGwt3WP2BkmlxfbVPDyfHzitK8");

        call.enqueue(new Callback<ResultList>() {
            @Override
            public void onResponse(@NonNull Call<ResultList> call, @NonNull Response<ResultList> response) {
                ResultList apiResults = response.body();
                List<ApiResult> apiResultList = Objects.requireNonNull(apiResults).getApiResultList();
                for(ApiResult result: apiResultList){
                    try {
                        if(getNewsByTitleAndContent(result.getTitle(), result.getDescription()) == null && !result.getAuthor().equals("")){
                            User user = userRepository.getUserByName(result.getAuthor());
                            int user_id;
                            if(user != null){
                                user_id = user.getId();
                            }
                            else{
                                User newUser = new User();
                                newUser.setName(result.getAuthor());
                                user_id = userRepository.insertUser(newUser);
                            }
                            Date date = stringToDate(result.getPublished());
                            insertNews(new News(1, user_id, result.getTitle(), result.getDescription(), date));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResultList> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}
