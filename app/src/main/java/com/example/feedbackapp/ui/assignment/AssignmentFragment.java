package com.example.feedbackapp.ui.assignment;

import android.os.Bundle;

import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.Adapter.AssignmentAdapter;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.Assignment;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.AssignmentInfo;
import com.example.feedbackapp.ModelClassToSendAPI.Assignment.SearchAssignmentInfo;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.AssignmentAPIServices;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AssignmentFragment extends Fragment {

    private AssignmentViewModel assignmentViewModel;

    // TODO: Control Varible
    FloatingActionButton btn_Add_Assignment;
    Button btn_Search;
    EditText editText_Search;

    //TODO: AccessToken Varible
    String accessToken = "";
    String keySearch = "";

    //TODO: assignmentsList
    ArrayList<Assignment> assignmentsList;

    //TODO: Define RecyclerView and Adapter variable
    RecyclerView assignmentListRecycler;
    AssignmentAdapter assignmentAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        assignmentViewModel =
                new ViewModelProvider(this).get(AssignmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_assignment, container, false);
        final TextView textView = root.findViewById(R.id.text_question);
        assignmentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        addEvents(root);

        accessToken = "Bearer "+ new UserInfo(root.getContext()).token();

        AssignmentAPIServices.ASSIGNMENT_API_SERVICES.getAssignmentList(accessToken).enqueue(new Callback<AssignmentInfo>() {
            @Override
            public void onResponse(Call<AssignmentInfo> call, Response<AssignmentInfo> response) {
                assignmentsList = response.body().getAssignment();
                LoadAssignmentList(root);
                Log.d("AAAAAAAAAAAAAAAA", "onResponse()");
                Toast.makeText(getActivity(),"Load thành công!",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<AssignmentInfo> call, Throwable t) {
                Toast.makeText(getActivity(),"Có lỗi xảy ra!",Toast.LENGTH_LONG).show();
                Log.d("TAG", "onFailure()");
            }
        });

        return root;
    }

    private void addControls(View root){
        btn_Add_Assignment = (FloatingActionButton)root.findViewById(R.id.btn_add_question);
        btn_Search = (Button) root.findViewById(R.id.btn_Search);
        editText_Search = (EditText) root.findViewById(R.id.editText_Search);

        assignmentListRecycler = root.findViewById(R.id.assignmentList);

    }

    private void addEvents(View root){
        addControls(root);
        btn_Add_Assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","abc"); // Put anything what you want

                AddAssignmentFragment addAssignmentFragment = new AddAssignmentFragment();
                addAssignmentFragment.setArguments(bundle);

                Navigation.findNavController(root).navigate(R.id.assignment_to_add_assignment, bundle);
            }
        });
        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keySearch = editText_Search.getText().toString();
                AssignmentAPIServices.ASSIGNMENT_API_SERVICES
                        .searchAssignment(accessToken, new SearchAssignmentInfo(keySearch))
                        .enqueue(new Callback<AssignmentInfo>() {
                    @Override
                    public void onResponse(Call<AssignmentInfo> call, Response<AssignmentInfo> response) {
                        assignmentsList = response.body().getAssignment();
                        LoadAssignmentList(root);
                        Log.d("AAAAAAAAAAAAAAAA", "onResponse()");
                        Toast.makeText(getActivity(),"Tìm kiếm thành công!",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<AssignmentInfo> call, Throwable t) {
                        Toast.makeText(getActivity(),"Có lỗi xảy ra!",Toast.LENGTH_LONG).show();
                        Log.d("TAG", "onFailure()");
                    }
                });
            }
        });
    }


    //Get Assignment List for Adapter
    public void LoadAssignmentList(View root){
        assignmentAdapter = new AssignmentAdapter(root.getContext(),assignmentsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        assignmentListRecycler.setLayoutManager(layoutManager);
        //assignmentListRecycler.setHasFixedSize(true);
        assignmentListRecycler.setAdapter(assignmentAdapter);
    }
}