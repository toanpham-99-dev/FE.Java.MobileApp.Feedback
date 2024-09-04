package com.example.feedbackapp.ModelClassToSendAPI.Login;

public class LoginValue {
    /**
     * Class lưu giá trị username, pasword và role để gởi đi
     */
    private String username;
    private String password;
    private String typeUser;

    public LoginValue(String username, String password, String typeUser) {
        this.username = username;
        this.password = password;
        this.typeUser = typeUser;
    }

    @Override
    public String toString() {
        return "LoginValue{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", typeUser='" + typeUser + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }
}
