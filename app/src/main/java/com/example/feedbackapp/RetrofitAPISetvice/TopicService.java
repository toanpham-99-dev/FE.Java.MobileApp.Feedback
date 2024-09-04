package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassList;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.ListTopic;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface TopicService {
    //service get lisst class
    @GET("api/topic")
    Call<ListTopic> getTopic(@Header("Authorization") String authHeader);
}
