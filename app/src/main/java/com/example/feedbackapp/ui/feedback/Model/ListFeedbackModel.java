package com.example.feedbackapp.ui.feedback.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListFeedbackModel {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("listFeedback")
    @Expose
    private List<ListFeedback> listFeedback = null;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ListFeedback> getListFeedback() {
        return listFeedback;
    }

    public void setListFeedback(List<ListFeedback> listFeedback) {
        this.listFeedback = listFeedback;
    }

}

