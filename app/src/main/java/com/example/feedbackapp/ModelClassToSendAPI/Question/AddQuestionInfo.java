package com.example.feedbackapp.ModelClassToSendAPI.Question;

public class AddQuestionInfo {
    private String QuestionContent;
    private String TopicId;

    public AddQuestionInfo(String questionContent, String topicId) {
        QuestionContent = questionContent;
        TopicId = topicId;
    }

    public String getQuestionContent() {
        return QuestionContent;
    }

    public void setQuestionContent(String questionContent) {
        QuestionContent = questionContent;
    }

    public String getTopicId() {
        return TopicId;
    }

    public void setTopicId(String topicId) {
        TopicId = topicId;
    }
}
