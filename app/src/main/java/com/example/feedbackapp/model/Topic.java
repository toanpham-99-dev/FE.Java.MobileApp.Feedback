package com.example.feedbackapp.model;

public class Topic {
    int ToppicID;
    String TopicName;

    public Topic(int toppicID, String topicName) {
        ToppicID = toppicID;
        TopicName = topicName;
    }

    public int getToppicID() {
        return ToppicID;
    }

    public void setToppicID(int toppicID) {
        ToppicID = toppicID;
    }

    public String getTopicName() {
        return TopicName;
    }

    public void setTopicName(String topicName) {
        TopicName = topicName;
    }
}
