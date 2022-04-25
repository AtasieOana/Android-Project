package com.example.projectandroid;

import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.example.projectandroid.models.News;
import com.example.projectandroid.models.User;
import com.example.projectandroid.repository.NewsRepository;
import com.example.projectandroid.repository.UserRepository;

import java.text.ParseException;

public class NewsDetailsActivity extends AppCompatActivity {

    TextView title, content;
    ImageView image;
    User user; // login user
    NewsRepository newsRepository;
    UserRepository userRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);

        userRepository = new UserRepository(this);
        newsRepository = new NewsRepository(this);

        SessionManagement sessionManagement = new SessionManagement(NewsDetailsActivity.this);
        String emailUser = sessionManagement.getSession();

        user = userRepository.getUserByEmail(emailUser);

        Intent intent = getIntent();
        int newsId = intent.getIntExtra("newsId",0);

        News news = new News();
        try {
            news = newsRepository.getNewsById(newsId);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        title = findViewById(R.id.newsTitle);
        content = findViewById(R.id.productDescription);
        title.setText(news.getTitle());
        content.setText(news.getContent());
    }
}