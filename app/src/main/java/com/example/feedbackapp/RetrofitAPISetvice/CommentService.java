package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Answer.AnswerInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Comment.CommentInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Comment.ListComment;
import com.example.feedbackapp.ModelClassToSendAPI.Answer.ListAnswer;
import com.example.feedbackapp.ModelClassToSendAPI.Comment;
import com.example.feedbackapp.UserInfo.BaseUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CommentService {
    //tạo 1 biến gson dùng cho service bên dưới
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();

    //khởi tạo service
    CommentService commentService = new Retrofit.Builder()
            .baseUrl(BaseUrl.value())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CommentService.class);

    //service thêm và lấy danh sách comment
    @GET("api/comment")
    Call<ListComment> getComment(@Header("Authorization") String authHeader);
    @POST("api/comment")
    Call<CommentInfo> postComment(@Header("Authorization") String authHeader, @Body Comment comment);
}
