package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Question.QuestionInfo;
import com.example.feedbackapp.ModelClassToSendAPI.Assignment.EditAssignmentInfo;
import com.example.feedbackapp.ModelClassToSendAPI.Question.LoadQuestionByTopicIdInfo;
import com.example.feedbackapp.ModelClassToSendAPI.Question.AddQuestionInfo;
import com.example.feedbackapp.UserInfo.BaseUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface QuestionAPIServices {
    //tạo 1 biến gson dùng cho service bên dưới
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();

    //khởi tạo service
    QuestionAPIServices QUESTION_API_SERVICES = new Retrofit.Builder()
            .baseUrl(BaseUrl.value())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(QuestionAPIServices.class);

    //service lấy danh sách Question
    @GET("/api/question")
    Call<QuestionInfo> getQuestionList(@Header("Authorization") String authHeader);

    //Lấy danh sach question theo topic
    @POST("/api/question/filter")
    Call<QuestionInfo> getQuestionListByTopicId(@Header("Authorization") String authHeader,
                                                @Body LoadQuestionByTopicIdInfo topicId);

    //Add new question
    @POST("/api/question")
    Call<ResponseBody> addNewQuestion(@Header("Authorization") String authHeader, @Body AddQuestionInfo addQuestionInfo);

    //service sửa 1 question theo id
    @PUT("/api/question/{id}")
    Call<ResponseBody> editQuestion(@Header("Authorization") String token,
                                      @Path("id") String id,
                                      @Body AddQuestionInfo editQuestionInfo);

    //service xóa 1 question theo id
    @DELETE("/api/question/{id}")
    Call<ResponseBody> deleteQuestion(@Header("Authorization") String token, @Path("id") String id);

}
