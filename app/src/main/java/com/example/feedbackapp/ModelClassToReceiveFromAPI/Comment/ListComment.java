package com.example.feedbackapp.ModelClassToReceiveFromAPI.Comment;

import com.example.feedbackapp.ModelClassToSendAPI.Comment;

import java.util.ArrayList;

public class ListComment {
    boolean isSuccess;
    String message;
    ArrayList<Comment> listComment;

    public ListComment(boolean isSuccess, String message, ArrayList<Comment> listComment) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.listComment = listComment;
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

    public ArrayList<Comment> getListComment() {
        return listComment;
    }

    public void setListComment(ArrayList<Comment> listComment) {
        this.listComment = listComment;
    }
}
