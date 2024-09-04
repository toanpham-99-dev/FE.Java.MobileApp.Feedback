package com.example.feedbackapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback.Feedback;
import com.example.feedbackapp.ModelClassToSendAPI.Comment;
import com.example.feedbackapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    Context context;
    ArrayList<Comment> listComment;

    public CommentAdapter(Context context, ArrayList<Comment> listComment){
        this.context = context;
        this.listComment = listComment;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment_list_layout, parent, false);

        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }

    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comment comment = listComment.get(position);

        //gán dữ liệu cho từng item
        holder.txt_commentNumber.setText(String.valueOf(position) );
        holder.txt_commentContent.setText(comment.getComment());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //khởi tạo các view con trong 1 item
        TextView txt_commentNumber, txt_commentContent;

        //TextView txt
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_commentNumber = itemView.findViewById(R.id.txt_commentNumber);
            txt_commentContent = itemView.findViewById(R.id.txt_commentContent);

        }
    }
}


