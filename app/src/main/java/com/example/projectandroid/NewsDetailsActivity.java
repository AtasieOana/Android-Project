package com.example.projectandroid;

import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.example.projectandroid.models.News;
import com.example.projectandroid.repository.NewsRepository;
import com.example.projectandroid.models.News;
import java.text.ParseException;

public class NewsDetailsActivity extends AppCompatActivity {

    TextView title, content;
    ImageView image;
    private NewsRepository newsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);
        SessionManagement sessionManagement = new SessionManagement(NewsDetailsActivity.this);
        String emailUser = sessionManagement.getSession();
        Intent intent = getIntent();
        int newsId = intent.getIntExtra("idProduct",0);
       // dbHelper = new DBHelper(this);
       // try {
         //   News news = newsRepository.getNewsById(newsId);
       // } catch (ParseException e) {
         //   e.printStackTrace();
       // }
        News news = new News();
        title = findViewById(R.id.newsTitle);
        content = findViewById(R.id.productDescription);
        title.setText(news.getTitle());
        content.setText(news.getContent());
    }
}