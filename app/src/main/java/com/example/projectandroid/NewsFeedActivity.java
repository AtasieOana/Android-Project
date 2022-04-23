package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectandroid.newsApi.AsyncTaskNews;


public class NewsFeedActivity extends AppCompatActivity{

    Button addNewsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        Fragment fragment = NewsFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layoutNewsFragment,fragment,"news_fragment");
        transaction.commit();

        addNewsButton = findViewById(R.id.addButton);

        System.out.println("main activity");

        addNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to NewNewsActivity
                Intent intent = new Intent(getApplicationContext(), NewNewsActivity.class);
                startActivity(intent);
            }
        });
    }



}
