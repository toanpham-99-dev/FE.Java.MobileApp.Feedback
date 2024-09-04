package com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment;

import java.util.ArrayList;

public class NewAssignment {
    private boolean isSuccess;
    private String message;
    private Assignmengt assignmengt;

    public NewAssignment(boolean isSuccess, String message, Assignmengt assignment) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.assignmengt = assignment;
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

    public Assignmengt getAssignment() {
        return assignmengt;
    }

    public void setAssignment(Assignmengt assignment) {
        this.assignmengt = assignment;
    }
}
