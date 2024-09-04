package com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback;

public class FeedbackInfo {
    private String message;
    private boolean isSuccess;
    private Feedback feedback;

    // Constructor
    public FeedbackInfo(String message, boolean isSuccess, Feedback feedback){
        this.message = message;
        this.isSuccess = isSuccess;
        this.feedback = feedback;
    }


    // GET
    public String getMessage(){return this.message;}
    public boolean getIsSuccess(){return this.isSuccess;}
    public Feedback getFeedback(){return this.feedback;}
}
