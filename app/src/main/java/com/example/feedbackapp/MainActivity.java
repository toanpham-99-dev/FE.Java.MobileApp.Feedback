package com.example.feedbackapp;


import android.app.AlertDialog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Menu;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.Toast;


import com.example.feedbackapp.UserInfo.UserInfo;

import com.example.feedbackapp.ui.dashboard.TraineeDashboardFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    //Test and delete
    private ScaleGestureDetector mScaleGestureDetector;
    GestureDetector gestureDetector;
    float mScale = 0.1f;
    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ẩn title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        //
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);

        //ẩn bớt phần tử trong slide menu

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_homepage, R.id.nav_assignment, R.id.nav_classs, R.id.nav_module,R.id.nav_enrrollment, R.id.nav_feedback,

                R.id.nav_question, R.id.nav_contact, R.id.nav_logout,
                R.id.nav_add_feedback,R.id.nav_review_new_feedback,R.id.nav_feedbackright,
                R.id.nav_feedbackdetail,R.id.nav_statisticdofeedback,R.id.nav_dofeedback,
                R.id.nav_edit_feedback,R.id.nav_detail_feedback,R.id.nav_review_edit_feedback,

                R.id.nav_trainee_dashboard, R.id.nav_viewcommentfeedback, R.id.nav_assignment1

        )

                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);// điều hướng đến  fragment nav_host_fragment trong layout content_main
        //navController.navigate(R.id.nav_joinmodule);
        ConfigNavigationView(navController);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



//Code to zoom
        gestureDetector = new GestureDetector(this, new GestureListener());

        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener(){
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                float scale = 1 - detector.getScaleFactor();
                float prevScale = mScale;
                mScale += scale;

                if (mScale > 10f)
                    mScale = 10f;

                ScaleAnimation scaleAnimation = new ScaleAnimation(1f / prevScale, 1f / mScale, 1f / prevScale, 1f / mScale, detector.getFocusX(), detector.getFocusY());
                scaleAnimation.setDuration(0);
                scaleAnimation.setFillAfter(true);
                RecyclerView layout = (RecyclerView) findViewById(R.id.rcvDetail);
                layout.startAnimation(scaleAnimation);
                return true;
            }
        });



        //hiện thông tin người dùng sau khi đăng nhập, dùng để test
        ShowUserData();




    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // support show dialog join

    // Code for zoom in/out scroll view
    //Link ferer:  https://www.youtube.com/watch?v=TRqysuYnDlU
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        super.dispatchTouchEvent(event);
        mScaleGestureDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        return gestureDetector.onTouchEvent(event);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {

            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return true;
        }
    }

    //hàm dùng để ẩn bớt chức năng tùy theo role user
    void ConfigNavigationView(NavController navController){
        UserInfo userInfo = new UserInfo(getApplicationContext());
        String role = userInfo.role();

        Menu nav_Menu = navigationView.getMenu();
        if(role.equals("admin")){
            NavGraph navGraph = navController.getGraph();
            navGraph.setStartDestination(R.id.nav_assignment1);
            navController.setGraph(navGraph);
            nav_Menu.findItem(R.id.nav_join).setVisible(false);
            nav_Menu.findItem(R.id.nav_trainee_dashboard).setVisible(false);

            //không ẩn đi gì cả, sau này sẽ ẩn Join đi
        }
        else if(role.equals("trainer")){
            nav_Menu.findItem(R.id.nav_enrrollment).setVisible(false);
            nav_Menu.findItem(R.id.nav_feedback).setVisible(false);
            nav_Menu.findItem(R.id.nav_question).setVisible(false);
            nav_Menu.findItem(R.id.nav_join).setVisible(false);
            nav_Menu.findItem(R.id.nav_trainee_dashboard).setVisible(false);
           // nav_Menu.findItem(R.id.nav_statisticdofeedback).setVisible(false);
            NavGraph navGraph = navController.getGraph();
            navGraph.setStartDestination(R.id.nav_assignment1);
            navController.setGraph(navGraph);

            //nav_Menu.findItem(R.id.nav_join).setVisible(false);

        }
        else{
            nav_Menu.findItem(R.id.nav_trainee_dashboard).setVisible(false);
            nav_Menu.findItem(R.id.nav_assignment).setVisible(false);
            nav_Menu.findItem(R.id.nav_enrrollment).setVisible(false);
            nav_Menu.findItem(R.id.nav_feedback).setVisible(false);
            nav_Menu.findItem(R.id.nav_statisticdofeedback).setVisible(false);
            nav_Menu.findItem(R.id.nav_question).setVisible(false);
            nav_Menu.findItem(R.id.nav_statisticdofeedback).setVisible(false);
            //nav_Menu.findItem(R.id.nav_join).setVisible(false);
            nav_Menu.findItem(R.id.nav_statisticdofeedback).setVisible(false);
            NavGraph navGraph1 = navController.getGraph();
            navGraph1.setStartDestination(R.id.nav_trainee_dashboard);
            navController.setGraph(navGraph1);
            //nav_Menu.findItem(R.id.nav_join).setVisible(false);
        }
    }

    //hàm hiển thị xác nhận đăng xuất
    void showLogoutDialog(){
        //hiện dialog login failed
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.logout_confirm_dialog, null);
        final Button btnYes = (Button) alertLayout.findViewById(R.id.btn_Yes);
        final Button btnCancel = (Button) alertLayout.findViewById(R.id.btn_Cancel);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setCancelable(false);
        AlertDialog dialog = alert.create();
        btnYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //thực hiện xóa dữ liệu account
                //về trang login
                Toast.makeText(getApplicationContext(),"logout confirmed!",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //không làm gì cả
                Toast.makeText(getApplicationContext(),"logout canceled!",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //hàm này để thử xem dữ liệu như token, username, có lưu lại được không
    public void ShowUserData(){
        UserInfo userInfo = new UserInfo(getApplicationContext());
        String toastValue = "token: " + userInfo.token() +
                "\n username: " + userInfo.username() +
                "\n login time: " + userInfo.loginTime() +

                "\n remember: " + userInfo.isRemember() +
                "\n role: " + userInfo.role();

        Toast.makeText(getApplicationContext(),toastValue,Toast.LENGTH_LONG).show();
    }


}