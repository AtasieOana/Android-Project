package com.example.projectandroid.newsApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultList {

    @SerializedName("news")
    private List<ApiResult> apiResultList;

    public List<ApiResult> getApiResultList() {
        return apiResultList;
    }

    public void setApiResultList(List<ApiResult> apiResultList) {
        this.apiResultList = apiResultList;
    }

    @Override
    public String toString() {
        return "ResultList{" +
                "apiResultList=" + apiResultList +
                '}';
    }
}
