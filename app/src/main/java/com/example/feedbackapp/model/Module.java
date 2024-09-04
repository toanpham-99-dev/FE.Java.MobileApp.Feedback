package com.example.feedbackapp.model;

public class Module {
    int ModuleId;
    String AdminID;
    String ModuleName;
    String StartTime;
    String EndTime;
    Boolean IsDeleted;
    String FeedbackStartTime;
    String FeedbackEndTime;
    int FeedbackID;

    public Module(int moduleId, String adminID, String moduleName, String startTime, String endTime, Boolean isDeleted, String feedbackStartTime, String feedbackEndTime, int feedbackID) {
        ModuleId = moduleId;
        AdminID = adminID;
        ModuleName = moduleName;
        StartTime = startTime;
        EndTime = endTime;
        IsDeleted = isDeleted;
        FeedbackStartTime = feedbackStartTime;
        FeedbackEndTime = feedbackEndTime;
        FeedbackID = feedbackID;
    }

    public Module(int moduleId, String moduleName) {
        ModuleId = moduleId;
        ModuleName = moduleName;
    }

    public int getModuleId() {
        return ModuleId;
    }

    public void setModuleId(int moduleId) {
        ModuleId = moduleId;
    }

    public String getAdminID() {
        return AdminID;
    }

    public void setAdminID(String adminID) {
        AdminID = adminID;
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

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
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

    public int getFeedbackID() {
        return FeedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        FeedbackID = feedbackID;
    }
}
