package com.example.feedbackapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.RetrofitAPISetvice.ModuleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModuleRepository {
    private static final String API_BASE_URL = "https://androidserverr2s.herokuapp.com/";
    private static final String accessToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRiYTk1N2FhNjBjN2M3YzNlYTEiLCJ0eXBlVXNlciI6ImFkbWluIiwiaWF0IjoxNjIxODU5NDMwfQ.-GljSrlUF4b3nl8ojzpk1xK1O-_MX5B6a31g8u5eTp8";
    private ModuleService moduleService;
    private MutableLiveData<ListModule> moduleListLiveData;

    public ModuleRepository() {
        moduleListLiveData = new MutableLiveData<>();
        //tạo 1 biến gson dùng cho service bên dưới
        Gson gson = new GsonBuilder()
                .setDateFormat("dd-MM-yyyy")
                .create();
        //khởi tạo service
        moduleService = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ModuleService.class);

    }

    public void getModule(String accessToken) {
        moduleService.getModule(accessToken)
                .enqueue(new Callback<ListModule>() {
                    @Override
                    public void onResponse(Call<ListModule> call, Response<ListModule> response) {
                        if (response.body() != null) {
                            moduleListLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ListModule> call, Throwable t) {
                        moduleListLiveData.postValue(null);
                    }
                });
    }

    public LiveData<ListModule> getModuleListLiveData() {

        return moduleListLiveData;
    }
}
