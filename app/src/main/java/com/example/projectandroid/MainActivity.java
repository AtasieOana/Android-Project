package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.projectandroid.models.News;
import com.example.projectandroid.models.User;
import com.example.projectandroid.repository.NewsRepository;
import com.example.projectandroid.repository.UserRepository;

import java.text.ParseException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // database initial test
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setName("test");
        user.setPassword("password");

        UserRepository userRepository = new UserRepository(this.getApplicationContext());

        userRepository.insertUser(user);

        System.out.println("I'm here");

        System.out.println(userRepository.getUserByEmail("test@gmail.com"));

        News news = new News();
        news.setTitle("Title news");
        news.setCreatedAt(new Date());
        news.setContent("This is the content");
        news.setUserId(1);

        NewsRepository newsRepository = new NewsRepository(this.getApplicationContext());

        newsRepository.insertNews(news);

        System.out.println("I'm here x2");

        try {
            System.out.println(newsRepository.getAllNews());
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}