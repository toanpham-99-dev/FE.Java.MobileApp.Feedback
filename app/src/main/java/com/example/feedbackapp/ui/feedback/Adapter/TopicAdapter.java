package com.example.feedbackapp.ui.feedback.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.ui.feedback.Model.Topic;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {
    private RecyclerView.RecycledViewPool viewPool =new RecyclerView.RecycledViewPool();
    List<Topic> topics;
    QuestionAdapter questionAdapter;

    public TopicAdapter() {
    }

    public Topic topic;
    public TopicAdapter (List<Topic> topics)
    {
        this.topics = topics;
    }

    @NonNull
    @Override
    public TopicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_create_feedback_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicAdapter.ViewHolder holder, int position) {
        topic = topics.get(position);
        holder.txt_Topic.setText(topic.getTopicName());


        // Create layout manager with initial prefetch item count
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rcv_subItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(topic.getListQuestion().size());
        //Create subItem view adapter
        questionAdapter = new QuestionAdapter(topic.getListQuestion());
        holder.rcv_subItem.setLayoutManager(layoutManager);
        holder.rcv_subItem.setAdapter(questionAdapter);
        holder.rcv_subItem.setRecycledViewPool(viewPool);

    }
    public QuestionAdapter GetQuestionAdapter()
    {
        Log.i("TOPIC VALUES RESPONSE", "topic.getListQuestion().toString()");
        questionAdapter = new QuestionAdapter(topic.getListQuestion());
        return this.questionAdapter;
    }


    @Override
    public int getItemCount() {
        return topics.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_Topic;
        RecyclerView rcv_subItem;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //Ánh xạ view
            txt_Topic = itemView.findViewById(R.id.txt_topic_edit);
            rcv_subItem = itemView.findViewById(R.id.rcv_question_edit);
        }
    }
}
