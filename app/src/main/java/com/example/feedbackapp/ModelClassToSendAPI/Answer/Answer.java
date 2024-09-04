package com.example.feedbackapp.ModelClassToSendAPI.Answer;

public class Answer {
    private String Id;
    private String ClassId;
    private String ModuleId;
    private String QuestionId;
    private  int Value;
    private String TopicId;
    private String TopicName;
    public String getTopicId() {
        return TopicId;
    }

    public void setTopicId(String topicId) {
        TopicId = topicId;
    }

    public String getTopicName() {
        return TopicName;
    }

    public void setTopicName(String topicName) {
        TopicName = topicName;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String classId) {
        ClassId = classId;
    }

    public String getModuleId() {
        return ModuleId;
    }

    public void setModuleId(String moduleId) {
        ModuleId = moduleId;
    }

    public String getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(String questionId) {
        QuestionId = questionId;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public Answer(String id, String classId, String moduleId, String questionId, int value) {
        Id = id;
        ClassId = classId;
        ModuleId = moduleId;
        QuestionId = questionId;
        Value = value;
    }



    public Answer(String classId, String moduleId, String questionId, int value) {
        ClassId = classId;
        ModuleId = moduleId;
        QuestionId = questionId;
        Value = value;
    }


}
