package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ModuleInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ModuleReturnByID;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.TraineeAssignment.TraineeAssignment;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.TraineeAssignment.TraineeAssignmentInfo;
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
import retrofit2.http.Path;

public interface TraineeAssignmentAPIService {
    //tạo 1 biến gson dùng cho service bên dưới
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();

    //khởi tạo service
    TraineeAssignmentAPIService traineeAssignmentAPIServices = new Retrofit.Builder()
            .baseUrl(BaseUrl.value())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(TraineeAssignmentAPIService.class);


    //service thêm mới trainee assignment
    @POST("api/trainee-assignment")
    Call<TraineeAssignmentInfo> addTraineeAssignment(
            @Header("Authorization") String token,
            @Body TraineeAssignment traineeAssignment
            );

    //service xóa 1 module theo id
}
