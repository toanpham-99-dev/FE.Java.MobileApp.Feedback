package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Auth.ListAdminInfo;
import com.example.feedbackapp.UserInfo.BaseUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AuthAPIService {
    //tạo 1 biến gson dùng cho service bên dưới
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();

    //khởi tạo service
    AuthAPIService authAPIServices = new Retrofit.Builder()
            .baseUrl(BaseUrl.value())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AuthAPIService.class);



    //service lấy thông tin tất cả tài khoản của admin
    @GET("/api/auth/getAdmin")
    Call<ListAdminInfo> getListAdmin(@Header("Authorization") String authToken);
}
