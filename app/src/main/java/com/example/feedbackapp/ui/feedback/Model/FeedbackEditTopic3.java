package com.example.feedbackapp.ui.feedback.Model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackEditTopic3 {


        @SerializedName("Id")
        @Expose
        private String id;
        @SerializedName("TopicName")
        @Expose
        private String topicName;
        @SerializedName("listQuestion")
        @Expose
        private List<FeedbackEditQuestion4> listQuestion = null;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public List<FeedbackEditQuestion4> getListQuestion() {
            return listQuestion;
        }

        public void setListQuestion(List<FeedbackEditQuestion4> listQuestion) {
            this.listQuestion = listQuestion;
        }
}
