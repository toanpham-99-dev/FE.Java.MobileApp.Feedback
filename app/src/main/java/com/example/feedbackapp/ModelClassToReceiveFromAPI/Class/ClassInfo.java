package com.example.feedbackapp.ModelClassToReceiveFromAPI.Class;

public class ClassInfo {
    Classs class_res;
    boolean isSuccess;
    String message;

    public ClassInfo(Classs class_res, boolean isSuccess, String message) {
        this.class_res = class_res;
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public Classs getClass_res() {
        return class_res;
    }

    public void setClass_res(Classs class_res) {
        this.class_res = class_res;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
