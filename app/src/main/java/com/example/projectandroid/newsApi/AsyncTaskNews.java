package com.example.projectandroid.newsApi;

import android.content.Context;
import android.os.AsyncTask;

import com.example.projectandroid.repository.NewsRepository;

public class AsyncTaskNews extends AsyncTask<Void, Void, String> {

    private final NewsRepository newsRepository;

    public AsyncTaskNews (Context context){
        newsRepository = new NewsRepository(context);
    }

    @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... arg0)
        {
            newsRepository.addNews();
            System.out.println("async part");
            return "News added";
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

        }
}

