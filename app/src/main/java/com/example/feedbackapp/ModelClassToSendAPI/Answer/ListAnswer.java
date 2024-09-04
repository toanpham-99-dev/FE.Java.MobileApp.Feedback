package com.example.feedbackapp.ModelClassToSendAPI.Answer;

import java.util.ArrayList;

public class ListAnswer {
    private ArrayList<Answer> listAnswer;

    public ListAnswer(ArrayList<Answer> listAnswer) {
        this.listAnswer = listAnswer;
    }

    public ArrayList<Answer> getListAnswer() {
        return listAnswer;
    }

    public void setListAnswer(ArrayList<Answer> listAnswer) {
        this.listAnswer = listAnswer;
    }
}
