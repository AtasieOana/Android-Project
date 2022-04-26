package com.example.projectandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectandroid.models.News;
import com.example.projectandroid.models.User;
import com.example.projectandroid.repository.UserRepository;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    ArrayList<News> newsArrayList;
    OnItemListener onItemListener;
    UserRepository userRepository;
    Context context;

    public CustomAdapter(ArrayList<News> newsArrayList, OnItemListener onItemListener,Context context) {
        this.newsArrayList = newsArrayList;
        this.onItemListener = onItemListener;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull  ViewGroup viewgroup, int i) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.news_layout_in_list,viewgroup,false);

        this.userRepository = new UserRepository(context);

        return new CustomViewHolder(view);
    }

    public void filterList(ArrayList<News> newsFiltered) {
        newsArrayList = newsFiltered;
        System.out.println(newsFiltered);
        System.out.println(newsArrayList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder customViewHolder, int i) {
        News news = newsArrayList.get(i);

        User user = userRepository.getUserById(news.getUserId());

        String writtenBy = "Written by " + user.getName();
        customViewHolder.newsAuthor.setText(writtenBy);
        customViewHolder.newsTitle.setText(news.getTitle());

        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onItemClick(newsArrayList.get(customViewHolder.getBindingAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView newsTitle, newsAuthor;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsAuthor = itemView.findViewById(R.id.newsAuthor);

        }
    }

    public interface OnItemListener{
        void onItemClick(News news);
    }
}
