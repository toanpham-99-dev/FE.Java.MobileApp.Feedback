package com.example.feedbackapp.ModelClassToReceiveFromAPI.Comment;


public class CommentInfo {

    public CommentInfo(boolean isSuccess, String message, String comment) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Class lưu giá trị trả về khi gọi API login (Class lớn)
     */
    private boolean isSuccess;
    private String message;
    private String comment;

    public CommentInfo(boolean success, String message) {
        this.isSuccess = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}