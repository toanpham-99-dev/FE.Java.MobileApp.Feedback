package com.example.feedbackapp.ui.module;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import androidx.navigation.fragment.NavHostFragment;

import androidx.navigation.Navigation;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.Toast;

import com.example.feedbackapp.Adapter.ModuleAdapter;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.ModuleAPIService;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.ui.assignment.AddAssignmentFragment;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModuleFragment extends Fragment {

    ModuleAdapter moduleAdapter;
    ListModule listModuleReceived;  //giá trị API trả về, gồm cả isSuccess, message
    ArrayList<Module> moduleList;
    RecyclerView moduleListRecycler;

    ImageButton imageButton;

    ImageButton btnAddModule;


    private ModuleViewModel mViewModel;

    public static ModuleFragment newInstance() {
        return new ModuleFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        LoadAllModule(getView());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.module_fragment, container, false);
        moduleListRecycler = root.findViewById(R.id.rcv_ModuleList);
        LoadAllModule(root);
        moduleListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));


        imageButton = root.findViewById(R.id.btn_AddModule);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_add_module);
            }
        });

//        btnAddModule = root.findViewById(R.id.btn_AddModule);
//        btnAddModule.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("key","abc"); // Put anything what you want
//                AddEditModuleFragment addEditModuleFragment = new AddEditModuleFragment();
//                addEditModuleFragment.setArguments(bundle);
//                Navigation.findNavController(root).navigate(R.id.module_to_add_module, bundle);
//                Toast.makeText(root.getContext(),"nhấn Add Module",Toast.LENGTH_LONG).show();
//            }
//        });
      
        //nếu không phải admin, ẩn quyền thêm xóa sửa
        UserInfo userInfo = new UserInfo(root.getContext());
        if(!userInfo.role().equals("admin")){
            //btnAddModule.setVisibility(View.GONE);
            imageButton.setVisibility(View.GONE);
        }

        return root;
    }

    private void LoadAllModule(View root){
        //"Bearer "+ new UserInfo(root.getContext()).token() - đáng ra là phải dùng token này để tương tác, nhưng retrofit đã hỗ trợ lưu
        //load list module lên fragment
//        listModuleReceived = response.body();
//        Toast.makeText(root.getContext(),listModuleReceived.getListModule().length,Toast.LENGTH_LONG).show();
//        setModuleAdapter(root);
        ModuleAPIService.moduleAPIServices.getAllModule("Bearer "+ new UserInfo(root.getContext()).token()).enqueue(new Callback<ListModule>() {
            @Override
            public void onResponse(Call<ListModule> call, Response<ListModule> response) {
                listModuleReceived = response.body();
                setModuleAdapter(root);
            }

            @Override
            public void onFailure(Call<ListModule> call, Throwable t) {

            }
        });
    }

    private void setModuleAdapter(View root){
        moduleList =  listModuleReceived.getListModule();
        moduleAdapter =new ModuleAdapter(root.getContext(), moduleList);
        Toast.makeText(root.getContext(),"thêm vào adapter thành công "+moduleList.size(),Toast.LENGTH_LONG).show();
        moduleListRecycler.setAdapter(moduleAdapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ModuleViewModel.class);
        // TODO: Use the ViewModel
    }

}