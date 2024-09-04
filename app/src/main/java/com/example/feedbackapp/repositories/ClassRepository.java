package com.example.feedbackapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassList;
import com.example.feedbackapp.RetrofitAPISetvice.ClassService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClassRepository {
    private static final String API_BASE_URL = "https://androidserverr2s.herokuapp.com/";
    private static final String accessToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRiYTk1N2FhNjBjN2M3YzNlYTEiLCJ0eXBlVXNlciI6ImFkbWluIiwiaWF0IjoxNjIxODU5NDMwfQ.-GljSrlUF4b3nl8ojzpk1xK1O-_MX5B6a31g8u5eTp8";
    private ClassService classService;
    private MutableLiveData<ClassList> classListLiveData;

    public ClassRepository() {
        classListLiveData = new MutableLiveData<>();
        //tạo 1 biến gson dùng cho service bên dưới
        Gson gson = new GsonBuilder()
                .setDateFormat("dd-MM-yyyy")
                .create();
        //khởi tạo service
        classService = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ClassService.class);

    }

    public void getClas(String accessToken) {
        classService.getClas(accessToken)
                .enqueue(new Callback<ClassList>() {
                    @Override
                    public void onResponse(Call<ClassList> call, Response<ClassList> response) {
                        if (response.body() != null) {
                            classListLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ClassList> call, Throwable t) {
                        classListLiveData.postValue(null);
                    }
                });
    }

    public LiveData<ClassList> getClassListLiveData() {

        return classListLiveData;
    }
}
