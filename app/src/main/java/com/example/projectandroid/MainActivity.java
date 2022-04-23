package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectandroid.newsApi.AsyncTaskNews;
import com.example.projectandroid.repository.NewsRepository;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //LogIn button
        Button button;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NewsList button
        Button button2;

        button2 = findViewById(R.id.NewsList);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openNewsList();
            }
        });

    }

    //button NewsList
    public void openNewsList() {
        Intent intent = new Intent(this, NewsFeedActivity.class);
        startActivity(intent);
    }

}