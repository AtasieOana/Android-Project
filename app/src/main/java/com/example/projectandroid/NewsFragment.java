package com.example.projectandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectandroid.models.News;
import com.example.projectandroid.repository.NewsRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

public class NewsFragment extends Fragment implements CustomAdapter.OnItemListener{

    private ArrayList<News> newsList = new ArrayList<>();
    private NewsRepository newsRepository;
    CustomAdapter adapter;

    public NewsFragment() {
    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        newsRepository = new NewsRepository(this.getContext());
        initList();
        initRecycleView(view);
        return view;
    }

    private void initRecycleView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recViewNews);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CustomAdapter(newsList,this, this.getContext());
        recyclerView.setAdapter(adapter);
    }

    private void initList() {
        try {
            newsList = (ArrayList<News>) newsRepository.getAllNews();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(News news) {
        Intent intent = new Intent(getContext(), NewsDetailsActivity.class);
        intent.putExtra("newsId",news.getId());
        requireContext().startActivity(intent);    }
}

