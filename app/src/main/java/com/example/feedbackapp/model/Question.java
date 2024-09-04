package com.example.feedbackapp.model;

public class Question {
    String QuestionID;
    String QuestionContent;
   int Answer;

    public Question(String questionID, String questionContent, int answer) {
        QuestionID = questionID;
        QuestionContent = questionContent;
        Answer = answer;
    }

    public String getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(String questionID) {
        QuestionID = questionID;
    }

    public String getQuestionContent() {
        return QuestionContent;
    }

    public void setQuestionContent(String questionContent) {
        QuestionContent = questionContent;
    }

    public int getAnswer() {
        return Answer;
    }

    public void setAnswer(int answer) {
        Answer = answer;
    }
}
