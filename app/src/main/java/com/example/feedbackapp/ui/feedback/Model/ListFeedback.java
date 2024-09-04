package com.example.feedbackapp.ui.feedback.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListFeedback {
    @SerializedName("Id")
    @Expose
    private String Id;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("AdminId")
    @Expose
    private String adminId;
    @SerializedName("TypeFeedbackId")
    @Expose
    private String typeFeedbackId;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("AdminName")
    @Expose
    private String adminName;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getTypeFeedbackId() {
        return typeFeedbackId;
    }

    public void setTypeFeedbackId(String typeFeedbackId) {
        this.typeFeedbackId = typeFeedbackId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

}

