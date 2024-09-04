package com.example.feedbackapp.ui.feedback.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.ui.feedback.Model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDetailAdapter extends RecyclerView.Adapter<QuestionDetailAdapter.ViewHolder> {
    private List<Question> listQuestions;
    ArrayList<Question> arrListQuestion;
    QuestionDetailAdapter(List<Question>listQuestions)
    {
        this.listQuestions =listQuestions;
    }
    @NonNull
    @Override
    public QuestionDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_detail_feedback_item_sub, parent,
                false);
        return new QuestionDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionDetailAdapter.ViewHolder holder, int position) {
        Question listQuestion = listQuestions.get(position);
        holder.txt_detail_question.setText(listQuestion.getQuestionContent());
    }

    @Override
    public int getItemCount() {
        return listQuestions.size();
    }

//    @Override
//    public void onCheckBoxChecking(ArrayList<ListQuestion> arrayList) {
//        arrListQuestion=arrayList;
//        Log.d("checkbox","Check box OK");
//    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_detail_question;
        ViewHolder(View itemView)
        {
            super(itemView);
            txt_detail_question = itemView.findViewById(R.id.txt_edit_question);
        }

    }
}
