package com.example.feedbackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Login.LoginInfo;
import com.example.feedbackapp.ModelClassToSendAPI.Login.LoginValue;
import com.example.feedbackapp.RetrofitAPISetvice.LoginAPIServices;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.ui.dashboard.TraineeDashboardFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    //khai báo các thành phần có trong layout
    EditText txtUserName;
    TextView txtUserNameReport;
    EditText txtPassword;
    TextView txtPasswordReport;
    Spinner spnRolePicker;
    CheckBox chkRememberMe;
    Button btnLogin;
    LoginInfo loginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //kiểm tra xem phiên đăng nhập còn hay không, nếu còn thì vào menu luôn
        CheckRememberTime();

        //lấy các thành phần có trong layout
        txtUserName = (EditText) findViewById(R.id.txt_UserName);
        txtUserNameReport = (TextView) findViewById(R.id.txt_UserNameReport);
        txtPassword = (EditText) findViewById(R.id.txt_Password);
        txtPasswordReport = (TextView) findViewById(R.id.txt_PasswordReport);
        spnRolePicker = (Spinner) findViewById(R.id.spn_RolePicker);
        chkRememberMe = (CheckBox) findViewById(R.id.chk_RememberMe);
        btnLogin = (Button) findViewById(R.id.btn_Login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OnLoginValidate();
            }
        });
    }
    //hàm validate cho login
    void OnLoginValidate(){
        //tạo biến kiểm tra đã đúng validate chưa
        boolean isValidated = true;

        //tạo biến lưu giá trị Username và Password
        String username = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();
        int role = spnRolePicker.getSelectedItemPosition()+1;

        //kiểm tra để trống và chứa khoảng trắng username và password
        if(username.trim().equals("")){
            txtUserNameReport.setText("Username must have at least 1 character!");
            isValidated = false;
            //return;
        }
        else if(username.contains(" ")){
            txtUserNameReport.setText("Username must have at blank space!");
            isValidated = false;
            //return;
        }
        else {
            txtUserNameReport.setText("");
        }

        if(password.trim().equals("")) {
            txtPasswordReport.setText("Password must have at least 1 character!");
            isValidated = false;
            //return;
        }
        else if(password.contains(" ")){
            txtPasswordReport.setText("Password must have at blank space!");
            isValidated = false;
            //return;
        }
        else{
            txtPasswordReport.setText("");
        }
        //nếu validate không lỗi, kiểm tra tài khoản
        if(isValidated){
            OnAccountCheck();
        }
    }

    void OnAccountCheck(){
        //tạo biến lưu giá trị Username và Password
        String username = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();
        int role = spnRolePicker.getSelectedItemPosition()+1;
        String strRole = "";

        //kiểm tra tài khoản và role
        if(role==2){
            strRole = "trainer";
        }
        else if(role==3){
            strRole = "trainee";
        }
        else{
            strRole = "admin";
        }

        //tạo model lưu giá trị nhập khi login
        //LoginValue lv = new LoginValue("huydpqn1234","Admin123@","trainee");
        LoginValue lv = new LoginValue(username,password,strRole);
        //gọi API kiểm tra tài khoản đăng nhập, nếu đúng, vào trang chính, nếu sai hiển thị lỗi
        LoginAPIServices.loginAPIServices.onLoginClick(lv).enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                loginInfo = response.body();
                if(loginInfo!=null){
                    if(loginInfo.isSuccess()){
                        //lưu thông tin đăng nhập lại để còn dùng tiếp
                        OnRememberAccount();
                        //chuyển đến trang menu
                        SwitchToMemuScreen();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"sai account!",Toast.LENGTH_LONG).show();
                        ShowLoginFailDialog();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"null!",Toast.LENGTH_LONG).show();
                    ShowLoginFailDialog();
                }
            }
            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Có lỗi xảy ra!",Toast.LENGTH_LONG).show();
            }
        });
    }

    //hàm kiểm tra xử lý Remember Me
    void OnRememberAccount(){
        int role = spnRolePicker.getSelectedItemPosition()+1;
        String strRole = "";

        //kiểm tra tài khoản và role
        if(role==2){
            strRole = "trainer";
        }
        else if(role==3){
            strRole = "trainee";
        }
        else{
            strRole = "admin";
        }
        //tạo biến Remember password
        Boolean isRemember = chkRememberMe.isChecked();

        //tạo biến lưu ngày giờ đơn giản
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String curentDateTime = formatter.format(Calendar.getInstance().getTime());
//        Date curentDateTime = null;
//        try {
//            curentDateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("27-05-2021 11:00:00");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        //String curentDateTime =formatter.format("27-05-2021 11:00:00");
        UserInfo userInfo = new UserInfo(getApplicationContext());
        userInfo.newInfo(loginInfo.getAccessToken(),loginInfo.getAccount().getUserName(),String.valueOf(curentDateTime),isRemember,strRole);
    }

    void ShowLoginFailDialog(){
        //hiện dialog login failed
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.login_failed_dialog, null);
        final Button btnYes = (Button) alertLayout.findViewById(R.id.btn_Yes);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setCancelable(false);
        AlertDialog dialog = alert.create();
        btnYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //hàm kiểm tra xem còn phiên đăng nhập hay không
    void CheckRememberTime(){
        //vì có try catch, nên nếu chưa có dữ liệu vẫn không lo bị văng
        try {
            //gọi dữ liệu account đã lưu
            UserInfo userInfo = new UserInfo(getApplicationContext());
            //lấy ngày giờ hiện tại để so sánh
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date curentDateTime = Calendar.getInstance().getTime();
            Date loginDateTime = formatter.parse(userInfo.loginTime());
            //tính khoảng cách giữa thời gian login và hiện tại (mili giây)
            long difference_In_Time = curentDateTime.getTime() - loginDateTime.getTime();
            // đổi 1 ngày ra miligiay = 86 400 000, 30 phút ra miligiay =  1 800 000
            if((userInfo.isRemember().equals("true") && difference_In_Time < 86400000) ||
                    (userInfo.isRemember().equals("false") && difference_In_Time < 1800000)){
                //nếu phiên đăng nhập vẫn còn
                SwitchToMemuScreen();
            }
            else{
                //hết phiên, đăng nhập lại
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    void SwitchToMemuScreen(){
        //chuyển đến trang menu
        Intent mainMenu = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainMenu);
    }
}