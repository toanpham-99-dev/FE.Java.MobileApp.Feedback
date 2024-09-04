package com.example.feedbackapp.ModelClassToReceiveFromAPI.Login;

public class LoginInfo {

    /**
     * Class lưu giá trị trả về khi gọi API login (Class lớn)
     */
    private boolean success;
    private String message;
    private String accessToken;
    private Account account;

    public LoginInfo(boolean success, String message, String accessToken, Account account) {
        this.success = success;
        this.message = message;
        this.accessToken = accessToken;
        this.account = account;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", account=" + account +
                '}';
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
