package com.example.feedbackapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassList;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.ListTopic;
import com.example.feedbackapp.RetrofitAPISetvice.ClassService;
import com.example.feedbackapp.RetrofitAPISetvice.TopicService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopicRepository {
    private static final String API_BASE_URL = "https://androidserverr2s.herokuapp.com/";
    private static final String accessToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRiYTk1N2FhNjBjN2M3YzNlYTEiLCJ0eXBlVXNlciI6ImFkbWluIiwiaWF0IjoxNjIxODU5NDMwfQ.-GljSrlUF4b3nl8ojzpk1xK1O-_MX5B6a31g8u5eTp8";
    private TopicService topicService;
    private MutableLiveData<ListTopic> topicListLiveData;

    public TopicRepository() {
        topicListLiveData = new MutableLiveData<>();
        //tạo 1 biến gson dùng cho service bên dưới
        Gson gson = new GsonBuilder()
                .setDateFormat("dd-MM-yyyy")
                .create();
        //khởi tạo service
        topicService = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(TopicService.class);

    }

    public void getTopic(String accessToken) {
        topicService.getTopic(accessToken)
                .enqueue(new Callback<ListTopic>() {
                    @Override
                    public void onResponse(Call<ListTopic> call, Response<ListTopic> response) {
                        if (response.body() != null) {
                            topicListLiveData.postValue(response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<ListTopic> call, Throwable t) {
                        topicListLiveData.postValue(null);
                    }
                });
    }

    public LiveData<ListTopic> getTopicListLiveData() {

        return topicListLiveData;
    }
}
