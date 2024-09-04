package com.example.feedbackapp.UserInfo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class UserInfo extends Application {
    //class này dùng để lưu access token sau khi đăng nhập
    //và để sử dụng access token lại nhiều lần

    //khởi tạo các biến cần thiết
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //5 cái ở dưới là khai báo để khỏi viết dấu nháy kép
    String accessToken = "token";

    String username = "username";
    String loginTime = "loginTime";
    String isRemember = "isRemember";
    String role = "role";


    //hàm khởi tạo trước khi ghi và đọc dữ liệu
    public UserInfo(Context context){
        sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }
    //hàm thay đổi giá trị lưu khi đăng nhập tài khoản mới

    public void newInfo(String newToken, String newUsername, String newLoginTime, Boolean newIsRemember, String newRole){
        editor = sharedPreferences.edit();
        editor.putString(accessToken, newToken);
        editor.putString(username,newUsername);
        editor.putString(loginTime,newLoginTime);
        editor.putBoolean(isRemember, newIsRemember);
        editor.putString(role, newRole);

        editor.apply();
    }
    //hàm xóa các giá trị lưu khi đăng xuất
    public void onLogoutExecute(){
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    //hàm lấy token ra dùng
    public String token(){
        return sharedPreferences.getString(accessToken,"none");
    }
    public String username(){
        return sharedPreferences.getString(username,"none");
    }
    public String loginTime(){
        return sharedPreferences.getString(loginTime,"none");
    }

    public String role(){
        return sharedPreferences.getString(role,"none");
    }
    public String isRemember(){
        if(sharedPreferences.getBoolean(isRemember,false)){
            return "true";
        }
        else{
            return "false";
        }
    }

    //hàm kiểm tra xem đã quá thời gian duy trì đăng nhập chưa
    public boolean isExpired(){
        return true;
    }
}
