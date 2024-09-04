package com.example.feedbackapp.ui.feedback.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.constant.SystemConstant;
import com.example.feedbackapp.ui.feedback.Model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionReviewEditAdapter extends RecyclerView.Adapter<QuestionReviewEditAdapter.ViewHolder>{
    private List<Question> listQuestions;
    ArrayList<Question> arrListQuestion;
    QuestionReviewEditAdapter(List<Question>listQuestions)
    {
        this.listQuestions =listQuestions;
    }
    @NonNull
    @Override
    public QuestionReviewEditAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_review_edit_feedback_itemsub, parent,
                false);
        return new QuestionReviewEditAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionReviewEditAdapter.ViewHolder holder, int position) {
        Question listQuestion = listQuestions.get(position);
        for(int i = 0; i< SystemConstant.save_state_edit.size(); i++)
        {
            if(listQuestion.getId().contains(SystemConstant.save_state_edit.get(i)))
            {
                holder.txt_question_review.setText(listQuestion.getQuestionContent());
            }
        }

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
        TextView txt_question_review;
        ViewHolder(View itemView)
        {
            super(itemView);
            txt_question_review = itemView.findViewById(R.id.txt_edit_question);
        }

    }
}
