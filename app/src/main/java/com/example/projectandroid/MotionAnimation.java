package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectandroid.NewsFeedActivity;
import com.example.projectandroid.R;
import com.example.projectandroid.newsApi.AsyncTaskNews;
import com.example.projectandroid.repository.NewsRepository;


public class MotionAnimation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.motion_layout);


    }

}
