package com.example.feedbackapp.ModelClassToReceiveFromAPI.Question;

public class Question {
    private String Id;
    private String TopicId;
    private String TopicName;
    private String QuestionContent;
    private Boolean isDeleted;
    private Boolean isUse;


    public Question(String id, String topicId, String topicName, String questionContent, Boolean isDeleted, Boolean isUse) {
        Id = id;
        TopicId = topicId;
        TopicName = topicName;
        QuestionContent = questionContent;
        this.isDeleted = isDeleted;
        this.isUse = isUse;
    }

    public String getId() {
        return Id;
    }

    public String getTopicId() {
        return TopicId;
    }

    public String getTopicName() {
        return TopicName;
    }

    public String getQuestionContent() {
        return QuestionContent;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setTopicId(String topicId) {
        TopicId = topicId;
    }

    public void setTopicName(String topicName) {
        TopicName = topicName;
    }

    public void setQuestionContent(String questionContent) {
        QuestionContent = questionContent;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getUse() {
        return isUse;
    }

    public void setUse(Boolean use) {
        isUse = use;
    }
}
