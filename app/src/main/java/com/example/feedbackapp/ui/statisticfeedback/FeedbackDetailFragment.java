package com.example.feedbackapp.ui.statisticfeedback;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.Adapter.CustomAdapter;
import com.example.feedbackapp.Adapter.CustomApdapterModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassList;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.ListTopic;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.Topic;
import com.example.feedbackapp.ModelClassToSendAPI.Answer.Answer;
import com.example.feedbackapp.R;
import com.example.feedbackapp.model.Class;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;

import java.util.ArrayList;
import java.util.List;

public class FeedbackDetailFragment extends Fragment {

    public FeedbackDetailFragment() {
        // Required empty public constructor
    }
    //My parameters
    // Initialize variable
    // Spiner
    // Viewmodel
    private StatisticFeedBackViewModel mviewModel;
    private CustomAdapter adapter;
    private CustomApdapterModule adapterModule;
    private String accessToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRiYTk1N2FhNjBjN2M3YzNlYTEiLCJ0eXBlVXNlciI6ImFkbWluIiwiaWF0IjoxNjIxODU5NDMwfQ.-GljSrlUF4b3nl8ojzpk1xK1O-_MX5B6a31g8u5eTp8";
    RecyclerView rcvDetail;
    private ArrayList<Topic> topics;
    LinearLayoutManager layoutManagerTopic;
    private TopicAdpDetail adapterTopic;

    private Spinner spinner;// for clss
    private Spinner spinnerModule;
    private List<Classs> classes;
    private List<com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module> modules;
    private Button showOverview;
    private Button viewComment;
    //for zoom in/out
    private float mScale = 1f;
    private ScaleGestureDetector mScaleGestureDetector;
    GestureDetector gestureDetector;

    // get positon item of spinner
    private Classs classChoosed;
    private Module moduleChoosed;
    private ArrayList<Answer> answerArrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mviewModel = ViewModelProviders.of(this).get(StatisticFeedBackViewModel.class);
        mviewModel.init();
        mviewModel.getClassListLiveData().observe(this, new Observer<ClassList>() {
            @Override
            public void onChanged(ClassList classList) {
                //link: https://viblo.asia/p/android-architecture-components-viewmodel-xu-ly-configuration-changes-chua-bao-gio-don-gian-den-the-ByEZk3A4ZQ0

                adapter = new CustomAdapter(getActivity(),
                        R.layout.spinner_item_layout,
                        R.id.textView_item_name,
                        classList.getListClass());
                spinner.setAdapter(adapter);
                spinner.setSelection(getArguments().getInt("class", 0));
            }
        });
        //Module
        mviewModel.getModuleListLiveData().observe(this, new Observer<ListModule>() {
            @Override
            public void onChanged(ListModule listModule) {
                //link: https://viblo.asia/p/android-architecture-components-viewmodel-xu-ly-configuration-changes-chua-bao-gio-don-gian-den-the-ByEZk3A4ZQ0
                //if (classList != null) {

                adapterModule = new CustomApdapterModule(getActivity(),
                        R.layout.spinner_item_layout,
                        R.id.textView_item_name,
                        listModule.getListModule());
                spinnerModule.setAdapter(adapterModule);

                spinnerModule.setSelection(getArguments().getInt("module", 0));
          }
        });
        mviewModel.getTopicListLiveData().observe(this, new Observer<ListTopic>() {
            @Override
            public void onChanged(ListTopic listTopic) {
                //link: https://viblo.asia/p/android-architecture-components-viewmodel-xu-ly-configuration-changes-chua-bao-gio-don-gian-den-the-ByEZk3A4ZQ0
                topics = listTopic.getListTopic();

                //Initialize topic adapter
                adapterTopic = new TopicAdpDetail(getActivity(), topics, classChoosed, moduleChoosed);
                // Initailize layout manager
                layoutManagerTopic = new LinearLayoutManager(getContext());
                // Set layout manager
                rcvDetail.setLayoutManager(layoutManagerTopic);
                //set adapter
                rcvDetail.setAdapter(adapterTopic);

            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_feedback_detail, container, false);
// Code for spinner c

        this.spinner = (Spinner) v.findViewById(R.id.txt_className);
        mviewModel.getClas(accessToken);
        this.spinnerModule = (Spinner) v.findViewById(R.id.spinner_module);
        mviewModel.getModule(accessToken);

// Click event for button View Detail
        showOverview = (Button) v.findViewById(R.id.show_overview);
        this.showOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("class", spinner.getSelectedItemPosition());
                bundle.putInt("module",spinnerModule.getSelectedItemPosition());
                mviewModel.setClassPosition(spinner.getSelectedItemPosition());
                mviewModel.setModulePosition(spinnerModule.getSelectedItemPosition());
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_statisticdofeedback, bundle);
            }
        });

// Click event for button View Comment
        viewComment = (Button) v.findViewById(R.id.view_comment);
        this.viewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("class", spinner.getSelectedItemPosition());
                bundle.putInt("module",spinnerModule.getSelectedItemPosition());
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_viewcommentfeedback, bundle);
            }
        });
// When user select a List-Item on spinner Class
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //spinner.setSelection(o_onchange);
// When user select a List-Item on spinner Module
        this.spinnerModule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandlerModule(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

// Code for rycycle view
        //Assign variable
      //  mviewModel.getTopic(accessToken);
        rcvDetail = v.findViewById(R.id.rcvDetail);
        return v;
    }
// handler click spinner
    // Class
    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Classs clas = (Classs) adapter.getItem(position);
        classChoosed =(Classs) adapter.getItem(position);
        mviewModel.getTopic(accessToken);
    }

// module
    private void onItemSelectedHandlerModule(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Module module = (Module) adapter.getItem(position);
        String itemModule =module.getModuleName();
        moduleChoosed = (Module) adapter.getItem(position);
        mviewModel.getTopic(accessToken);
    }

}