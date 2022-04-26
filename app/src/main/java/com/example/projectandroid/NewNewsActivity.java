package com.example.projectandroid;


import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectandroid.models.News;
import com.example.projectandroid.models.User;
import com.example.projectandroid.repository.NewsRepository;
import com.example.projectandroid.repository.UserRepository;

import java.util.Date;

public class NewNewsActivity extends AppCompatActivity {

    EditText titleInput, contentInput;
    Button addNewsButton;
    UserRepository userRepository;
    NewsRepository newsRepository;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_news);

        titleInput = findViewById(R.id.titleInput);
        contentInput = findViewById(R.id.contentInput);
        addNewsButton = findViewById(R.id.addNewsButton);

        userRepository = new UserRepository(this);
        newsRepository = new NewsRepository(this);

        SessionManagement sessionManagement = new SessionManagement(NewNewsActivity.this);
        String emailUser = sessionManagement.getSession();

        user = userRepository.getUserByEmail(emailUser);

        addNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValidation();
            }
        });
    }

    public void setValidation() {
        // title invalid
        if (titleInput.getText().toString().isEmpty()) {
            Toast.makeText(NewNewsActivity.this, "Please write a title", Toast.LENGTH_SHORT).show();
        }
        else{
            // content invalid
            if (contentInput.getText().toString().isEmpty()) {
                Toast.makeText(NewNewsActivity.this, "Please write a content", Toast.LENGTH_SHORT).show();
            } else if (contentInput.getText().length() < 20) {
                Toast.makeText(NewNewsActivity.this, "Content needs to have at least 20 characters", Toast.LENGTH_SHORT).show();
            } else {
                News news = new News(user.getId(), titleInput.getText().toString(),contentInput.getText().toString(), new Date());
                int newsId = newsRepository.insertNews(news);
                sendNotificationsWithDeepLinks(newsId);
                Toast.makeText(NewNewsActivity.this, "News added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewNewsActivity.this, NewsFeedActivity.class);
                startActivity(intent);
            }
        }

    }


    public void sendNotificationsWithDeepLinks(int newsId){
        NotificationGenerator.openActivityNotification(this, newsId);
    }
}
