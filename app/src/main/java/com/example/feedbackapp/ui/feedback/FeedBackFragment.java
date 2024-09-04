package com.example.feedbackapp.ui.feedback;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.constant.SystemConstant;
import com.example.feedbackapp.ui.feedback.Adapter.FeedbackAdapter;
import com.example.feedbackapp.ui.feedback.Model.FeedackViewModel;
import com.example.feedbackapp.ui.feedback.Model.FeedbackEditFeedbackList2;
import com.example.feedbackapp.ui.feedback.Model.FeedbackEditFilterId1;
import com.example.feedbackapp.ui.feedback.Model.FeedbackEditTopic3;
import com.example.feedbackapp.ui.feedback.Model.ListFeedbackModel;
import com.example.feedbackapp.ui.feedback.Service.APIService;
import com.example.feedbackapp.ui.feedback.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedBackFragment extends Fragment {

    private FeedackViewModel mViewModel;
    Button btnView,btnEdit,btnDelete;
    ImageView btnAddFeedback;
    ImageView btn_Edit;
    FeedbackEditFilterId1 feedbackEditFilterId1;


    public static FeedBackFragment newInstance() {
        return new FeedBackFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feedback_fragment,container,false);

        GetData();

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rcvFeedback);
        DataService dataService = APIService.getService();
        UserInfo userInfo = new UserInfo(getContext());
        SystemConstant.admin_token=userInfo.token();


        Call<ListFeedbackModel> callback = dataService.GetDataListFeedback("Bearer "+userInfo.token());
        callback.enqueue(new Callback<ListFeedbackModel>()
        {
            @Override
            public void onResponse(Call<ListFeedbackModel> call, Response<ListFeedbackModel> response) {
                ListFeedbackModel listfeedback = (ListFeedbackModel) response.body();
                FeedbackAdapter feedbackAdapter = new FeedbackAdapter(listfeedback.getListFeedback());

                recyclerView.setAdapter(feedbackAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                Log.d("BBB",listfeedback.getMessage());
            }

            @Override
            public void onFailure(Call<ListFeedbackModel> call, Throwable t) {
                Log.d("DDD","Not OK");
            }
        });



        btnAddFeedback = (ImageView) view.findViewById(R.id.btnAddFeedback);
        btnAddFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_add_feedback);
            }
        });
        /*btn_Edit = (ImageView) view.findViewById(R.id.btn_Edit);
        btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_edit_feedback);
            }
        });*/

        return view;
    }

    private void GetData() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FeedackViewModel.class);
        // TODO: Use the ViewModel

    }

}