package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ModuleService {
    //service get lisst class
    @GET("api/module")
    Call<ListModule> getModule(@Header("Authorization") String authHeader);
}
