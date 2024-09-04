package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Answer.AnswerInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Answer.ListAnswerInfor;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassList;
import com.example.feedbackapp.ModelClassToSendAPI.Answer.Answer;
import com.example.feedbackapp.ModelClassToSendAPI.Answer.ListAnswer;
import com.example.feedbackapp.UserInfo.BaseUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AnswerService {
    //tạo 1 biến gson dùng cho service bên dưới
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();

    //khởi tạo service
    AnswerService answerService = new Retrofit.Builder()
            .baseUrl(BaseUrl.value())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AnswerService.class);

    //service thêm và lấy danh sách answer
    @GET("api/answer")
    Call<ListAnswerInfor> getAnswer(@Header("Authorization") String authHeader);

    @POST("api/answer")
    Call<AnswerInfo> postAnswer(@Header("Authorization") String authHeader, @Body ListAnswer listAnswer);
}
