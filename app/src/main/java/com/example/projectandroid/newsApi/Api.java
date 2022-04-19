package com.example.projectandroid.newsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Api {

    String BASE_URL = "https://api.currentsapi.services/v1/";


    @GET("latest-news")
    Call<ResultList> getApiResult(@Header("Authorization") String authHeader);
}
