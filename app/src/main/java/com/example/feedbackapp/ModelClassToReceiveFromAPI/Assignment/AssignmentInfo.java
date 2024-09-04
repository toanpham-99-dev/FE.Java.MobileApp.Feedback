package com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Login.Account;

import java.util.ArrayList;
import java.util.List;

public class AssignmentInfo {
    private boolean isSuccess;
    private String message;
    private ArrayList<Assignment> listAssignment;

    public AssignmentInfo(boolean success, String message, String accessToken, ArrayList<Assignment> assignment) {
        this.isSuccess = success;
        this.message = message;
        this.listAssignment = assignment;
    }


    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Assignment> getAssignment() {
        return listAssignment;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAssignment(ArrayList<Assignment> assignment) {
        this.listAssignment = assignment;
    }
}
