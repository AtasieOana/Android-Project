package com.example.projectandroid;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.example.projectandroid.models.News;
import com.example.projectandroid.models.NewsImage;
import com.example.projectandroid.models.User;
import com.example.projectandroid.repository.NewsRepository;
import com.example.projectandroid.repository.NewsImageRepository;
import com.example.projectandroid.repository.UserRepository;

import java.text.ParseException;

public class NewsDetailsActivity extends AppCompatActivity {

    TextView title, content;
    ImageView image;
    User user; // login user
    NewsRepository newsRepository;
    UserRepository userRepository;
    NewsImageRepository newsImageRepository;

    Button goBackToFeed;
    Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);

        userRepository = new UserRepository(this);
        newsRepository = new NewsRepository(this);
        newsImageRepository = new NewsImageRepository(this);

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

        NewsImage newsImage = newsImageRepository.getPhotoByNewsId(newsId);
        title = findViewById(R.id.newsTitle);
        content = findViewById(R.id.productDescription);
        image = findViewById(R.id.productPhoto);

        title.setText(news.getTitle());
        content.setText(news.getContent());

        if(newsImage != null && newsImage.getImage() != null){
            image.setImageBitmap(newsImage.getImage());
        }
        else{
            image.setImageResource(android.R.drawable.ic_menu_gallery);

        }

        String content1 = news.getContent();

        shareButton = findViewById(R.id.Share);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(content1);
            }
        });

        goBackToFeed = findViewById(R.id.backToFeed);

        goBackToFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to NewsFeedActivity
                Intent intent = new Intent(getApplicationContext(), NewsFeedActivity.class);
                startActivity(intent);
            }
        });
    }

    public void share(String x) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, x);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

    }
}



