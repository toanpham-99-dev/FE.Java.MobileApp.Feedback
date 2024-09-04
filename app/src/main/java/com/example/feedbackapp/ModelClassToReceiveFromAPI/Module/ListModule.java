package com.example.feedbackapp.ModelClassToReceiveFromAPI.Module;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;

import java.util.ArrayList;

public class ListModule {
    private Boolean isSuccess;
    private String message;
    private ArrayList<Module> listModule;

    public ListModule(Boolean isSuccess, String message, ArrayList<Module> listModule) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.listModule = listModule;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Module> getListModule() {
        return listModule;
    }

    public void setListModule(ArrayList<Module> listModule) {
        this.listModule = listModule;
    }
}
