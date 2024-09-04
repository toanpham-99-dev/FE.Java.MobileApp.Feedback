package com.example.feedbackapp.ui.feedback.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListTypeFeedbackModel {

    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("TypeName")
    @Expose
    private String typeName;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}

