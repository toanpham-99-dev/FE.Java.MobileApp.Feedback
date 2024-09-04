package com.example.feedbackapp.ModelClassToReceiveFromAPI.Auth;

public class Admin {
    private String Id = "";
    private String UserName = "";
    private String Password = "";
    private String Name = "";
    private String Email = "";


    // GET
    public String getId() {return Id;}
    public String getUserName() {return UserName;}
    public String getPassword() {return Password;}
    public String getName() {return Name;}
    public String getEmail() {return Email;}

    // SET
    public void setId(String id) {Id = id;}
    public void setUserName(String userName) {UserName = userName;}
    public void setPassword(String password) {Password = password;}
    public void setName(String name) {Name = name;}
    public void setEmail(String email) {Email = email;}
}
