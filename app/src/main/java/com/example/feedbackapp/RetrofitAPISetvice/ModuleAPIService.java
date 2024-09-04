package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Login.LoginInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ModuleInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ModuleReturnByID;
import com.example.feedbackapp.ModelClassToSendAPI.Login.LoginValue;
import com.example.feedbackapp.UserInfo.BaseUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ModuleAPIService {

    //tạo 1 biến gson dùng cho service bên dưới
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();

    //khởi tạo service
    ModuleAPIService moduleAPIServices = new Retrofit.Builder()
            .baseUrl(BaseUrl.value())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ModuleAPIService.class);

    //service lấy tất cả module
    @GET("/api/module")
    Call<ListModule> getAllModule(@Header("Authorization") String token);

    //service lấy 1 module theo id
    @GET("/api/module/{id}")
    Call<ModuleReturnByID> getModule(@Path("id") String id);

    //service thêm mới module
    @POST("api/module")
    Call<ModuleInfo> addModule(
            @Header("Authorization") String token,
            @Body Module module
    );

    //service update
    @PUT("api/module/{id}")
    Call<ModuleInfo> editModule(
            @Header("Authorization") String token,
            @Path("id") String id,
            @Body Module module
    );

    //service xóa 1 module theo id
    @GET("/api/module/{id}")
    Call<ModuleInfo> getModule(@Header("Authorization") String token, @Path("id") String id);

    //service xóa 1 module theo id
    @DELETE("/api/module/{id}")
    Call<Module> deleteModule(@Header("Authorization") String token, @Path("id") String id);
}
