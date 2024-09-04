package com.example.feedbackapp.ModelClassToReceiveFromAPI.Class;

public class TraineeForClass {
    private String Id;
    private String UserName;
    private String Name;
    private String Email;
    private String Phone;
    private String Address;
    private boolean isActive;
    private String Password;
    private String ActivationCode;
    private String ResetPasswordCode;

    public String getId() {
        return Id;
    }

    public String getUserName() {
        return UserName;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getPassword() {
        return Password;
    }

    public String getActivationCode() {
        return ActivationCode;
    }

    public String getResetPasswordCode() {
        return ResetPasswordCode;
    }
}
