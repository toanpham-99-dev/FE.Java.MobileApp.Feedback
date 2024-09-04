package com.example.feedbackapp.ModelClassToReceiveFromAPI.Answer;

import com.example.feedbackapp.ModelClassToSendAPI.Answer.Answer;

import java.util.ArrayList;

public class ListAnswerInfor {
    private boolean isSuccess;
    private String message;
    private ArrayList<Answer> listAnswer;
    public ListAnswerInfor(boolean isSuccess, String message, ArrayList<Answer> listAnswer) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.listAnswer = listAnswer;
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

    public ArrayList<Answer> getListAnswer() {
        return this.listAnswer;
    }

    public void setListAnswer(ArrayList<Answer> listAnswer) {
        this.listAnswer = listAnswer;
    }
}
