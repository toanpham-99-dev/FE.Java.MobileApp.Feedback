package com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic;

import com.example.feedbackapp.model.Class;

import java.util.ArrayList;

public class Topic {
    private String Id;
    private String TopicName;
    private ArrayList<Question> listQuestion;

    public Topic(){

    }
    public Topic(String id, String topicName, ArrayList<Question> listQuestion) {
        Id = id;
        TopicName = topicName;
        this.listQuestion = listQuestion;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTopicName() {
        return TopicName;
    }

    public void setTopicName(String topicName) {
        TopicName = topicName;
    }

    public ArrayList<Question> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(ArrayList<Question> listQuestion) {
        this.listQuestion = listQuestion;
    }

    // TODO: Text show in Spinner by Toan
    @Override
    public String toString()  {
        return this.TopicName;
    }
}
