package com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic;

import java.util.ArrayList;

public class ListTopic {
    private boolean isSuccess;
     private String message;
     private ArrayList<Topic> listTopic;

    public ListTopic(boolean isSuccess, String message, ArrayList<Topic> listTopic) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.listTopic = listTopic;
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

    public ArrayList<Topic> getListTopic() {
        return listTopic;
    }

    public void setListTopic(ArrayList<Topic> listTopic) {
        this.listTopic = listTopic;
    }
}
