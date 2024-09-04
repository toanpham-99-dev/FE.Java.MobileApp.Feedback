package com.example.feedbackapp.ui.statisticfeedback;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Answer.ListAnswerInfor;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.ModelClassToSendAPI.Answer.Answer;
import com.example.feedbackapp.R;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.Question;
import com.example.feedbackapp.RetrofitAPISetvice.AnswerService;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionAdpDetail extends RecyclerView.Adapter<QuestionAdpDetail.ViewHolder> {
    // Initialize Arraylist
    ArrayList<Question> arrayListQuestion;
    private Classs classChoosed;
    private Module moduleChoosed;
    private  ArrayList<Answer> answerArrayList = new ArrayList<Answer>();
    //Create constructor
   public QuestionAdpDetail(ArrayList<Question> arrayListQuestion, Classs classChoosed, Module moduleChoosed){
        this.arrayListQuestion = arrayListQuestion;
        this.classChoosed = classChoosed;
        this.moduleChoosed = moduleChoosed;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Initalize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_question_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Set question name on TextView
        // need a getPercnet in class ClassDataUtils.java by id question
        holder.questionName.setText("- " + arrayListQuestion.get(position).getQuestionContent());
        AnswerService.answerService.getAnswer(
                "Bear eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRiYTk1N2FhNjBjN2M3YzNlYTEiLCJ0eXBlVXNlciI6ImFkbWluIiwiaWF0IjoxNjIxODU5NDMwfQ.-GljSrlUF4b3nl8ojzpk1xK1O-_MX5B6a31g8u5eTp8"

        ).enqueue(new Callback<ListAnswerInfor>() {
            @Override
            public void onResponse(Call<ListAnswerInfor> call, Response<ListAnswerInfor> response) {

                ListAnswerInfor listAnswerInfor = response.body();
                if(listAnswerInfor.isSuccess()){
                    answerArrayList = listAnswerInfor.getListAnswer();
                    int indexCount0 =0,indexCount1 =0, indexCount2 =0, indexCount3 =0, indexCount4 =0;
                    for(int i = 0; i< answerArrayList.size(); i++){
                        if((answerArrayList.get(i).getClassId().equals(classChoosed.getId())) && (answerArrayList.get(i).getQuestionId().equals( arrayListQuestion.get(position).getId()))){
                            switch (answerArrayList.get(i).getValue()){
                                case 0:indexCount0++;break;
                                case 1:indexCount1++;break;
                                case 2:indexCount2++;break;
                                case 3: indexCount3++;break;
                                default:indexCount4++;break;
                            }
                        }
                    }
                    ArrayList<Integer> count = new ArrayList<>();
                    count.add(indexCount0);
                    count.add(indexCount1);
                    count.add(indexCount2);
                    count.add(indexCount3);
                    count.add(indexCount4);
                    Integer sum = 0;
                    sum = indexCount0 + indexCount1 + indexCount2 + indexCount3 +indexCount4;
                    ArrayList <Float> percentQuestion = new ArrayList<Float>();
                    for(int i=0;i<count.size();i++){
                        if(sum == 0) sum = 1;
                        percentQuestion.add((float)count.get(i)/sum*100);
                    }
                    holder.stronglyDisagree.setText(String.valueOf(percentQuestion.get(0)) +"%");
                    holder.disagree.setText(String.valueOf(percentQuestion.get(1)) +"%");
                    holder.neutral.setText(String.valueOf(percentQuestion.get(2)) +"%");
                    holder.strongAgree.setText(String.valueOf(percentQuestion.get(3)) +"%");
                    holder.agree.setText(String.valueOf(percentQuestion.get(4)) +"%");
                }


            }
            @Override
            public void onFailure(Call<ListAnswerInfor> call, Throwable t) {
                Log.d("TAG", "Fail");
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListQuestion.size();
    }
    // Initialize radio
    private void setPercent(final ViewHolder holder, int selection) {



    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Inittial variable
        TextView questionName, stronglyDisagree, disagree, neutral, agree, strongAgree;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Asign variable
            questionName = itemView.findViewById(R.id.questionName);
            stronglyDisagree = itemView.findViewById(R.id.StronglyDisagree);
            disagree = itemView.findViewById(R.id.Disagree);
            neutral = itemView.findViewById(R.id.Neutral);
            strongAgree = itemView.findViewById(R.id.StrongAgree);
            agree = itemView.findViewById(R.id.Agree);
        }
    }
}



