package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ClassService {
    //service get lisst class
    @GET("api/class")
    Call<ClassList> getClas(@Header("Authorization") String authHeader);
}
