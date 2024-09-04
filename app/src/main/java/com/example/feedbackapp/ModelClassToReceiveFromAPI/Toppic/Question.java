package com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic;

public class Question {
   private String Id;
    private String QuestionContent;

    public Question(String id, String questionContent) {
        Id = id;
        QuestionContent = questionContent;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getQuestionContent() {
        return QuestionContent;
    }

    public void setQuestionContent(String questionContent) {
        QuestionContent = questionContent;
    }
}
