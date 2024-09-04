package com.example.feedbackapp.ModelClassToReceiveFromAPI.Question;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.Assignment;

import java.util.ArrayList;

public class QuestionInfo {
    private boolean isSuccess;
    private String message;
    private ArrayList<Question> listQuestion;

    public QuestionInfo(boolean isSuccess, String message, ArrayList<Question> listQuestion) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.listQuestion = listQuestion;
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

    public ArrayList<Question> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(ArrayList<Question> listQuestion) {
        this.listQuestion = listQuestion;
    }
}
