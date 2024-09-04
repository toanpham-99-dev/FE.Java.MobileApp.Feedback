package com.example.feedbackapp.ui.feedback.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.constant.SystemConstant;
import com.example.feedbackapp.ui.feedback.Model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionEditAdapter extends RecyclerView.Adapter<QuestionEditAdapter.ViewHolder> {
    private List<Question> listQuestions;
    public ArrayList<Question>arrayList_id =  new ArrayList<>();
    public ArrayList<String>id_typeFeedback = new ArrayList<>();


    public QuestionEditAdapter() {
        this.listQuestions = new ArrayList<>();
    }

    public QuestionEditAdapter(List<Question>listQuestions)
    {
        this.listQuestions =listQuestions;
    }
    @NonNull
    @Override
    public QuestionEditAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_edit_feedback_subitem, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionEditAdapter.ViewHolder holder, int position) {
        Question question = listQuestions.get(position);
        for(int i=0;i<SystemConstant.feedbackEditTopic3.size();i++)
        {
            for(int j=0;j<SystemConstant.feedbackEditTopic3.get(i).getListQuestion().size();j++)
            {
                if(question.getId().contains(SystemConstant.feedbackEditTopic3.get(i).getListQuestion().get(j).getId())) {
                    holder.ck_question.setChecked(true);
                    SystemConstant.save_state_edit.add(listQuestions.get(position).getId());
                    Log.i("SET ID QUESTION 1","OK");
                }
            }
        }

        holder.ck_question.setText(listQuestions.get(position).getQuestionContent());
        holder.ck_question.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holder.ck_question.isChecked())
                {
                    //Gán ID của Question vào danh sách để lưu question
                    SystemConstant.save_state_edit.add(listQuestions.get(position).getId());
                    Log.i("SET ID QUESTION 2","OK");

                }
                else
                {
                    SystemConstant.save_state_edit.remove(listQuestions.get(position).getId());
                    Log.i("DELETE ID QUESTION 3","OK");
                }

            }
        });

    }
    public ArrayList<Question> onclicked()
    {
        return SystemConstant.arrayList_id;
    }

    @Override
    public int getItemCount() {
        return listQuestions.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        CheckBox ck_question;
        ViewHolder(View itemView)
        {
            super(itemView);
            ck_question = itemView.findViewById(R.id.ck_question_edit);
        }

    }
}
