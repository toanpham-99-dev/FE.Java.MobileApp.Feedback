package com.example.feedbackapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.TraineeForClass;
import com.example.feedbackapp.R;
import com.example.feedbackapp.ui.classs.ClassAddEditFragment;
import com.example.feedbackapp.ui.classs.ShowTraineeOfClassFragment;

import java.util.ArrayList;

public class ClassAdapterForTrainer extends RecyclerView.Adapter<ClassAdapterForTrainer.ViewHolder> {
    Context context;
    ArrayList<Classs> listClass;

    //dùng để hiển thị danh sách trainee tại 1 recycler view khác
    TraineeOfClassAdapter traineeOfClassAdapter;
    ArrayList<TraineeForClass> traineeList;
    RecyclerView rcvTraineeList;

    public ClassAdapterForTrainer(Context context, ArrayList<Classs> listClass) {
        this.context = context;
        this.listClass = listClass;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.item_class_for_trainer, parent, false);
        return new ClassAdapterForTrainer.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Classs classs = listClass.get(position);
        holder.txtTrainerClassID.setText(Html.fromHtml("<b>Class ID: </b>"+classs.getId()));
        holder.txtTrainerClassName.setText(Html.fromHtml("<b>Class Name: </b>"+classs.getClassName()));
        holder.txtTrainerNumber.setText(Html.fromHtml("<b>Number of Trainee: </b>"+classs.traineeCount()));

        holder.btnReadClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Bundle bundle = new Bundle();
                bundle.putString("ClassId",classs.getId());
                Navigation.findNavController(v).navigate(R.id.class_to_list_trainee, bundle);
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                ShowTraineeOfClassFragment showTraineeOfClassFragment = new ShowTraineeOfClassFragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.class_fragment_frame_layout,showTraineeOfClassFragment).addToBackStack(null).commit();
            }
        });
    }

    public Classs selectedClass(int position){
        Classs classs = listClass.get(position);
        return classs;
    }

    @Override
    public int getItemCount() {
        return listClass.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //khởi tạo các view
        TextView txtTrainerClassID, txtTrainerClassName, txtTrainerNumber;
        ImageButton btnReadClass;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTrainerClassID = itemView.findViewById(R.id.txt_trainerClassID);
            txtTrainerClassName = itemView.findViewById(R.id.txt_trainerCLassName);
            txtTrainerNumber = itemView.findViewById(R.id.txt_trainerNumber);

            btnReadClass = itemView.findViewById(R.id.btn_ReadClass);
            btnReadClass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
