package com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback;

import java.util.ArrayList;

public class ListFeedbackInfo {
    private String message = "";
    private boolean isSuccess = true;
    private ArrayList<Feedback> listFeedback = new ArrayList<Feedback>();


    // GET
    public String getMessage(){return this.message;}
    public boolean getIsSuccess(){return this.isSuccess;}
    public ArrayList<Feedback> getListFeedback(){return this.listFeedback;}
}
