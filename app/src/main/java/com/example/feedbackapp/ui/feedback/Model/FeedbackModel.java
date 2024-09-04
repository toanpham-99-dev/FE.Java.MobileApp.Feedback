package com.example.feedbackapp.ui.feedback.Model;

public class FeedbackModel {


    public FeedbackModel() {

    }

    private String feedbackId;
    private String feedbackTitle;
    private String adminId;


    public FeedbackModel(String feedbackId, String feedbackTitle, String adminId) {
        this.feedbackId = feedbackId;
        this.feedbackTitle = feedbackTitle;
        this.adminId = adminId;
    }
    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }




}
