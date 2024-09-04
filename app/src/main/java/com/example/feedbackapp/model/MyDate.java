package com.example.feedbackapp.model;

public class MyDate {
    private int date = 1;
    private int month = 1;
    private int year = 2000;

    public MyDate(int date, int month, int year){
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public int getDate() {
        return date;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
