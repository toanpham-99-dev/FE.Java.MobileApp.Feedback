package com.example.feedbackapp.ModelClassToReceiveFromAPI.Answer;


public class AnswerInfo {

    /**
     * Class lưu giá trị trả về khi gọi API login (Class lớn)
     */
    private boolean isSuccess;
    private String message;

    public AnswerInfo(boolean success, String message) {
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