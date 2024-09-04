package com.example.feedbackapp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.Assignment;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.ErrorResponse;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.AssignmentAPIServices;
import com.example.feedbackapp.RetrofitAPISetvice.QuestionAPIServices;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.ui.assignment.AddAssignmentFragment;
import com.example.feedbackapp.ui.assignment.EditAssignmentFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder>{
    Context context;
    ArrayList<Assignment> assignmentArrayList;

    public AssignmentAdapter(Context context, ArrayList<Assignment> assignmentArrayList) {
        this.context = context;
        this.assignmentArrayList = assignmentArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.assignment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Gán dữ liêuk
        Assignment assignment = assignmentArrayList.get(position);
        holder.textView_idAssignment.setText(Html.fromHtml("<b>Topic Id: </b>" + assignment.getId()));
        holder.textView_moduleName.setText(Html.fromHtml("<b>Module Name: </b>" + assignment.getModuleName()));
        holder.textView_className.setText(Html.fromHtml("<b>Class Name: </b>" + assignment.getClassName()));
        holder.textView_trainerName.setText(Html.fromHtml("<b>Trainer Name: </b>" + assignment.getTrainerName()));
        holder.textView_rCode.setText(assignment.getRegistrationCode());

        holder.btn_edit_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("assignmentId",assignment.getId());
                bundle.putString("classId",assignment.getClassId());
                bundle.putString("className",assignment.getClassName());
                bundle.putString("moduleId",assignment.getModuleId());
                bundle.putString("moduleName",assignment.getModuleName());
                bundle.putString("trainerId",assignment.getTrainerId());
                bundle.putString("trainerName",assignment.getTrainerName());

                EditAssignmentFragment editAssignmentFragment = new EditAssignmentFragment();
                editAssignmentFragment.setArguments(bundle);

                Navigation.findNavController(v).navigate(R.id.assignment_to_edit_assignment, bundle);
                Toast.makeText(v.getContext(),"nhấn Edit Question" + position,Toast.LENGTH_LONG).show();
            }
        });
        holder.btn_delete_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowConfirmDialog(assignment.getClassId(),assignment.getId());
                Toast.makeText(v.getContext(),"nhấn Delete assignment" + position,Toast.LENGTH_LONG).show();
            }

            //Xử lí hiển thị ShowConfirmDialog
            void ShowConfirmDialog(String classId, String id){
                //hiện dialog
                LayoutInflater inflater = LayoutInflater.from(context);
                View alertLayout = inflater.inflate(R.layout.logout_confirm_dialog, null);
                TextView note = (TextView) alertLayout.findViewById(R.id.txt_LogoutMessage);
                if(classId.equals(""))
                    note.setText("Do you want to assignment thí question?");
                note.setText("This assignment is in use whit 1 Class. Do you want to delete thí assignment?");
                final Button btn_Yes = (Button) alertLayout.findViewById(R.id.btn_Yes);
                final Button btn_Cancel = (Button) alertLayout.findViewById(R.id.btn_Cancel);
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setView(alertLayout);
                alert.setCancelable(false);
                AlertDialog dialog = alert.create();
                btn_Yes.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.dismiss();
                        DeleteAssignment(id);
                    }
                });
                btn_Cancel.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

            //Hàm xử lí xóa assignment
            private void DeleteAssignment(String id) {
                String accessToken = "Bearer "+ new UserInfo(context).token();
                //Gọi api delete assignment
                AssignmentAPIServices.ASSIGNMENT_API_SERVICES.deleteAssignment(accessToken, id).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.body() != null){
                            Gson gson = new Gson();
                            try {
                                ErrorResponse errorResponse = gson.fromJson(
                                        response.body().string(),
                                        ErrorResponse.class);
                                if(errorResponse.getMessage().equals("Your action is done successfully"))
                                    ShowSuccessDialog(true);
                                else
                                    ShowSuccessDialog(false);
                                Toast toast = Toast.makeText(context,  errorResponse.getMessage(),Toast.LENGTH_LONG);
                                toast.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        ShowSuccessDialog(false);
                    }
                });
            }
            //Xử lí hiển thị ShowSuccessDialog
            void ShowSuccessDialog(Boolean isSuccess){
                //hiện dialog
                LayoutInflater inflater = LayoutInflater.from(context);
                View alertLayout = inflater.inflate(R.layout.success_dialog_layout, null);
                TextView note = (TextView) alertLayout.findViewById(R.id.txt_SingleMessage);
                note.setText("Delete Successfully!");
                if(isSuccess == false){
                    alertLayout = inflater.inflate(R.layout.failure_dialog_layout, null);
                    note = (TextView) alertLayout.findViewById(R.id.txt_SingleErrorMessage);
                    note.setText("Delete Fail!");
                }
                final Button btnYes = (Button) alertLayout.findViewById(R.id.btn_OK);
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setView(alertLayout);
                alert.setCancelable(false);
                AlertDialog dialog = alert.create();
                btnYes.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.dismiss();
                        notifyItemRemoved(position);
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignmentArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView btn_edit_assignment, btn_delete_assignment;
        TextView textView_idAssignment, textView_moduleName,
                textView_className, textView_trainerName, textView_rCode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ view
            btn_edit_assignment = itemView.findViewById(R.id.btn_edit_assignment);
            btn_delete_assignment = itemView.findViewById(R.id.btn_delete_assignment);

            textView_idAssignment = itemView.findViewById(R.id.textView_idTopic);
            textView_moduleName = itemView.findViewById(R.id.textView_topicName);
            textView_className = itemView.findViewById(R.id.textView_questionId);
            textView_trainerName = itemView.findViewById(R.id.textView_questionContent);
            textView_rCode = itemView.findViewById(R.id.textView_rCode);

            //nếu không phải admin, ẩn quyền thêm xóa sửa
            UserInfo userInfo = new UserInfo(itemView.getContext().getApplicationContext());
            if(!userInfo.role().equals("admin")){
                btn_edit_assignment.setVisibility(View.GONE);
                btn_delete_assignment.setVisibility(View.GONE);
            }
        }
    }
}
