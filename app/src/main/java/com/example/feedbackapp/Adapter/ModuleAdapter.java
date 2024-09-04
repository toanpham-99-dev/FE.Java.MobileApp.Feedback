package com.example.feedbackapp.Adapter;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ModuleReturnByID;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.ModuleAPIService;
import com.example.feedbackapp.UserInfo.UserInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    Context context;
    ArrayList<Module> listModule;

    public ModuleAdapter(Context context, ArrayList<Module> listModule) {
        this.context = context;
        this.listModule = listModule;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.item_module_manager_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listModule.size(); // trả item tại vị trí postion
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //gán dữ liệu cho từng item
        Module module = listModule.get(position);
        holder.txtLabelModuleID.setText(Html.fromHtml("<b>Module ID: </b>"+ module.getId()));
        holder.txtLabelModuleName.setText(Html.fromHtml("<b>Module Name: </b>"+ module.getModuleName()));
        holder.txtLabelAdminID.setText(Html.fromHtml("<b>Admin ID: </b>"+ module.getAdminId()));
        holder.txtLabelStartDate.setText(Html.fromHtml("<b>Start Date: </b>"+ module.getStartTime()));
        holder.txtLabelEndDate.setText(Html.fromHtml("<b>End Date: </b>"+ module.getEndTime()));
        holder.txtLabelFeedbackTitle.setText(Html.fromHtml("<b>Feedback Title: </b>"+ module.getFeedbackTitle()));
        holder.txtLabelFeedbackStartTime.setText(Html.fromHtml("<b>Feedback Start Time: </b>"+ module.getFeedbackStartTime()));
        holder.txtLabelFeedbackEndTime.setText(Html.fromHtml("<b>Feedback End Time: </b>"+ module.getFeedbackEndTime()));

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteModule(module,position);
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("ModuleId", module.getId());
                Navigation.findNavController(v).navigate(R.id.module_to_edit_module, bundle);
            }
        });


    }

    //hàm hiển thị xác nhận xóa module
    void DeleteModule(Module module,int position){
        //hiện dialog xác nhận xóa
        LayoutInflater inflater = LayoutInflater.from(context);
        View alertLayout = inflater.inflate(R.layout.logout_confirm_dialog, null);
        //custom thông báo cho module (chưa bắt đầu và đang chạy)
        TextView txtMessage;
        txtMessage = alertLayout.findViewById(R.id.txt_LogoutMessage);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date curentDate = Calendar.getInstance().getTime();
        Date startDate = Calendar.getInstance().getTime();  //gán tạm để không null
        try {
            curentDate = formatter.parse(Calendar.getInstance().getTime().toString());
            startDate = formatter.parse(module.getStartTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(startDate.getTime()-curentDate.getTime() > 0){
            //nếu module chưa bắt đầu
            txtMessage.setText("Do you want to delete this Module?");
        }
        else{
            //nếu module đang chạy
            txtMessage.setText("This Module has been started. You really want to delete this Module?");
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
                doDelete(module);
                dialog.dismiss();
                listModule.remove(position);
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

    void doDelete(Module module){
        ModuleAPIService.moduleAPIServices.deleteModule("Bearer "+ new UserInfo(context).token(), module.getId()).enqueue(new Callback<Module>() {
            @Override
            public void onResponse(Call<Module> call, Response<Module> response) {
                if(response.isSuccessful()){
                    showDeleteMessage(true);
                }
                else{
                    showDeleteMessage(false);
                }
            }

            @Override
            public void onFailure(Call<Module> call, Throwable t) {
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

    class ViewHolder extends RecyclerView.ViewHolder {
        //khởi tạo các view con trong 1 item
        TextView txtLabelModuleID,
                txtLabelModuleName,
                txtLabelAdminID,
                txtLabelStartDate,
                txtLabelEndDate,
                txtLabelFeedbackTitle,
                txtLabelFeedbackStartTime,
                txtLabelFeedbackEndTime;
        ImageButton btnEdit,
                btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtLabelModuleID = itemView.findViewById(R.id.txt_labelModuleID);
            txtLabelModuleName = itemView.findViewById(R.id.txt_labelModuleName);
            txtLabelAdminID = itemView.findViewById(R.id.txt_labelAdminID);
            txtLabelStartDate = itemView.findViewById(R.id.txt_labelStartDate);
            txtLabelEndDate = itemView.findViewById(R.id.txt_labelendDate);
            txtLabelFeedbackTitle = itemView.findViewById(R.id.txt_labelFeedbackTitle);
            txtLabelFeedbackStartTime = itemView.findViewById(R.id.txt_labelFeedbackStartTime);
            txtLabelFeedbackEndTime = itemView.findViewById(R.id.txt_labelFeedbackEndTime);

            btnEdit = itemView.findViewById(R.id.btn_EditModule);
            btnDelete = itemView.findViewById(R.id.btn_DeleteModule);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context.getApplicationContext(),"nhấn Edit Module",Toast.LENGTH_LONG).show();
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context.getApplicationContext(),"nhấn Delete Module",Toast.LENGTH_LONG).show();
                }
            });
            //nếu không phải admin, ẩn quyền thêm xóa sửa
            UserInfo userInfo = new UserInfo(context.getApplicationContext());
            if(!userInfo.role().equals("admin")){
                btnEdit.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);
            }
        }
    }

    public void DeleteModule(int position){

    }
}


