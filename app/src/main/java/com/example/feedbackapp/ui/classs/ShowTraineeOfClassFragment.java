package com.example.feedbackapp.ui.classs;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedbackapp.Adapter.TraineeOfClassAdapter;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.TraineeForClass;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.ClassAPIService;
import com.example.feedbackapp.UserInfo.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowTraineeOfClassFragment extends Fragment{
    private ShowTraineeOfClassViewModel mViewModel;
    TextView txtTraineeListClassID, txtTraineeListClassName;
    Button btnBack;
    String classID;
    TraineeOfClassAdapter traineeAdapter;
    Classs classs;
    ClassInfo classInfo;
    ArrayList<TraineeForClass> traineeList;
    RecyclerView rcvTraineeList;

    public static ShowTraineeOfClassFragment getInstance() {
        return new ShowTraineeOfClassFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.show_trainee_of_class_fragment, container, false);
        try{
            classID = getArguments().getString("ClassId");
            LoadTrainee(root);
        }
        catch(Exception ex) {

        }
        txtTraineeListClassID = root.findViewById(R.id.txt_TraineeListClassID);
        txtTraineeListClassName = root.findViewById(R.id.txt_TraineeListClassName);
        btnBack = root.findViewById(R.id.btn_TraineeListBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                if(fragmentManager.getBackStackEntryCount()>0){
//                    fragmentManager.popBackStack();
//                }
                Navigation.findNavController(v).navigate(R.id.show_trainee_back_to_class);
            }
        });

        rcvTraineeList = root.findViewById(R.id.rcv_TraineeList);
        rcvTraineeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }

    void LoadTrainee(View root){
        ClassAPIService.classAPIService.getClass("Bearer "+ new UserInfo(getContext()).token(),classID).enqueue(new Callback<ClassInfo>() {
            @Override
            public void onResponse(Call<ClassInfo> call, Response<ClassInfo> response) {
                if(response.isSuccessful()){
                    classInfo = response.body();
                    setLayoutValue(root);
                }
            }

            @Override
            public void onFailure(Call<ClassInfo> call, Throwable t) {

            }
        });
    }

    void setLayoutValue(View root){
        classs = classInfo.getClass_res();
        txtTraineeListClassID.setText(classs.getId());
        txtTraineeListClassName.setText(classs.getClassName());
        traineeList = new ArrayList<TraineeForClass>(Arrays.asList(classs.getListTrainee()));
        traineeAdapter = new TraineeOfClassAdapter(root.getContext(),traineeList);
        rcvTraineeList.setAdapter(traineeAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShowTraineeOfClassViewModel.class);
        // TODO: Use the ViewModel
    }
}