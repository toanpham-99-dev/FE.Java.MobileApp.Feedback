package com.example.feedbackapp.model;

public class CustomItem {
    private String spinerItemName;
    private String spinnerId;

    public CustomItem(String spinerItemName, String spinnerId){
        this.spinerItemName = spinerItemName;
        this.spinnerId = spinnerId;
    }

    public String getSpinerItemName() {
        return spinerItemName;
    }

    public String getSpinnerId() {
        return spinnerId;
    }
}
