package com.example.feedbackapp.ModelClassToReceiveFromAPI.Class;

import com.example.feedbackapp.model.Trainee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Classs {
    private String Id;
    private String ClassName;
    private Integer Capacity;
    private String StartTime;
    private String EndTime;
    private boolean isDeleted;
    private TraineeForClass[] listTrainee;

    public Classs(String id, String className, Integer capacity, String startTime, String endTime, boolean isDeleted, TraineeForClass[] listTrainee) {
        Id = id;
        ClassName = className;
        Capacity = capacity;
        StartTime = startTime;
        EndTime = endTime;
        this.isDeleted = isDeleted;
        this.listTrainee = listTrainee;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public Integer getCapacity() {
        return Capacity;
    }

    public void setCapacity(Integer capacity) {
        Capacity = capacity;
    }

    public String getStartTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date classStartTime = formatter.parse(StartTime);
            return String.valueOf(classStartTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return StartTime;
        }
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date classEndTime = formatter.parse(EndTime);
            return String.valueOf(classEndTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return EndTime;
        }
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public TraineeForClass[] getListTrainee() {
        return listTrainee;
    }

    public String traineeCount(){
        return String.valueOf(listTrainee.length);
    }

    public void setListTrainee(TraineeForClass[] listTrainee) {
        this.listTrainee = listTrainee;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    // TODO: Text show in Spinner by Toan
    @Override
    public String toString()  {
        return this.getClassName();
    }
}
