package com.example.feedbackapp.ui.statisticfeedback;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.feedbackapp.Adapter.CommentAdapter;
import com.example.feedbackapp.Adapter.CustomAdapter;
import com.example.feedbackapp.Adapter.CustomApdapterModule;
import com.example.feedbackapp.Adapter.FeedbackAdapter;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassList;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Comment.ListComment;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback.Feedback;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback.ListFeedbackInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.ModelClassToSendAPI.Comment;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.CommentService;
import com.example.feedbackapp.RetrofitAPISetvice.FeedbackAPIServices;
import com.example.feedbackapp.model.Class;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCommentFragment extends Fragment {
    // Viewmodel
    private StatisticFeedBackViewModel mviewModel;
    private CustomAdapter adapter;
    private CustomApdapterModule adapterModule;
    private String accessToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRiYTk1N2FhNjBjN2M3YzNlYTEiLCJ0eXBlVXNlciI6ImFkbWluIiwiaWF0IjoxNjIxODU5NDMwfQ.-GljSrlUF4b3nl8ojzpk1xK1O-_MX5B6a31g8u5eTp8";

    //declare for spinner
    private Spinner spinner;// for clss
    private Spinner spinnerModule;
    private List<Classs> classess;
    private List<com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module> modules;

    //Button
    private Button showDetail;
    private Button showOverview;

    // get positon item of spinner
    private Classs classChoosed;
    private Module moduleChoosed;

    // Recycleview
    private RecyclerView rcvViewComment;
    public ViewCommentFragment() {
        // Required empty public constructor
    }

    //for comment
    ListComment listComment;
    CommentAdapter commentAdapter;
    ArrayList<Comment> arrayComment;
    RecyclerView rcvComment;

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

        // For comment

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_comment, container, false);
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

// Click event for button showdetail
        showDetail = (Button) v.findViewById(R.id.show_detail);
        this.showDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("class", spinner.getSelectedItemPosition());
                bundle.putInt("module",spinnerModule.getSelectedItemPosition());
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_feedbackdetail, bundle);
            }
        });
// When user select a List-Item on spinner Class
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id, view);
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
                onItemSelectedHandlerModule(parent, view, position, id, view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
// for comment
        rcvComment = v.findViewById(R.id.rcvComment);
        arrayComment = new ArrayList<Comment>();
        rcvComment.setLayoutManager(new LinearLayoutManager(getActivity()));

        actGetListComment(v);
// Code for rycycle view
        return v;
    }
    // handler click spinner
    // Class
    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id, View root) {
        Adapter adapter = adapterView.getAdapter();
        Classs clas = (Classs) adapter.getItem(position);
        classChoosed =(Classs) adapter.getItem(position);
        showListComment(root);
    }

    // module
    private void onItemSelectedHandlerModule(AdapterView<?> adapterView, View view, int position, long id, View root) {
        Adapter adapter = adapterView.getAdapter();
        Module module = (Module) adapter.getItem(position);
        String itemModule =module.getModuleName();
        moduleChoosed = (Module) adapter.getItem(position);
        showListComment(root);
    }
    // Function for show comment
    private void actGetListComment(View root){
        CommentService.commentService.getComment(
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRjMTk1N2FhNjBjN2M3YzNlYTIiLCJ0eXBlVXNlciI6InRyYWluZWUiLCJpYXQiOjE2MjE4NjAwNjB9.2fpi3Fs7bYl233OHKppcDVZwmcVz3aG1TubOh_ZWj9E"
        ).enqueue(new Callback<ListComment>() {
            @Override
            public void onResponse(Call<ListComment> call, Response<ListComment> response) {
                listComment = response.body();
                arrayComment = listComment.getListComment();
                Log.d("TAG", "Success: ");
                //showListComment(root);
            }

            @Override
            public void onFailure(Call<ListComment> call, Throwable t) {
                Log.d("TAG", "Fail! ");
            }
        });
    }


    private void showListComment(View root){
        ArrayList<Comment> commentArrayList = new ArrayList<Comment>();
        for(int i = 0; i < arrayComment.size(); i++)
        {
             if((arrayComment.get(i).getClassId().equals(classChoosed.getId())) && (arrayComment.get(i).getModuleId().equals( moduleChoosed.getId()))){
                 commentArrayList.add(arrayComment.get(i));
             }
        }
        commentAdapter = new CommentAdapter(root.getContext(), commentArrayList);
        rcvComment.setAdapter(commentAdapter);
    }
}