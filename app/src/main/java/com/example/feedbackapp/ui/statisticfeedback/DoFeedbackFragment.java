package com.example.feedbackapp.ui.statisticfeedback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.Adapter.ClassDataUtils;
import com.example.feedbackapp.Adapter.CustomAdapter;
import com.example.feedbackapp.Adapter.CustomApdapterModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassList;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.ListTopic;
import com.example.feedbackapp.R;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.model.HeaderRecycleView;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.Topic;

import java.util.ArrayList;
public class DoFeedbackFragment extends Fragment {

    public DoFeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mviewModel = ViewModelProviders.of(this).get(StatisticFeedBackViewModel.class);
        mviewModel.init();
        mviewModel.getTopicListLiveData().observe(this, new Observer<ListTopic>() {
            @Override
            public void onChanged(ListTopic listTopic) {
                //link: https://viblo.asia/p/android-architecture-components-viewmodel-xu-ly-configuration-changes-chua-bao-gio-don-gian-den-the-ByEZk3A4ZQ0

                // get data from bundle
                String classId = getArguments().getString("ClassId");
                String className = getArguments().getString("ClassName");
                String moduleId = getArguments().getString("ModuleId");
                String moduleName = getArguments().getString("ModuleName");
                // get headerRecycleView
                UserInfo userInfo = new UserInfo(getActivity());
                String userName = userInfo.username();
                headerRecycleView = new HeaderRecycleView(userName, moduleName, className);
                headerRecycleView.setClassId(classId);
                headerRecycleView.setModuleId(moduleId);
                //RecycleView
                arrayListTopic = listTopic.getListTopic();
               // add for header and footer rcv
                Topic a = new Topic();
                Topic b = new Topic();
                arrayListTopic.add(0,a);
                arrayListTopic.add(b);
                adapterTopic = new TopicAdp(getActivity(), arrayListTopic, headerRecycleView);
                // Initailize layout manager
                layoutManagerTopic = new LinearLayoutManager(getContext());
                // Set layout manager
                rcvListTopic.setLayoutManager(layoutManagerTopic);
                //set adapter
                rcvListTopic.setAdapter(adapterTopic);


            }
        });
    }
// My declare parameter
  //Get access token
private String accessToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRiYTk1N2FhNjBjN2M3YzNlYTEiLCJ0eXBlVXNlciI6ImFkbWluIiwiaWF0IjoxNjIxODU5NDMwfQ.-GljSrlUF4b3nl8ojzpk1xK1O-_MX5B6a31g8u5eTp8";

   // Fore rcv
    private StatisticFeedBackViewModel mviewModel;
    private ArrayList<Topic> topics;

    // Initialize variable
    RecyclerView rcvListTopic;
    ArrayList<Topic> arrayListTopic;
    HeaderRecycleView headerRecycleView;
    LinearLayoutManager layoutManagerTopic;
    TopicAdp adapterTopic;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_do_feedback, container, false);

// Code for rycycle view
        //Assign variable
        rcvListTopic = v.findViewById(R.id.rcvListTopic);
        mviewModel.getTopic(accessToken);
        return v;
    }
}