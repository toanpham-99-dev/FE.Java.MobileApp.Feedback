package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassList;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ListClass;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.UserInfo.BaseUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

public interface ClassAPIService {
    //tạo 1 biến gson dùng cho service bên dưới
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();

    //khởi tạo service
    ClassAPIService classAPIService = new Retrofit.Builder()
            .baseUrl(BaseUrl.value())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ClassAPIService.class);

    //service lấy tất cả class
    @GET("/api/class")
    Call<ListClass> getAllClass(@Header("Authorization") String token);

    //service lấy 1 class để edit
    @GET("/api/class/{id}")
    Call<ClassInfo> getClass(@Header("Authorization") String token, @Path("id") String id);

    //service edit class
    @PUT("/api/class/{id}")
    Call<ClassInfo> editClass(@Header("Authorization") String token, @Path("id") String id, @Body Classs classs);

    //service add class
    @POST("api/class")
    Call<ClassList> addClass(@Header("Authorization") String authHeader, @Body Classs classs);

    //service xóa 1 class theo id
    @DELETE("/api/class/{id}")
    Call<Classs> deleteClass(@Header("Authorization") String token, @Path("id") String id);
}
