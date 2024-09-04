package com.example.feedbackapp.ui.feedback.Adapter;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.ErrorResponse;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.AssignmentAPIServices;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.constant.SystemConstant;
import com.example.feedbackapp.ui.feedback.FeedBackFragment;
import com.example.feedbackapp.ui.feedback.Fragment_Edit_Feedback;
import com.example.feedbackapp.ui.feedback.Model.FeedbackEditFeedbackList2;
import com.example.feedbackapp.ui.feedback.Model.FeedbackEditFilterId1;
import com.example.feedbackapp.ui.feedback.Model.FeedbackEditTopic3;
import com.example.feedbackapp.ui.feedback.Model.ListFeedback;
import com.example.feedbackapp.ui.feedback.Service.APIService;
import com.example.feedbackapp.ui.feedback.Service.DataService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {
    List<ListFeedback> listFeedback;
    public  FeedbackAdapter(List<ListFeedback>listFeedback)
    {
        this.listFeedback=listFeedback;
    }
    FeedBackFragment feedBackFragment = new FeedBackFragment();



    @NonNull
    @Override
    public FeedbackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //gán view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_feedback_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListFeedback list = listFeedback.get(position);
        holder.txtFeedbackId.setText(Html.fromHtml("<b>Feedback ID: </b>" + list.getTypeFeedbackId()));
        holder.txtFeedbackTitle.setText(Html.fromHtml("<b>Title: </b>" + list.getTitle()));
        holder.txtAdminId.setText(Html.fromHtml("<b>Admin Id: </b>" + list.getAdminId()));

        holder.imgEditFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataService dataServiceFilter = APIService.getService();
                Bundle bundle = new Bundle();
                bundle.putString("feedbackId",list.getId());
                bundle.putString("feedbackTitle",list.getTitle());
                UserInfo userInfo = new UserInfo(v.getContext());
                Call<FeedbackEditFilterId1> callFeedbackFilter = dataServiceFilter.GetDataFilterIdFeedback("Bearer "+userInfo.token(),list.getId());
                callFeedbackFilter.enqueue(new Callback<FeedbackEditFilterId1>() {
                    @Override
                    public void onResponse(Call<FeedbackEditFilterId1> call, Response<FeedbackEditFilterId1> response) {
                        FeedbackEditFilterId1 feedbackEditFilterId1 = (FeedbackEditFilterId1) response.body();
                        FeedbackEditFeedbackList2 feedbackEditFeedbackList2 =feedbackEditFilterId1.getFeedback();
                         SystemConstant.feedbackEditTopic3 = feedbackEditFeedbackList2.getListTopic();

                        Log.i("TEST GET FEEDBACK OK","OK");
                    }

                    @Override
                    public void onFailure(Call<FeedbackEditFilterId1> call, Throwable t) {

                    }
                });
                Navigation.findNavController(v).navigate(R.id.nav_edit_feedback,bundle);
            }
        });
        
        holder.imgDeleditFeedback.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                DataService dataService = APIService.getService();
                UserInfo userInfo = new UserInfo(v.getContext());
                dataService.DeleteFeedback("Bearer "+userInfo.token(),list.getId()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(v.getContext(),"DELETE was successfull",Toast.LENGTH_LONG).show();
                        Navigation.findNavController(v).navigate(R.id.nav_feedback);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }

        });
    }
    public int getItemCount()
    {
        return listFeedback.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtFeedbackId;
        TextView txtFeedbackTitle;
        TextView txtAdminId;
        ImageView imgDetailFeedback;
        ImageView imgEditFeedback;
        ImageView imgDeleditFeedback;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //Ánh xạ view
            txtFeedbackId =itemView.findViewById(R.id.txtFeedbackID);
            txtFeedbackTitle =itemView.findViewById(R.id.txtFeedbackTitle);
            txtAdminId=itemView.findViewById(R.id.txtFeedbackAdminID);
            imgEditFeedback=itemView.findViewById(R.id.btn_Edit);
            imgDetailFeedback=itemView.findViewById(R.id.btnView);
            imgDeleditFeedback =itemView.findViewById(R.id.btnDelete);
        }
    }
}
