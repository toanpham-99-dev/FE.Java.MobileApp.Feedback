package com.example.feedbackapp.ui.feedback.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("QuestionContent")
    @Expose
    private String questionContent;
    private boolean isChecked=false;

    public String getId() {
        return id;
    }
    private boolean getState()
    {
        return this.isChecked;
    }
    private boolean setState()
    {
        return this.isChecked;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

}