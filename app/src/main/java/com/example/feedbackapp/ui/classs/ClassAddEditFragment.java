package com.example.feedbackapp.ui.classs;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassList;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.ClassAPIService;
import com.example.feedbackapp.UserInfo.UserInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassAddEditFragment extends Fragment {

    private ClassAddEditViewModel mViewModel;
    EditText txtClassName, txtCapacity, txtClassStartDate, txtClassEndDate;
    TextView txtErrClassName, txtErrCapacity, txtErrClassStartDate, txtErrClassEndDate, txtTitleAddClass;
    ImageView selectStartDate, selectEndDate;
    Button btnSaveClass, btnBack;
    Boolean isValidated = true;

    String classID = "";
    Classs classs;
    ClassInfo classInfo;

    public static ClassAddEditFragment newInstance() {
        return new ClassAddEditFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.class_add_edit_fragment, container, false);

        txtClassName = root.findViewById(R.id.txt_AddClassName);
        txtCapacity = root.findViewById(R.id.txt_AddClassCapacity);
        txtClassStartDate = root.findViewById(R.id.txt_AddClassStartDate);
        txtClassEndDate = root.findViewById(R.id.txt_AddClassEndDate);
        txtErrClassName = root.findViewById(R.id.txt_errClassName);
        txtErrCapacity = root.findViewById(R.id.txt_errClassCapacity);
        txtErrClassStartDate = root.findViewById(R.id.txt_errClassStartDate);
        txtErrClassEndDate = root.findViewById(R.id.txt_errClassEndDate);
        selectStartDate = root.findViewById(R.id.img_startDateButton);
        selectEndDate = root.findViewById(R.id.img_endDateButton);
        btnSaveClass = root.findViewById(R.id.btn_SaveAddClass);
        btnBack = root.findViewById(R.id.btn_BackAddClass);

        selectStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowCalendarDialog(root, txtClassStartDate);
            }
        });
        selectEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowCalendarDialog(root, txtClassEndDate);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                if(fragmentManager.getBackStackEntryCount()>0){
//                    fragmentManager.popBackStack();
//                }
                Navigation.findNavController(v).navigate(R.id.nav_classs);
            }
        });
        try{
            classID = getArguments().getString("ClassId");
            ClassAPIService.classAPIService.getClass("Bearer "+ new UserInfo(getContext()).token(),classID).enqueue(new Callback<ClassInfo>() {
                @Override
                public void onResponse(Call<ClassInfo> call, Response<ClassInfo> response) {
                    if(response.isSuccessful()){
                        classInfo = response.body();
                        LoadClass(root);
                    }
                }

                @Override
                public void onFailure(Call<ClassInfo> call, Throwable t) {

                }
            });
        }
        catch(Exception ex) {

        }
        btnSaveClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnValidateClass(root);
            }
        });
        return root;
    }

    void LoadClass(View root){
        txtTitleAddClass = root.findViewById(R.id.txt_TitleAddClass);
        txtTitleAddClass.setText("Edit Class");
        selectStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do nothing
            }
        });
        txtClassStartDate.setEnabled(false);
        txtClassStartDate.setBackgroundColor(Color.parseColor("#EAEAEA"));
        selectStartDate.setBackgroundColor(Color.parseColor("#EAEAEA"));
        classs = classInfo.getClass_res();
        txtClassName.setText(classs.getClassName());
        txtCapacity.setText(classs.getCapacity().toString());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = Calendar.getInstance().getTime();
        Date endDate = Calendar.getInstance().getTime();
        try {
            startDate = formatter.parse(classs.getStartTime());
            endDate = formatter.parse(classs.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter = new SimpleDateFormat("MM/dd/yyyy");
        txtClassStartDate.setText(formatter.format(startDate));
        txtClassEndDate.setText(formatter.format(endDate));
    }

    void ShowCalendarDialog(View root, EditText value){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.calendar_dialog_layout, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(root.getContext());
        CalendarView calendarView = alertLayout.findViewById(R.id.cld_GetClassDate);
        alert.setView(alertLayout);
        //alert.setCancelable(false);
        AlertDialog dialog = alert.create();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String sday;
                String smonth;
                String syear;
                if(dayOfMonth < 10){
                    sday="0"+String.valueOf(dayOfMonth);
                }
                else{
                    sday=String.valueOf(dayOfMonth);
                }
                if((month+1) < 10){
                    smonth="0"+String.valueOf(month+1);
                }
                else {
                    smonth=String.valueOf(month+1);
                }
                syear = String.valueOf(year);
                value.setText(smonth+"/"+sday+"/"+syear);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    void OnValidateClass(View root){
        if(txtClassName.getText().toString().trim().equals("") || txtClassName.getText().length() > 255) {
            txtErrClassName.setText("Please enter class name and less than 255 character");
            isValidated = false;
        }
        else{
            txtErrClassName.setText("");
        }
        if(txtCapacity.getText().toString().trim().equals("") || Integer.valueOf(txtCapacity.getText().toString()) < 1){
            txtErrCapacity.setText("Please enter capacity in integer and larger than 0");
            isValidated = false;
        }
        else{
            txtErrCapacity.setText("");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date curentDate = Calendar.getInstance().getTime();
        Date startDate = Calendar.getInstance().getTime();
        Date endDate = Calendar.getInstance().getTime();
        try {
            curentDate = formatter.parse(Calendar.getInstance().getTime().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(classID.equals("")){
            try {
                startDate = formatter.parse(txtClassStartDate.getText().toString());
                //start date lớn hơn current date (ít nhất 1 ngày)
                if(startDate.getTime() - curentDate.getTime() < 0){
                    txtErrClassStartDate.setText("Please choose date after now date");
                    isValidated = false;
                }
                else{
                    txtErrClassStartDate.setText("");
                }
            } catch (ParseException e) {
                //e.printStackTrace();
                txtErrClassStartDate.setText("Please choose date or fill full dd/mm/yyyy");
                isValidated = false;
            }
        }
        else{

        }
        try {
            endDate = formatter.parse(txtClassEndDate.getText().toString());
            //start date lớn hơn current date (ít nhất 1 ngày)
            if(endDate.getTime() - curentDate.getTime() < 0){
                txtErrClassEndDate.setText("Please choose date after now date");
                isValidated = false;
            }
            else if(endDate.getTime()-startDate.getTime() <= 43200000){
                //nửa ngày
                txtErrClassEndDate.setText("Please choose end date after start date");
                isValidated = false;
            }
            else{
                txtErrClassEndDate.setText("");
            }
        } catch (ParseException e) {
            //e.printStackTrace();
            txtErrClassEndDate.setText("Please choose date or fill full dd/mm/yyyy");
            isValidated = false;
        }

        if(isValidated){
            if(classID.equals("")){
                doAddClass(root);
            }
            else{
                Toast.makeText(root.getContext(),"vào edit class",Toast.LENGTH_LONG).show();
                doEditClass(root);
            }
        }
        else{

        }
    }

    void doEditClass(View root){
        String className = txtClassName.getText().toString();
        String capacity = txtCapacity.getText().toString();
        String startDate = convertDatetoSubmit(txtClassStartDate.getText().toString());
        String endDate = convertDatetoSubmit(txtClassEndDate.getText().toString());
        Classs editclass = new Classs(classID,className,Integer.valueOf(capacity),startDate,endDate,false,null);
        ClassAPIService.classAPIService.editClass("Bearer "+ new UserInfo(root.getContext()).token(), classID, editclass).enqueue(new Callback<ClassInfo>() {
            @Override
            public void onResponse(Call<ClassInfo> call, Response<ClassInfo> response) {
                if(response.isSuccessful()){
                    Toast.makeText(root.getContext(),"được",Toast.LENGTH_LONG).show();
                    showSuccessDialog(root);
                }
                else {
                    Toast.makeText(root.getContext(),"sai cú pháp",Toast.LENGTH_LONG).show();
                    showFailDialog(root);
                }
            }

            @Override
            public void onFailure(Call<ClassInfo> call, Throwable t) {
                Toast.makeText(root.getContext(),"có lỗi gì đó",Toast.LENGTH_LONG).show();
                showFailDialog(root);
            }
        });
    }

    void doAddClass(View root){
        String className = txtClassName.getText().toString();
        String capacity = txtCapacity.getText().toString();
        String startDate = convertDatetoSubmit(txtClassStartDate.getText().toString());
        String endDate = convertDatetoSubmit(txtClassEndDate.getText().toString());
        Classs classs = new Classs(null,className,Integer.valueOf(capacity),startDate,endDate,false,null);
        ClassAPIService.classAPIService.addClass("Bearer "+ new UserInfo(root.getContext()).token(),classs).enqueue(new Callback<ClassList>() {
            @Override
            public void onResponse(Call<ClassList> call, Response<ClassList> response) {
                if(response.isSuccessful()){
                    Toast.makeText(root.getContext(),"được",Toast.LENGTH_LONG).show();
                    showSuccessDialog(root);
                }
                else {
                    Toast.makeText(root.getContext(),"sai cú pháp",Toast.LENGTH_LONG).show();
                    showFailDialog(root);
                }
            }

            @Override
            public void onFailure(Call<ClassList> call, Throwable t) {
                Toast.makeText(root.getContext(),"có lỗi gì đó",Toast.LENGTH_LONG).show();
                showFailDialog(root);
            }
        });
    }

    String convertDatetoSubmit(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date getDate = Calendar.getInstance().getTime();
        try {
            getDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(getDate);
    }

    void showSuccessDialog(View root){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.success_dialog_layout, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(root.getContext());
        TextView txtMessage = alertLayout.findViewById(R.id.txt_SingleMessage);
        if(classID.equals("")){
            txtMessage.setText("Add success!");
        }
        else{
            txtMessage.setText("Update success!");
        }

        alert.setView(alertLayout);
        //alert.setCancelable(false);
        AlertDialog dialog = alert.create();
        Button btnOK = alertLayout.findViewById(R.id.btn_OK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                FragmentManager fragmentManager = getFragmentManager();
//                if(fragmentManager.getBackStackEntryCount()>0){
//                    fragmentManager.popBackStack();
//                }
                Navigation.findNavController(root).navigate(R.id.add_edit_back_to_class);
            }
        });
        dialog.show();
    }
    void showFailDialog(View root){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.failure_dialog_layout, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(root.getContext());
        TextView txtMessage = alertLayout.findViewById(R.id.txt_SingleErrorMessage);
        if(classID.equals("")){
            txtMessage.setText("Add fail!");
        }
        else{
            txtMessage.setText("Update fail!");
        }

        alert.setView(alertLayout);
        //alert.setCancelable(false);
        AlertDialog dialog = alert.create();
        Button btnOK = alertLayout.findViewById(R.id.btn_OK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ClassAddEditViewModel.class);
        // TODO: Use the ViewModel
    }

}