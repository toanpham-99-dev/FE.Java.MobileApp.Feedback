package com.example.feedbackapp.ui.feedback.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackEditFilterId1 {


        @SerializedName("isSuccess")
        @Expose
        private Boolean isSuccess;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("feedback")
        @Expose
        private FeedbackEditFeedbackList2 feedback;

        public Boolean getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(Boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public FeedbackEditFeedbackList2 getFeedback() {
            return feedback;
        }

        public void setFeedback(FeedbackEditFeedbackList2 feedback) {
            this.feedback = feedback;
        }
    }

