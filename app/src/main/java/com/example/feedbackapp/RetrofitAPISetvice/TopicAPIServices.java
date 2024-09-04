package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Question.QuestionInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.ListTopic;
import com.example.feedbackapp.UserInfo.BaseUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface TopicAPIServices {
    //tạo 1 biến gson dùng cho service bên dưới
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();

    //khởi tạo service
    TopicAPIServices TOPIC_API_SERVICES = new Retrofit.Builder()
            .baseUrl(BaseUrl.value())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(TopicAPIServices.class);

    //service lấy danh sách Topic
    @GET("/api/topic")
    Call<ListTopic> getTopicList(@Header("Authorization") String authHeader);
}
