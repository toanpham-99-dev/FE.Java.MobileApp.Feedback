package com.example.feedbackapp.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.TraineeForClass;
import com.example.feedbackapp.R;

import java.util.ArrayList;

public class TraineeOfClassAdapter extends RecyclerView.Adapter<TraineeOfClassAdapter.ViewHolder> {
    Context context;
    ArrayList<TraineeForClass> traineeList;

    public TraineeOfClassAdapter(Context context, ArrayList<TraineeForClass> traineeList) {
        this.context = context;
        this.traineeList = traineeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.item_trainee_list_layout, parent, false);
        return new TraineeOfClassAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TraineeForClass trainee = traineeList.get(position);
        holder.txtNumber.setText(Html.fromHtml("<b>Number: </b>"+ (position+1)));
        holder.txtTraineeID.setText(Html.fromHtml("<b>Trainee ID: </b>"+trainee.getId()));
        holder.txtTraineeName.setText(Html.fromHtml("<b>Trainee Name: </b>"+trainee.getName()));
    }

    @Override
    public int getItemCount() {
        return traineeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //khởi tạo các view
        TextView txtNumber, txtTraineeID, txtTraineeName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNumber = itemView.findViewById(R.id.txt_traineeNumber);
            txtTraineeID = itemView.findViewById(R.id.txt_traineeID);
            txtTraineeName = itemView.findViewById(R.id.txt_traineeName);
        }
    }
}
