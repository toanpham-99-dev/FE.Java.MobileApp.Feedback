package com.example.feedbackapp.ui.statisticfeedback;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.Adapter.ClassDataUtils;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.R;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.Question;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.Topic;

import java.util.ArrayList;

public class TopicAdpDetail extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // Initial activity and array list
    private Activity activity;
    ArrayList<Topic> arrayListTopic;
    private Classs classChoosed;
    private Module moduleChoosed;
    private ArrayList<Question> arrayListQuestion;

    //Create contructor
    TopicAdpDetail(Activity activity, ArrayList<Topic> arrayListTopic, Classs classChoosed, Module moduleChoosed){
        this.activity =activity;
        this.arrayListTopic = arrayListTopic;
        this.classChoosed = classChoosed;
        this.moduleChoosed = moduleChoosed;
        //this.arrayListQuestion = arrayListQuestion;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_row_topic, parent, false);
            return new TopicViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
           String stt;
           switch (position){
               case 0: stt = "I. ";break;
               case 1: stt = "II. ";break;
               case 2: stt = "III. ";break;
               default: stt = "IV. ";break;
           }

            // Set topic name on TextView
        TopicViewHolder topicViewHolder = (TopicViewHolder) holder;
            //holder.topicName.setText(arrayListTopic.get(position));
            topicViewHolder.topicName.setText(stt + arrayListTopic.get(position).getTopicName());

            //Initialize memer ArrayList
            //ArrayList<Question> arrayListQuestion = new ArrayList<>();

            arrayListQuestion = arrayListTopic.get(position).getListQuestion();

            //Initialize member adapter
            QuestionAdpDetail adapterQuestion = new QuestionAdpDetail(arrayListQuestion, classChoosed, moduleChoosed);

            //Initialize layout manager
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
            //Set layout manager
            topicViewHolder.rcvListQuestion.setLayoutManager(linearLayoutManager);
            // Set adapter
            topicViewHolder.rcvListQuestion.setAdapter(adapterQuestion);
    }

    @Override
    public int getItemCount() {
        return arrayListTopic.size();
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable
        TextView topicName;
        RecyclerView rcvListQuestion;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            topicName = itemView.findViewById(R.id.topicName);
            rcvListQuestion = itemView.findViewById(R.id.rcvListQuestion);

        }
    }

}
//Link refer:https://eitguide.net/multiple-view-type-trong-recyclerview/
//link refer header footer: http://www.devexchanges.info/2016/10/adding-header-and-footer-layouts-for.html