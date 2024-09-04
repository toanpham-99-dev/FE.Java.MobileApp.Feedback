package com.example.feedbackapp.ui.dashboard;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.Adapter.FeedbackAdapter;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Auth.Admin;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Auth.ListAdminInfo;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Answer.AnswerInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Answer.ListAnswerInfor;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback.Feedback;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback.ListFeedbackInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback.Question;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.TraineeAssignment.TraineeAssignment;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.TraineeAssignment.TraineeAssignmentInfo;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.AnswerService;
import com.example.feedbackapp.RetrofitAPISetvice.FeedbackAPIServices;
import com.example.feedbackapp.RetrofitAPISetvice.TraineeAssignmentAPIService;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.model.CustomItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraineeDashboardFragment extends Fragment {
    ListFeedbackInfo listFeedbackInfo;
    FeedbackAdapter feedbackAdapter;
    ArrayList<Feedback> listFeedback;
    RecyclerView feedbackListRecycler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_trainee_dashboard, container, false);
        feedbackListRecycler = root.findViewById(R.id.rcv_feedbackList);
        listFeedback = new ArrayList<Feedback>();
        feedbackListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

       ShowJoinDialog(root);

        actGetListFeedback(root);
        // Test get list answer


        return root;
    }

    private void actGetListFeedback(View root){
        FeedbackAPIServices.feedbackAPIServices.getListFeedback(
                "Bearer "+ new UserInfo(root.getContext()).token()
        ).enqueue(new Callback<ListFeedbackInfo>() {
            @Override
            public void onResponse(Call<ListFeedbackInfo> call, Response<ListFeedbackInfo> response) {
                listFeedbackInfo = response.body();
                listFeedback = listFeedbackInfo.getListFeedback();
                showListFeedback(root);
            }

            @Override
            public void onFailure(Call<ListFeedbackInfo> call, Throwable t) {

            }
        });
    }


    private void showListFeedback(View root){
        feedbackAdapter = new FeedbackAdapter(root.getContext(), listFeedback);
        feedbackListRecycler.setAdapter(feedbackAdapter);
    }

    void ShowJoinDialog(View root){
        //hiện dialog login failed
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.join_module_dialog, null);

        final Button btnClose = (Button) alertLayout.findViewById(R.id.btn_closeDialog);
        final Button btnSubmit = (Button) alertLayout.findViewById(R.id.btn_submit);
        final EditText edtCodeValue = (EditText) alertLayout.findViewById(R.id.codeValue);
        final TextView txtCodeNotify = (TextView) alertLayout.findViewById(R.id.codeNotify);

        AlertDialog.Builder alert = new AlertDialog.Builder(root.getContext());
        alert.setView(alertLayout);
        alert.setCancelable(false);
        AlertDialog dialog = alert.create();

        btnClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String codeValue = edtCodeValue.getText().toString();
                if(codeValue.equals("")){
                    txtCodeNotify.setText("This code must have at least 1 character!");
                }else{
                    TraineeAssignment traineeAssignment = new TraineeAssignment();
                    traineeAssignment.setRegistrationCode(codeValue);

                    TraineeAssignmentAPIService.traineeAssignmentAPIServices.addTraineeAssignment(
                            "Bearer "+ new UserInfo(root.getContext()).token(),
                            traineeAssignment
                    ).enqueue(new Callback<TraineeAssignmentInfo>() {
                        @Override
                        public void onResponse(Call<TraineeAssignmentInfo> call, Response<TraineeAssignmentInfo> response) {
                            TraineeAssignmentInfo traineeAssignmentInfo = response.body();

                            if(traineeAssignmentInfo.getSuccess() == true){
                                LayoutInflater inflater = getLayoutInflater();
                                View alertLayoutSuccess = inflater.inflate(R.layout.success_dialog_layout, null);

                                AlertDialog.Builder alert = new AlertDialog.Builder(root.getContext());
                                alert.setView(alertLayoutSuccess);
                                alert.setCancelable(false);
                                AlertDialog dialogSuccess = alert.create();

                                TextView txtSingleMessage = (TextView) alertLayoutSuccess.findViewById(R.id.txt_SingleMessage);
                                txtSingleMessage.setText("Join Success!");

                                final Button btnOk = (Button) alertLayoutSuccess.findViewById(R.id.btn_OK);
                                btnOk.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        dialogSuccess.dismiss();
                                        dialog.dismiss();
                                    }
                                });
                                dialogSuccess.show();
                            }else{
                                String message = traineeAssignmentInfo.getMessage();

                                LayoutInflater inflater = getLayoutInflater();
                                View alertLayoutFail = inflater.inflate(R.layout.failure_dialog_layout, null);

                                AlertDialog.Builder alert = new AlertDialog.Builder(root.getContext());
                                alert.setView(alertLayoutFail);
                                alert.setCancelable(false);
                                AlertDialog dialogFail = alert.create();
                                TextView txtSingleMessage = (TextView) alertLayoutFail.findViewById(R.id.txt_SingleErrorMessage);

                                if(message.equals("This code is already exist")){
                                    txtSingleMessage.setText("You already join this module, please try another!");
                                }else if(message.equals("This code is not found")){
                                    txtSingleMessage.setText("Invalid Registation Code!!!");
                                }

                                final Button btnOk = (Button) alertLayoutFail.findViewById(R.id.btn_OK);
                                btnOk.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        dialogFail.dismiss();
                                    }
                                });
                                dialogFail.show();
                            }

                        }

                        @Override
                        public void onFailure(Call<TraineeAssignmentInfo> call, Throwable t) {
                        }
                    });


                }
            }
        });



        dialog.show();
    }
}
