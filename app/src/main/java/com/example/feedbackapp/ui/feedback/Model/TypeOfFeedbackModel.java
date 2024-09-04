package com.example.feedbackapp.ui.feedback.Model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypeOfFeedbackModel {

        @SerializedName("isSuccess")
        @Expose
        private Boolean isSuccess;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("listTypeFeedback")
        @Expose
        private List<ListTypeFeedbackModel> listTypeFeedback = null;

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

        public List<ListTypeFeedbackModel> getListTypeFeedback() {
            return listTypeFeedback;
        }

        public void setListTypeFeedback(List<ListTypeFeedbackModel> listTypeFeedback) {
            this.listTypeFeedback = listTypeFeedback;
        }

}
