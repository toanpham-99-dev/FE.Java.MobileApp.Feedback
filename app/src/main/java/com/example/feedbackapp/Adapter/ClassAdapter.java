package com.example.feedbackapp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.ClassAPIService;
import com.example.feedbackapp.RetrofitAPISetvice.ModuleAPIService;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.ui.classs.ClassAddEditFragment;
import com.example.feedbackapp.ui.classs.ClasssFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {
    Context context;
    ArrayList<Classs> listClass;

    public ClassAdapter(Context context, ArrayList<Classs> listClass) {
        this.context = context;
        this.listClass = listClass;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.item_class_manager_layout, parent, false);
        return new ClassAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Classs classs = listClass.get(position);
        holder.txtClassID.setText(Html.fromHtml("<b>Class ID: </b>"+classs.getId()));
        holder.txtClassName.setText(Html.fromHtml("<b>Class Name: </b>"+classs.getClassName()));
        holder.txtClassCapacity.setText(Html.fromHtml("<b>Capacity: </b>"+classs.getCapacity()));
        holder.txtClassStartDate.setText(Html.fromHtml("<b>Start Date: </b>"+classs.getStartTime()));
        holder.txtClassEndDate.setText(Html.fromHtml("<b>End Date: </b>"+classs.getEndTime()));

        holder.btnEditClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("ClassId",classs.getId());
                Navigation.findNavController(v).navigate(R.id.class_to_add_edit_class, bundle);
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                ClassAddEditFragment editClassFragment = new ClassAddEditFragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.class_fragment_frame_layout,editClassFragment).addToBackStack(null).commit();
            }
        });
        holder.btnDeleteClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteClass(classs,position);
            }
        });
    }

    void goToEditClass(View view, Classs classs){
//        AppCompatActivity activity = (AppCompatActivity) view.getContext();
//        ClassAddEditFragment editClassFragment = new ClassAddEditFragment();
//        activity.getSupportFragmentManager().beginTransaction().replace(R.id.class_fragment_frame_layout,editClassFragment).addToBackStack(null).commit();
        Navigation.findNavController(view).navigate(R.id.class_to_add_edit_class);
    }

    //hàm hiển thị xác nhận xóa class
    void DeleteClass(Classs classs, int position){
        //hiện dialog xác nhận xóa
        LayoutInflater inflater = LayoutInflater.from(context);
        View alertLayout = inflater.inflate(R.layout.logout_confirm_dialog, null);
        //custom thông báo cho module (chưa bắt đầu và đang chạy)
        TextView txtMessage;
        txtMessage = alertLayout.findViewById(R.id.txt_LogoutMessage);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date curentDate = Calendar.getInstance().getTime();
        Date startDate = Calendar.getInstance().getTime();  //gán tạm để không null
        Date endDate = Calendar.getInstance().getTime();  //gán tạm để không null
        try {
            curentDate = formatter.parse(Calendar.getInstance().getTime().toString());
            startDate = formatter.parse(classs.getStartTime());
            endDate = formatter.parse(classs.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if((startDate.getTime() - curentDate.getTime() > 0) || (curentDate.getTime() - endDate.getTime() > 0 )){
            //nếu class chưa bắt đầu hoặc đã kết thúc
            txtMessage.setText("Do you want to delete this item?");
        }
        else{
            //nếu class đang hoạt động
            txtMessage.setText("Class is operating. Do you really want to delete this class?");
        }
        final Button btnYes = (Button) alertLayout.findViewById(R.id.btn_Yes);
        final Button btnCancel = (Button) alertLayout.findViewById(R.id.btn_Cancel);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(alertLayout);
        alert.setCancelable(false);
        AlertDialog dialog = alert.create();
        btnYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //thực hiện gọi API xóa module
                Toast.makeText(context,"delete confirmed!",Toast.LENGTH_LONG).show();
                doDelete(classs);
                dialog.dismiss();
                listClass.remove(position);
                notifyDataSetChanged();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //không làm gì cả
                Toast.makeText(context,"delete canceled!",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    void doDelete(Classs classs){
        ClassAPIService.classAPIService.deleteClass("Bearer "+ new UserInfo(context).token(), classs.getId()).enqueue(new Callback<Classs>() {
            @Override
            public void onResponse(Call<Classs> call, Response<Classs> response) {
                if(response.isSuccessful()){
                    showDeleteMessage(true);
                }
                else{
                    showDeleteMessage(false);
                }
            }

            @Override
            public void onFailure(Call<Classs> call, Throwable t) {
                showDeleteMessage(false);
            }
        });
    }

    void showDeleteMessage(boolean success){
        LayoutInflater inflater = LayoutInflater.from(context);
        final Button btnOK;
        TextView txtMessage;
        View alertLayout;
        if(success){
            alertLayout = inflater.inflate(R.layout.success_dialog_layout, null);
            btnOK = alertLayout.findViewById(R.id.btn_OK);
            txtMessage = alertLayout.findViewById(R.id.txt_SingleMessage);
            txtMessage.setText("Delete success!");
        }
        else{
            alertLayout = inflater.inflate(R.layout.failure_dialog_layout, null);
            btnOK = alertLayout.findViewById(R.id.btn_OK);
            txtMessage = alertLayout.findViewById(R.id.txt_SingleErrorMessage);
            txtMessage.setText("Delete fail!");
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(alertLayout);
        alert.setCancelable(false);
        AlertDialog dialog = alert.create();
        btnOK.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return listClass.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //khởi tạo các view
        TextView txtClassID, txtClassName, txtClassCapacity, txtClassStartDate, txtClassEndDate;
        ImageButton btnEditClass, btnDeleteClass;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtClassID = itemView.findViewById(R.id.txt_labelClassID);
            txtClassName = itemView.findViewById(R.id.txt_labelCLassName);
            txtClassCapacity = itemView.findViewById(R.id.txt_labelClassCapacity);
            txtClassStartDate = itemView.findViewById(R.id.txt_labelClassStartDate);
            txtClassEndDate = itemView.findViewById(R.id.txt_labelClassEndDate);

            btnEditClass = itemView.findViewById(R.id.btn_EditClass);
            btnDeleteClass = itemView.findViewById(R.id.btn_DeleteClass);

            btnEditClass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context.getApplicationContext(),"Click Edit Class!",Toast.LENGTH_LONG).show();
                }
            });
            btnDeleteClass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context.getApplicationContext(),"Click Delete Class!",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
