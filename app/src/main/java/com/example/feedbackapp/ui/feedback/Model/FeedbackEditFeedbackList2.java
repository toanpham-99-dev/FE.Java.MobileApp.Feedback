package com.example.feedbackapp.ui.feedback.Model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackEditFeedbackList2 {


        @SerializedName("Id")
        @Expose
        private String id;
        @SerializedName("Title")
        @Expose
        private String title;
        @SerializedName("AdminId")
        @Expose
        private String adminId;
        @SerializedName("AdminName")
        @Expose
        private String adminName;
        @SerializedName("TypeFeedbackId")
        @Expose
        private String typeFeedbackId;
        @SerializedName("TypeFeedbackName")
        @Expose
        private String typeFeedbackName;
        @SerializedName("listTopic")
        @Expose
        private List<FeedbackEditTopic3> listTopic = null;
        @SerializedName("StartTime")
        @Expose
        private String startTime;
        @SerializedName("EndTime")
        @Expose
        private String endTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }

        public String getAdminName() {
            return adminName;
        }

        public void setAdminName(String adminName) {
            this.adminName = adminName;
        }

        public String getTypeFeedbackId() {
            return typeFeedbackId;
        }

        public void setTypeFeedbackId(String typeFeedbackId) {
            this.typeFeedbackId = typeFeedbackId;
        }

        public String getTypeFeedbackName() {
            return typeFeedbackName;
        }

        public void setTypeFeedbackName(String typeFeedbackName) {
            this.typeFeedbackName = typeFeedbackName;
        }

        public List<FeedbackEditTopic3> getListTopic() {
            return listTopic;
        }

        public void setListTopic(List<FeedbackEditTopic3> listTopic) {
            this.listTopic = listTopic;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

    }
