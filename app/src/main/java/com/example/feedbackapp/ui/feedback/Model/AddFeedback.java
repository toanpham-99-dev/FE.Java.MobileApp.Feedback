package com.example.feedbackapp.ui.feedback.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AddFeedback {
    private String Title;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTypeFeedbackId() {
        return TypeFeedbackId;
    }

    public void setTypeFeedbackId(String typeFeedbackId) {
        TypeFeedbackId = typeFeedbackId;
    }

    public List<String> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<String> listQuestion) {
        this.listQuestion = listQuestion;
    }

    private String TypeFeedbackId;
    private List<String> listQuestion;

    public AddFeedback(String title, String typeFeedbackId, List<String> listQuestion) {
        Title = title;
        TypeFeedbackId = typeFeedbackId;
        this.listQuestion = listQuestion;
    }


}
