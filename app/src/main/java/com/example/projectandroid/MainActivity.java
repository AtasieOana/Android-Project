package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectandroid.models.News;
import com.example.projectandroid.models.User;
import com.example.projectandroid.repository.NewsRepository;
import com.example.projectandroid.repository.UserRepository;

import java.text.ParseException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //LogIn button
        Button button;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LogIn button
        button = findViewById(R.id.buttonLogIn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openLoginPage();
            }
        });


        //NewsList button
        Button button2;

        button2 = findViewById(R.id.NewsList);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openNewsList();
            }
        });

        NewsRepository newsRepository = new NewsRepository(this.getApplicationContext());
        newsRepository.addNews();

        System.out.println("acii");

        try {
            System.out.println(newsRepository.getAllNews());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*
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

        News news_show = new News();



        try {
            news_show = newsRepository.getNewsById(1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(news_show);

        String title;
        title = news_show.getTitle();
        String content;
        content = news_show.getContent();

        TextView tvSSID = (TextView) findViewById(R.id.textViewSSID);
        tvSSID.setText(title + "\n" + "\n" + content);

        TextView Title = (TextView) findViewById(R.id.buttonTitle);
        Title.setText(title);
*/

    }

    //button LogIn
    public void openLoginPage() {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    //button NewsList
    public void openNewsList() {
        Intent intent = new Intent(this, NewsList.class);
        startActivity(intent);
    }

}