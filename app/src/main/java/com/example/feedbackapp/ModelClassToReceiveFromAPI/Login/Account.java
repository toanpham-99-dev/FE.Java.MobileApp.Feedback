package com.example.feedbackapp.ModelClassToReceiveFromAPI.Login;

public class Account {

    /**
     * Class lưu giá trị account (class con) khi gọi API login
     */
    private String _id;
    private String UserName;
    private String Password;
    private String Name;
    private String Email;
    private Integer __v;

    public Account(String _id, String userName, String password, String name, String email, Integer __v) {
        this._id = _id;
        UserName = userName;
        Password = password;
        Name = name;
        Email = email;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }
}
