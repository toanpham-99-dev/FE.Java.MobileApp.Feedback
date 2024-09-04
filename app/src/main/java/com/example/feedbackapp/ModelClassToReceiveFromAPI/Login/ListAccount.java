package com.example.feedbackapp.ModelClassToReceiveFromAPI.Login;

import com.example.feedbackapp.model.Trainee;
import java.util.ArrayList;

public class ListAccount {
    private boolean success;
    private String message;
    private ArrayList<Account> listAdmin;
    private ArrayList<Trainee> listTrainee;
    private ArrayList<Trainee> listTrainer;


    public ListAccount(boolean success, String message, ArrayList<Account> listAdmin, ArrayList<Trainee> listTrainee, ArrayList<Trainee> listTrainer) {
        this.success = success;
        this.message = message;
        this.listAdmin = listAdmin;
        this.listTrainee = listTrainee;
        this.listTrainer = listTrainer;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Account> getListAdmin() {
        return listAdmin;
    }

    public void setListAdmin(ArrayList<Account> listAdmin) {
        this.listAdmin = listAdmin;
    }

    public ArrayList<Trainee> getListTrainee() {
        return listTrainee;
    }

    public void setListTrainee(ArrayList<Trainee> listTrainee) {
        this.listTrainee = listTrainee;
    }

    public ArrayList<Trainee> getListTrainer() {
        return listTrainer;
    }

    public void setListTrainer(ArrayList<Trainee> listTrainer) {
        this.listTrainer = listTrainer;
    }
}
