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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.ErrorResponse;
import com.example.feedbackapp.R;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Question.Question;
import com.example.feedbackapp.RetrofitAPISetvice.QuestionAPIServices;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.ui.assignment.EditAssignmentFragment;
import com.example.feedbackapp.ui.question.AddQuestionFragment;
import com.example.feedbackapp.ui.question.EditQuestionFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{
    Context context;
    ArrayList<Question> questionArrayList;

    public QuestionAdapter(Context context, ArrayList<Question> questionArrayList) {
        this.context = context;
        this.questionArrayList = questionArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.question_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Gán dữ liêuk
        Question question = questionArrayList.get(position);
        holder.textView_idTopic.setText(Html.fromHtml("<b>Topic Id: </b>"+ question.getTopicId()));
        holder.textView_topicName.setText(Html.fromHtml("<b>Topic Name: </b>"+question.getTopicName()));
        holder.textView_questionId.setText(Html.fromHtml("<b>Question Id: </b>"+question.getId()));
        holder.textView_questionContent.setText(Html.fromHtml("<b>Question Content: </b>"+question.getQuestionContent()));

        holder.btn_edit_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("questionId",question.getId());
                bundle.putString("topicId",question.getTopicId());
                bundle.putString("topicName",question.getTopicName());
                bundle.putString("questionContent",question.getQuestionContent());

                EditQuestionFragment editQuestionFragment = new EditQuestionFragment();
                editQuestionFragment.setArguments(bundle);

                Navigation.findNavController(v).navigate(R.id.question_to_edit_question, bundle);
                Toast.makeText(v.getContext(),"nhấn Edit Question" + position,Toast.LENGTH_LONG).show();
            }
        });
        holder.btn_delete_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowConfirmDialog(question.getUse(),question.getId());
                Toast.makeText(v.getContext(),"nhấn Delete Question" + position,Toast.LENGTH_LONG).show();
            }

            //Xử lí hiển thị ShowConfirmDialog
            void ShowConfirmDialog(Boolean isUse, String id){
                //hiện dialog
                LayoutInflater inflater = LayoutInflater.from(context);
                View alertLayout = inflater.inflate(R.layout.logout_confirm_dialog, null);
                TextView note = (TextView) alertLayout.findViewById(R.id.txt_LogoutMessage);
                if(isUse == false)
                    note.setText("Do you want to delete thí question?");
                note.setText("This Question is in use whit 1 Feedback. Do you want to delete thí question?");
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
                        DeleteQuestion(id);
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

            //Hàm xử lí xóa Question
            private void DeleteQuestion(String id) {
                String accessToken = "Bearer "+ new UserInfo(context).token();
                //Gọi api delete question
                QuestionAPIServices.QUESTION_API_SERVICES.deleteQuestion(accessToken, id).enqueue(new Callback<ResponseBody>() {
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
        return questionArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView btn_edit_question, btn_delete_question;
        TextView textView_idTopic, textView_topicName,
                textView_questionId, textView_questionContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ view
            btn_edit_question = itemView.findViewById(R.id.btn_edit_question);
            btn_delete_question = itemView.findViewById(R.id.btn_delete_question);

            textView_idTopic = itemView.findViewById(R.id.textView_idTopic);
            textView_topicName = itemView.findViewById(R.id.textView_topicName);
            textView_questionId = itemView.findViewById(R.id.textView_questionId);
            textView_questionContent = itemView.findViewById(R.id.textView_questionContent);
        }
    }
}
