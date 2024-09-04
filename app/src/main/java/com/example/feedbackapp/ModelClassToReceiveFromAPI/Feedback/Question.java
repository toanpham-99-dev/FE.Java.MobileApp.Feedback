package com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback;

public class Question {
    private String Id = "";
    private String TopicId = "";
    private String TopicName = "";
    private String QuestionContent = "";
    private boolean isDeleted = false;

    // Constructor
    public Question(
            String Id,
            String TopicId,
            String TopicName,
            String QuestionContent,
            boolean isDeleted
    ){
        this.Id = Id;
        this.TopicId = TopicId;
        this.TopicName = TopicName;
        this.QuestionContent = QuestionContent;
        this.isDeleted = isDeleted;
    }


    // GET SET
    public String getId(){return this.Id;}
    public String getTopicId(){return this.TopicId;}
    public String getTopicName(){return this.TopicName;}
    public String getQuestionContent(){return this.QuestionContent;}
    public boolean getIsDeleted(){return this.isDeleted;}

}
