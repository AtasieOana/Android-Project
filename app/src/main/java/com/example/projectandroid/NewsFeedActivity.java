package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectandroid.models.News;

import java.util.ArrayList;


public class NewsFeedActivity extends AppCompatActivity{

    Button addNewsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        NewsFragment newsFragment =  NewsFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layoutNewsFragment,newsFragment,"news_fragment");
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

        initSearch(newsFragment);
    }


    private void initSearch(NewsFragment newsFragment){
        SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText, newsFragment);
                return false;
            }
        });
    }

    private void filter(String text, NewsFragment newsFragment) {
        newsFragment.filter(text);
    }

}
