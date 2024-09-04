package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback.ListFeedbackInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Login.LoginInfo;
import com.example.feedbackapp.ModelClassToSendAPI.Login.LoginValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface    FeedbackAPIServices {
    //tạo 1 biến gson dùng cho service bên dưới
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();

    //khởi tạo service
    FeedbackAPIServices feedbackAPIServices = new Retrofit.Builder()
            .baseUrl("https://androidserverr2s.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(FeedbackAPIServices.class);


        //service lấy danh sách các feedback
        @GET("api/feedback/")
        Call<ListFeedbackInfo> getListFeedback(
                @Header("Authorization") String authToken
        );
}
