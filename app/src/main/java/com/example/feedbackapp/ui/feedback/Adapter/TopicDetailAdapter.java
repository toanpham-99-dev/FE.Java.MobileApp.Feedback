package com.example.feedbackapp.ui.feedback.Adapter;

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

public class TopicDetailAdapter extends RecyclerView.Adapter<TopicDetailAdapter.ViewHolder> {
    private RecyclerView.RecycledViewPool viewPool =new RecyclerView.RecycledViewPool();
    List<Topic> topics;
    public TopicDetailAdapter(List<Topic> topics)
    {
        this.topics = topics;
    }

    @NonNull
    @Override
    public TopicDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_detail_feedback_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicDetailAdapter.ViewHolder holder, int position) {
        Topic topic = topics.get(position);
        holder.txt_Detail_Topic.setText(topic.getTopicName());

        // Create layout manager with initial prefetch item count
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rcv_Detail_subItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(topic.getListQuestion().size());
        //Create subItem view adapter
        QuestionDetailAdapter questionReviewAdapter = new QuestionDetailAdapter(topic.getListQuestion());
        holder.rcv_Detail_subItem.setLayoutManager(layoutManager);
        holder.rcv_Detail_subItem.setAdapter(questionReviewAdapter);
        holder.rcv_Detail_subItem.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_Detail_Topic;
        RecyclerView rcv_Detail_subItem;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //Ánh xạ view
            txt_Detail_Topic = itemView.findViewById(R.id.txt_edit_topic);
            rcv_Detail_subItem = itemView.findViewById(R.id.rcv_edit_sub);
        }
    }
}
