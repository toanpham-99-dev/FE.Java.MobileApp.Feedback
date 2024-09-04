package com.example.feedbackapp.ui.module;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Auth.Admin;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Auth.ListAdminInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback.Feedback;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback.ListFeedbackInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ModuleInfo;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.AuthAPIService;
import com.example.feedbackapp.RetrofitAPISetvice.FeedbackAPIServices;
import com.example.feedbackapp.RetrofitAPISetvice.ModuleAPIService;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.model.CustomItem;
import com.example.feedbackapp.model.CustomItemAdapter;
import com.example.feedbackapp.model.MyDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditModuleFragment extends Fragment {

    private AddEditModuleViewModel mViewModel;

    LinearLayout moduleNameWrap, moduleStartDateWrap, moduleEndDateWrap,
            moduleFeedbackEndDateWrap, moduleFeedbackStartDateWrap;
    Button btnSave, btnBack;
    EditText txt_AddModuleName, txt_AddModuleStartDate, txt_AddModuleEndDate,
            txt_AddModuleFeedbackStartDate, txt_AddModuleFeedbackEndDate;

    Spinner adminSpiner, feedbackSpinner;
    ArrayList<CustomItem> customList;

    ArrayList<CustomItem> listAdmin, listFeedback;
    int indexAdminId = 0, indexFeedbackId = 0;

    String moduleId = "";

    public static AddEditModuleFragment newInstance() {
        return new AddEditModuleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        moduleId = getArguments().getString("ModuleId");

        View root = inflater.inflate(R.layout.add_edit_module_fragment, container, false);

        adminSpiner = root.findViewById(R.id.spn_listAdmin);
        feedbackSpinner = root.findViewById(R.id.spn_listFeedback);

        txt_AddModuleName = root.findViewById(R.id.txt_AddModuleName);
        txt_AddModuleStartDate = root.findViewById(R.id.txt_AddModuleStartDate);
        txt_AddModuleEndDate = root.findViewById(R.id.txt_AddModuleEndDate);
        txt_AddModuleFeedbackStartDate = root.findViewById(R.id.txt_AddModuleFeedbackStartDate);
        txt_AddModuleFeedbackEndDate = root.findViewById(R.id.txt_AddModuleFeedbackEndDate);

        moduleNameWrap = root.findViewById(R.id.moduleNameWrap);
        moduleStartDateWrap = root.findViewById(R.id.moduleStartDateWrap);
        moduleEndDateWrap = root.findViewById(R.id.moduleEndDateWrap);
        moduleFeedbackEndDateWrap = root.findViewById(R.id.moduleFeedbackEndDateWrap);
        moduleFeedbackStartDateWrap = root.findViewById(R.id.moduleFeedbackStartDateWrap);

        listAdmin = new ArrayList<CustomItem>();
        listFeedback = new ArrayList<CustomItem>();

        // get api
        getData(root);


        //save data
        btnSave = root.findViewById(R.id.btn_SaveAddModule);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean isAccept = OnSaveModuleValidate(root);
            }
        });

        //save data
        btnBack = root.findViewById(R.id.btn_BackAddModule);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_module);
            }
        });

        return root;
    }

    private boolean OnSaveModuleValidate(View root){
        boolean flag = true;

        //tạo biến lưu giá trị Username và Password
        String moduleName = txt_AddModuleName.getText().toString();
        String moduleStartTime = txt_AddModuleStartDate.getText().toString();
        String moduleEndTime = txt_AddModuleEndDate.getText().toString();
        String feedbackStartTime = txt_AddModuleFeedbackStartDate.getText().toString();
        String feedbackEndTime = txt_AddModuleFeedbackEndDate.getText().toString();

        // check module name
        if(moduleName.isEmpty()){
            flag = false;
            try {
                TextView tvModuleNameNotify = root.findViewById(R.id.moduleNameNotify);
                ((ViewGroup) tvModuleNameNotify.getParent()).removeView(tvModuleNameNotify);
            }catch (Exception e){}


            TextView tvModuleNameNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
            tvModuleNameNotify.setId(R.id.moduleNameNotify);
            tvModuleNameNotify.setText("Please enter module name and less than 255");
            moduleNameWrap.addView(tvModuleNameNotify);
        }else{
            try {
                TextView tvModuleNameNotify = root.findViewById(R.id.moduleNameNotify);
                ((ViewGroup) tvModuleNameNotify.getParent()).removeView(tvModuleNameNotify);
            }catch (Exception e){}
        }

        // check module start time
        if(moduleStartTime.isEmpty()){
            flag = false;
            try {
                TextView tvModuleStartTimeNotify = root.findViewById(R.id.moduleStartTimeNotify);
                ((ViewGroup) tvModuleStartTimeNotify.getParent()).removeView(tvModuleStartTimeNotify);
            }catch (Exception e){}


            TextView tvModuleStartTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
            tvModuleStartTimeNotify.setId(R.id.moduleStartTimeNotify);
            tvModuleStartTimeNotify.setText("Please choose start date or fill mm/dd/yyyy");
            moduleStartDateWrap.addView(tvModuleStartTimeNotify);
        }else {
            MyDate startDate = convertStringToDate(moduleStartTime);

            if(startDate == null){
                flag = false;
                try {
                    TextView tvModuleStartTimeNotify = root.findViewById(R.id.moduleStartTimeNotify);
                    ((ViewGroup) tvModuleStartTimeNotify.getParent()).removeView(tvModuleStartTimeNotify);
                }catch (Exception e){}


                TextView tvModuleStartTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
                tvModuleStartTimeNotify.setId(R.id.moduleStartTimeNotify);
                tvModuleStartTimeNotify.setText("Date is not in the correct");
                moduleStartDateWrap.addView(tvModuleStartTimeNotify);
            }else{
                try {
                    TextView tvModuleStartTimeNotify = root.findViewById(R.id.moduleStartTimeNotify);
                    ((ViewGroup) tvModuleStartTimeNotify.getParent()).removeView(tvModuleStartTimeNotify);
                }catch (Exception e){}

                MyDate currentDate = getCurrentDate();

                if(!compareDate(currentDate, startDate)){
                    TextView tvModuleStartTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
                    tvModuleStartTimeNotify.setId(R.id.moduleStartTimeNotify);
                    tvModuleStartTimeNotify.setText("Please choose start date after now date");
                    moduleStartDateWrap.addView(tvModuleStartTimeNotify);

                    flag = false;
                }
            }
        }

        // check module end time
        if(moduleEndTime.isEmpty()){
            flag = false;
            try {
                TextView tvModuleEndTimeNotify = root.findViewById(R.id.moduleEndTimeNotify);
                ((ViewGroup) tvModuleEndTimeNotify.getParent()).removeView(tvModuleEndTimeNotify);
            }catch (Exception e){}


            TextView tvModuleEndTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
            tvModuleEndTimeNotify.setId(R.id.moduleEndTimeNotify);
            tvModuleEndTimeNotify.setText("Please choose start date or fill mm/dd/yyyy");
            moduleEndDateWrap.addView(tvModuleEndTimeNotify);
        }else {
            MyDate endDate = convertStringToDate(moduleEndTime);

            if(endDate == null){
                flag = false;
                try {
                    TextView tvModuleEndTimeNotify = root.findViewById(R.id.moduleEndTimeNotify);
                    ((ViewGroup) tvModuleEndTimeNotify.getParent()).removeView(tvModuleEndTimeNotify);
                }catch (Exception e){}


                TextView tvModuleEndTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
                tvModuleEndTimeNotify.setId(R.id.moduleEndTimeNotify);
                tvModuleEndTimeNotify.setText("Date is not in the correct");
                moduleEndDateWrap.addView(tvModuleEndTimeNotify);
            }else{
                try {
                    TextView tvModuleEndTimeNotify = root.findViewById(R.id.moduleEndTimeNotify);
                    ((ViewGroup) tvModuleEndTimeNotify.getParent()).removeView(tvModuleEndTimeNotify);
                }catch (Exception e){}

                // check date
                MyDate startDate = convertStringToDate(moduleStartTime);
                if(!compareDate(startDate, endDate)){
                    TextView tvModuleEndTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
                    tvModuleEndTimeNotify.setId(R.id.moduleEndTimeNotify);
                    tvModuleEndTimeNotify.setText("Please choose end date after start date");
                    moduleEndDateWrap.addView(tvModuleEndTimeNotify);

                    flag = false;
                }

            }
        }


        // check module feedback start time
        if(feedbackStartTime.isEmpty()){
            flag = false;
            try {
                TextView tvModuleFeedbackStartTimeNotify = root.findViewById(R.id.moduleFeedbackStartTimeNotify);
                ((ViewGroup) tvModuleFeedbackStartTimeNotify.getParent()).removeView(tvModuleFeedbackStartTimeNotify);
            }catch (Exception e){}


            TextView tvModuleFeedbackStartTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
            tvModuleFeedbackStartTimeNotify.setId(R.id.moduleFeedbackStartTimeNotify);
            tvModuleFeedbackStartTimeNotify.setText("Please choose start date or fill mm/dd/yyyy");
            moduleFeedbackStartDateWrap.addView(tvModuleFeedbackStartTimeNotify);

        }else {
            MyDate startFeedbackDate = convertStringToDate(feedbackStartTime);

            if(startFeedbackDate == null){
                flag = false;
                try {
                    TextView tvModuleFeedbackStartTimeNotify = root.findViewById(R.id.moduleFeedbackStartTimeNotify);
                    ((ViewGroup) tvModuleFeedbackStartTimeNotify.getParent()).removeView(tvModuleFeedbackStartTimeNotify);
                }catch (Exception e){}

                TextView tvModuleFeedbackStartTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
                tvModuleFeedbackStartTimeNotify.setId(R.id.moduleFeedbackStartTimeNotify);
                tvModuleFeedbackStartTimeNotify.setText("Date is not in the correct");
                moduleFeedbackStartDateWrap.addView(tvModuleFeedbackStartTimeNotify);
            }else{
                try {
                    TextView tvModuleFeedbackStartTimeNotify = root.findViewById(R.id.moduleFeedbackStartTimeNotify);
                    ((ViewGroup) tvModuleFeedbackStartTimeNotify.getParent()).removeView(tvModuleFeedbackStartTimeNotify);
                }catch (Exception e){}

                MyDate currentDate = getCurrentDate();

                if(!compareDate(currentDate, startFeedbackDate)){
                    TextView tvModuleFeedbackStartTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
                    tvModuleFeedbackStartTimeNotify.setId(R.id.moduleStartTimeNotify);
                    tvModuleFeedbackStartTimeNotify.setText("Please choose feedback start date after now date");
                    moduleFeedbackStartDateWrap.addView(tvModuleFeedbackStartTimeNotify);

                    flag = false;
                }
            }
        }


        // check module feedback end time
        if(feedbackEndTime.isEmpty()){
            flag = false;
            try {
                TextView tvModuleFeedbackEndTimeNotify = root.findViewById(R.id.moduleFeedbackEndTimeNotify);
                ((ViewGroup) tvModuleFeedbackEndTimeNotify.getParent()).removeView(tvModuleFeedbackEndTimeNotify);
            }catch (Exception e){}


            TextView tvModuleFeedbackEndTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
            tvModuleFeedbackEndTimeNotify.setId(R.id.moduleFeedbackEndTimeNotify);
            tvModuleFeedbackEndTimeNotify.setText("Please choose start date or fill mm/dd/yyyy");
            moduleFeedbackEndDateWrap.addView(tvModuleFeedbackEndTimeNotify);

        }else {
            MyDate endFeedbackDate = convertStringToDate(feedbackEndTime);

            if(endFeedbackDate == null){
                flag = false;
                try {
                    TextView tvModuleFeedbackEndTimeNotify = root.findViewById(R.id.moduleFeedbackEndTimeNotify);
                    ((ViewGroup) tvModuleFeedbackEndTimeNotify.getParent()).removeView(tvModuleFeedbackEndTimeNotify);
                }catch (Exception e){}

                TextView tvModuleFeedbackEndTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
                tvModuleFeedbackEndTimeNotify.setId(R.id.moduleFeedbackStartTimeNotify);
                tvModuleFeedbackEndTimeNotify.setText("Date is not in the correct");
                moduleFeedbackEndDateWrap.addView(tvModuleFeedbackEndTimeNotify);
            }else{
                try {
                    TextView tvModuleFeedbackEndTimeNotify = root.findViewById(R.id.moduleFeedbackEndTimeNotify);
                    ((ViewGroup) tvModuleFeedbackEndTimeNotify.getParent()).removeView(tvModuleFeedbackEndTimeNotify);
                }catch (Exception e){}

                // check date
                MyDate starFeedbacktDate = convertStringToDate(feedbackStartTime);
                if(!compareDate(starFeedbacktDate, endFeedbackDate)){
                    TextView tvModuleFeedbackEndTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
                    tvModuleFeedbackEndTimeNotify.setId(R.id.moduleFeedbackStartTimeNotify);
                    tvModuleFeedbackEndTimeNotify.setText("Please choose Feedback end date after Feedback start date");
                    moduleFeedbackEndDateWrap.addView(tvModuleFeedbackEndTimeNotify);

                    flag = false;
                }
            }
        }



        // check accept
        if(flag){
            MyDate startDate = convertStringToDate(moduleStartTime);
            MyDate endDate = convertStringToDate(moduleEndTime);
            MyDate feedbackEndDate = convertStringToDate(feedbackEndTime);
            MyDate feedbackStartDate = convertStringToDate(feedbackStartTime);


            Module module = new Module();
            module.setModuleName(moduleName);
            module.setFeedbackId(listFeedback.get(indexFeedbackId).getSpinnerId());
            module.setAdminId(indexAdminId == 0 ? "current admin" : listAdmin.get(indexAdminId).getSpinnerId());

            module.setEndTime(endDate.getDate() + "/" + endDate.getMonth() + "/" + endDate.getYear());
            module.setStartTime(startDate.getDate() + "/" + startDate.getMonth() + "/" + startDate.getYear());

            module.setFeedbackStartTime(feedbackStartDate.getDate() + "/" + feedbackStartDate.getMonth() + "/" + feedbackStartDate.getYear());
            module.setFeedbackEndTime(feedbackEndDate.getDate() + "/" + feedbackEndDate.getMonth() + "/" + feedbackEndDate.getYear());



            ModuleAPIService.moduleAPIServices.editModule(
                    "Bearer "+ new UserInfo(root.getContext()).token(),
                    moduleId,
                    module

            ).enqueue(new Callback<ModuleInfo>() {
                @Override
                public void onResponse(Call<ModuleInfo> call, Response<ModuleInfo> response) {
                    ModuleInfo moduleInfo = response.body();

                    if(moduleInfo.getIsSuccess()){
                        ShowSuccessDialog(root);
                    }

                }

                @Override
                public void onFailure(Call<ModuleInfo> call, Throwable t) {

                }
            });
        }

        return flag;
    }

    void ShowSuccessDialog(View root){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayoutSuccess = inflater.inflate(R.layout.success_dialog_layout, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(root.getContext());
        alert.setView(alertLayoutSuccess);
        alert.setCancelable(false);
        AlertDialog dialogSuccess = alert.create();

        TextView txtSingleMessage = (TextView) alertLayoutSuccess.findViewById(R.id.txt_SingleMessage);
        txtSingleMessage.setText("Update Success!");

        final Button btnOk = (Button) alertLayoutSuccess.findViewById(R.id.btn_OK);
        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialogSuccess.dismiss();
            }
        });
        dialogSuccess.show();
    }

    private MyDate getCurrentDate(){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        Date date = calendar.getTime();
        int day = calendar.get(Calendar.DATE);
        //Note: +1 the month for current month
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        MyDate currentDate = new MyDate(day, month, year);
        return currentDate;
    }

    private boolean compareDate(MyDate firstDate, MyDate secondDate){
        if(firstDate.getYear() > secondDate.getYear()) return false;

        if(firstDate.getYear() == secondDate.getYear()){
            if(firstDate.getMonth() > secondDate.getMonth()) return false;

            if(firstDate.getMonth() == secondDate.getMonth()){
                if(firstDate.getDate() > secondDate.getDate()) return false;
            }
        }
        return true;
    }

    private MyDate convertStringToDate(String strDate){
        String[] arrOfStr = strDate.split("/");
        int lengthTerm = arrOfStr.length;
        if(lengthTerm != 3) return null;

        // check year
        int year = 2000;
        try{
            year = Integer.parseInt(arrOfStr[2]);
            if(year < 2000) return null;
        }catch (Exception ex){
            return null;
        }

        // check month
        int month = 1;
        try{
            month = Integer.parseInt(arrOfStr[0]);
            if(month > 12 || month < 1) return null;
        }catch (Exception ex){
            return null;
        }


        // check date
        int date = 1;
        try{
            date = Integer.parseInt(arrOfStr[1]);
            int[] arrMonth = {31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if(isLeapYear(year)){
                arrMonth[2] = 29;
            }

            if(date < 1 || date > arrMonth[month]) return null;

        }catch (Exception ex){
            return null;
        }

        return new MyDate(date, month, year);
    }

    private boolean isLeapYear (int year){
        boolean isLeap = false;
        if(year % 4 == 0)//chia hết cho 4 là năm nhuận
        {
            if( year % 100 == 0)
            //nếu vừa chia hết cho 4 mà vừa chia hết cho 100 thì không phải năm nhuận
            {
                if ( year % 400 == 0)//chia hết cho 400 là năm nhuận
                    isLeap = true;
                else
                    isLeap = false;//không chia hết cho 400 thì không phải năm nhuận
            }
            else//chia hết cho 4 nhưng không chia hết cho 100 là năm nhuận
                isLeap = true;
        }
        else {
            isLeap = false;
        }

        return isLeap;
    }

    private void getData(View root) {
        // init spinner
        CustomItem itemAdmin = new CustomItem("Current Admin", "current admin");
        listAdmin.add(itemAdmin);
        loadListItem(root, listAdmin, adminSpiner);

        CustomItem itemFeedback = new CustomItem("Feedback", "feedback");
        ArrayList<CustomItem> listFeedbacTemp = new ArrayList<CustomItem>();
        listFeedbacTemp.add(itemFeedback);
        loadListItem(root, listFeedbacTemp, feedbackSpinner);


        // get list admin
        AuthAPIService.authAPIServices.getListAdmin("Bearer "+ new UserInfo(root.getContext()).token()).enqueue(new Callback<ListAdminInfo>() {
            @Override
            public void onResponse(Call<ListAdminInfo> call, Response<ListAdminInfo> response) {
                ListAdminInfo listAdminInfo = response.body();

                for(Admin adminItem : listAdminInfo.getListAdmin()){
                    CustomItem item = new CustomItem(adminItem.getUserName(), adminItem.getId());
                    listAdmin.add(item);
                }

                loadListItem(root, listAdmin, adminSpiner);


                // get list feedback title
                FeedbackAPIServices.feedbackAPIServices.getListFeedback("Bearer "+ new UserInfo(root.getContext()).token()).enqueue(new Callback<ListFeedbackInfo>() {
                    @Override
                    public void onResponse(Call<ListFeedbackInfo> call, Response<ListFeedbackInfo> response) {
                        ListFeedbackInfo listFeedbackInfo = response.body();
                        for(Feedback feedbackItem : listFeedbackInfo.getListFeedback()){
                            CustomItem item = new CustomItem(feedbackItem.getTitle(), feedbackItem.getId());
                            listFeedback.add(item);
                        }
                        loadListItem(root, listFeedback, feedbackSpinner);


                        // get module info
                        ModuleAPIService.moduleAPIServices.getModule(
                                "Bearer "+ new UserInfo(root.getContext()).token(),
                                moduleId
                        ).enqueue(new Callback<ModuleInfo>() {
                            @Override
                            public void onResponse(Call<ModuleInfo> call, Response<ModuleInfo> response) {
                                ModuleInfo moduleInfo = response.body();
                                Module module = moduleInfo.getModule();
                                loadData(module);

                            }

                            @Override
                            public void onFailure(Call<ModuleInfo> call, Throwable t) {

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<ListFeedbackInfo> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<ListAdminInfo> call, Throwable t) {

            }
        });
    }

    void loadData(Module module){
        txt_AddModuleName.setText(module.getModuleName());

        indexAdminId = getPositionItemSelected(module.getAdminId(), listAdmin);
        indexFeedbackId = getPositionItemSelected(module.getFeedbackId(), listFeedback);
        adminSpiner.setSelection(indexAdminId);
        feedbackSpinner.setSelection(indexFeedbackId);


        SimpleDateFormat formatter_db = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date showDate = Calendar.getInstance().getTime();
        String strShowDate = "";

        // start module
        try {
            showDate = formatter_db.parse(module.getStartTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        strShowDate = formatter.format(showDate);
        txt_AddModuleStartDate.setText(strShowDate);


        // end module
        try {
            showDate = formatter_db.parse(module.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        strShowDate = formatter.format(showDate);
        txt_AddModuleEndDate.setText(strShowDate);


        // feddback start module
        try {
            showDate = formatter_db.parse(module.getFeedbackStartTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        strShowDate = formatter.format(showDate);
        txt_AddModuleFeedbackStartDate.setText(strShowDate);


        // feddback end module
        try {
            showDate = formatter_db.parse(module.getFeedbackEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        strShowDate = formatter.format(showDate);
        txt_AddModuleFeedbackEndDate.setText(strShowDate);
    }

    private int getPositionItemSelected (String spinnerId, ArrayList<CustomItem> customList){
        int index = -1;
        int length = customList.size();
        for(int i = 0; i < length; i++){
            if(customList.get(i).getSpinnerId().equals(spinnerId)){
                index = i;
                break;
            }
        }

        return index;
    }

    private void loadListItem (View root, ArrayList<CustomItem> customList, Spinner spinner){
        CustomItemAdapter adapter = new CustomItemAdapter(root.getContext(), customList);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddEditModuleViewModel.class);
        // TODO: Use the ViewModel
    }

}