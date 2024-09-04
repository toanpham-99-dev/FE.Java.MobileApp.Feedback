package com.example.feedbackapp.model;

public class    Class {
    int ClassID;
    String ClassName;
    String AdminID;
    int Capacity;
    String StartTime;
    String EndTime;
    public Class(int classID,String className)
    {
        ClassID = classID;
        ClassName = className;
    }
    public Class(int classID, String className, String adminID, int capacity, String startTime, String endTime) {
        ClassID = classID;
        ClassName = className;
        AdminID = adminID;
        Capacity = capacity;
        StartTime = startTime;
        EndTime = endTime;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getAdminID() {
        return AdminID;
    }

    public void setAdminID(String adminID) {
        AdminID = adminID;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }
}

