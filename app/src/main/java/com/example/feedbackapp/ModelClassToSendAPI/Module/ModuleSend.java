package com.example.feedbackapp.ModelClassToSendAPI.Module;

public class ModuleSend {

    /**
     * Class gửi đi khác với nhận về, vì gửi đi chỉ 7 biến, nhận về 11 biến
     */
    private String ModuleName;
    private String StartTime;
    private String EndTime;
    private Boolean AdminId;
    private String FeedbackId;
    private String FeedbackStartTime;
    private String FeedbackEndTime;

    public ModuleSend(String moduleName, String startTime, String endTime, Boolean adminId, String feedbackId, String feedbackStartTime, String feedbackEndTime) {
        ModuleName = moduleName;
        StartTime = startTime;
        EndTime = endTime;
        AdminId = adminId;
        FeedbackId = feedbackId;
        FeedbackStartTime = feedbackStartTime;
        FeedbackEndTime = feedbackEndTime;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public Boolean getAdminId() {
        return AdminId;
    }

    public void setAdminId(Boolean adminId) {
        AdminId = adminId;
    }

    public String getFeedbackId() {
        return FeedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        FeedbackId = feedbackId;
    }

    public String getFeedbackStartTime() {
        return FeedbackStartTime;
    }

    public void setFeedbackStartTime(String feedbackStartTime) {
        FeedbackStartTime = feedbackStartTime;
    }

    public String getFeedbackEndTime() {
        return FeedbackEndTime;
    }

    public void setFeedbackEndTime(String feedbackEndTime) {
        FeedbackEndTime = feedbackEndTime;
    }
}
