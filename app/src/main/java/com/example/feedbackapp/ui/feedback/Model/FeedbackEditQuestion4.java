package com.example.feedbackapp.ui.feedback.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackEditQuestion4 {


        @SerializedName("Id")
        @Expose
        private String id;
        @SerializedName("TopicId")
        @Expose
        private String topicId;
        @SerializedName("TopicName")
        @Expose
        private String topicName;
        @SerializedName("QuestionContent")
        @Expose
        private String questionContent;
        @SerializedName("isDeleted")
        @Expose
        private Boolean isDeleted;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getQuestionContent() {
            return questionContent;
        }

        public void setQuestionContent(String questionContent) {
            this.questionContent = questionContent;
        }

        public Boolean getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(Boolean isDeleted) {
            this.isDeleted = isDeleted;
        }

}
