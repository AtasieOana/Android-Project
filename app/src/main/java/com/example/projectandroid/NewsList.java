package com.example.projectandroid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectandroid.models.News;
import com.example.projectandroid.repository.NewsRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class NewsList extends AppCompatActivity {

    private NewsRepository newsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        newsRepository = new NewsRepository(this.getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomAdapter(generateData()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }



    private List<String> generateData() {
        List<String> dataa = new ArrayList<>();
        List<News> news = new ArrayList<News>();
        News n = null;
        String data;
        int x;
        try {
            news = newsRepository.getAllNews();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        x = news.size();
        for (int i = 1; i <= x; i++) {
            try {
                n = newsRepository.getNewsById(i);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            data = n.getTitle();
            
            dataa.add(String.valueOf(data)+String.valueOf(x));
        }

        return dataa;
    }

/*
    private List<String> generateData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(String.valueOf(i) + "th Element");
        }
        return data;
    }
*/


}
