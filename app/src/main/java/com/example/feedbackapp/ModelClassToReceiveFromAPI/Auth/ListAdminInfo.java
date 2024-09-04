package com.example.feedbackapp.ModelClassToReceiveFromAPI.Auth;

import java.util.ArrayList;

public class ListAdminInfo {
    private boolean success = false;
    private String message = "";
    private ArrayList<Admin> listAdmin = new ArrayList<Admin>();

    // GET
    public boolean isSuccess() {return success;}
    public String getMessage() {return message;}
    public ArrayList<Admin> getListAdmin() {return listAdmin;}

    // SET

    public void setSuccess(boolean success) {this.success = success;}
    public void setMessage(String message) {this.message = message;}
    public void setListAdmin(ArrayList<Admin> listAdmin) {this.listAdmin = listAdmin;}
}
