package com.example.feedbackapp.ui.assignment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.ErrorResponse;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ListClass;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Login.ListAccount;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.ModelClassToSendAPI.Assignment.AddAssignmentInfo;
import com.example.feedbackapp.RetrofitAPISetvice.AssignmentAPIServices;
import com.example.feedbackapp.RetrofitAPISetvice.ClassAPIService;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.LoginAPIServices;
import com.example.feedbackapp.RetrofitAPISetvice.ModuleAPIService;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.model.Trainee;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAssignmentFragment extends Fragment {

    private AssignmentViewModel assignmentViewModel;

    // TODO: Control Varible
    Spinner spinner_module, spinner_class, spinner_trainer;
    Button btn_Save, btn_Back;

    // TODO: Khai báo các list và Adapter
    ArrayList<Module> moduleList;
    ArrayList<Classs> classList;
    ArrayList<Trainee> trainerList;

    //TODO: AccessToken Varible
    String accessToken = "";

    //TODO: Biến thông tin Assignment
    String moduleId, classId, trainerId;

    public AddAssignmentFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddAssignmentFragment newInstance(String param1, String param2) {
        AddAssignmentFragment fragment = new AddAssignmentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        assignmentViewModel =
                new ViewModelProvider(this).get(AssignmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_assignment, container, false);
        addEvents(root);
        accessToken = "Bearer "+ new UserInfo(root.getContext()).token();

        // Lấy danh sách Module, Class, Trainer và đổ lên Spinner
        LoadAllModule(root);
        LoadAllClass(root);
        LoadAllTrainer(root);
        return root;
    }

    //Lấy ds Module
    private void LoadAllModule(View root){
        ModuleAPIService.moduleAPIServices.getAllModule(accessToken).enqueue(new Callback<ListModule>() {
            @Override
            public void onResponse(Call<ListModule> call, Response<ListModule> response) {
                //moduleList = new ArrayList<Module>(Arrays.asList(response.body().getListModule()));
                moduleList = response.body().getListModule();
                setModuleSpinner(root);
            }

            @Override
            public void onFailure(Call<ListModule> call, Throwable t) {
            }
        });
    }

    //Lấy ds class
    private void LoadAllClass(View root) {
        ClassAPIService.classAPIService.getAllClass(accessToken).enqueue(new Callback<ListClass>() {
            @Override
            public void onResponse(Call<ListClass> call, Response<ListClass> response) {
                classList = new ArrayList<Classs>(Arrays.asList(response.body().getListClass()));
                setClassSpinner(root);
            }

            @Override
            public void onFailure(Call<ListClass> call, Throwable t) {
            }
        });
    }

    //Lấy ds trainer
    private void LoadAllTrainer(View root) {
        LoginAPIServices.loginAPIServices.getInfo().enqueue(new Callback<ListAccount>() {
            @Override
            public void onResponse(Call<ListAccount> call, Response<ListAccount> response) {
                trainerList = response.body().getListTrainer();
                setTrainerSpinner(root);
            }

            @Override
            public void onFailure(Call<ListAccount> call, Throwable t) {
            }
        });
    }

    //Hàm đổ ds Module lên spinner
    private void setModuleSpinner(View root){
        ArrayAdapter<Module> adapter =
                new ArrayAdapter<Module>(root.getContext(),  android.R.layout.simple_spinner_dropdown_item, moduleList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner_module.setAdapter(adapter);
    }

    //Đổ ds class lên spinner
    private void setClassSpinner(View root) {
        ArrayAdapter<Classs> adapter =
                new ArrayAdapter<Classs>(root.getContext(),  android.R.layout.simple_spinner_dropdown_item, classList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner_class.setAdapter(adapter);
    }

    //Đổ ds trainer lên spinner
    private void setTrainerSpinner(View root) {
        ArrayAdapter<Trainee> adapter =
                new ArrayAdapter<Trainee>(root.getContext(),  android.R.layout.simple_spinner_dropdown_item, trainerList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner_trainer.setAdapter(adapter);
    }

    //Hàm thêm sự kiện
    private void addEvents(View root) {
        addControls(root);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewAssignment();
            }

            private void AddNewAssignment() {
                Bundle bundle = new Bundle();
                AddAssignmentFragment addAssignmentFragment = new AddAssignmentFragment();
                addAssignmentFragment.setArguments(bundle);

                //Gọi API thêm Assignment
                AssignmentAPIServices.ASSIGNMENT_API_SERVICES.addNewAssignment(accessToken,new AddAssignmentInfo(moduleId,classId,trainerId))
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if(response.body() == null){
                                    Gson gson = new Gson();
                                    try {
                                        ErrorResponse errorResponse = gson.fromJson(
                                                response.errorBody().string(),
                                                ErrorResponse.class);
                                        if(errorResponse.getMessage().equals("Your action is done successfully"))
                                            ShowSuccessDialog(root, bundle);
                                        else
                                            ShowSuccessDialog(root, null);
                                        Toast toast = Toast.makeText(root.getContext(),  errorResponse.getMessage(),Toast.LENGTH_LONG);
                                        toast.show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                ShowSuccessDialog(root, null);
                                Log.e("TAG", "onFailure: ", t);
                            }
                        });
            }
        });

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","abc"); // Put anything what you want

                AddAssignmentFragment addAssignmentFragment = new AddAssignmentFragment();
                addAssignmentFragment.setArguments(bundle);

                Navigation.findNavController(root).navigate(R.id.add_assignment_to_assignment, bundle);
            }
        });

        //Events khi chọn item trên Spinner Module
        // When user select a List-Item.
        this.spinner_module.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandlerModule(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Events khi chọn item trên Spinner Class
        // When user select a List-Item.
        this.spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandlerClass(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Events khi chọn item trên Spinner Trainer
        // When user select a List-Item.
        this.spinner_trainer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandlerTrainer(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //Hàm thêm điều khiển
    private void addControls(View root) {
        spinner_module = (Spinner) root.findViewById(R.id.spinner_Trainer);
        spinner_class = (Spinner) root.findViewById(R.id.txt_className);
        spinner_trainer = (Spinner) root.findViewById(R.id.txt_moduleId);
        btn_Save = (Button) root.findViewById(R.id.btn_Save);
        btn_Back = (Button) root.findViewById(R.id.btn_Back);
    }

    //Xử lí sự kiện chọn item Module
    private void onItemSelectedHandlerModule(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Module module = (Module) adapter.getItem(position);
        moduleId = module.getId();
        Toast.makeText(view.getContext(), "Selected Module: " + module.getModuleName() ,Toast.LENGTH_SHORT).show();
    }

    //Xử lí sự kiện chọn item Class
    private void onItemSelectedHandlerClass(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Classs aClasss = (Classs) adapter.getItem(position);
        classId = aClasss.getId();
        Toast.makeText(view.getContext(), "Selected Class: " + aClasss.getClassName() ,Toast.LENGTH_SHORT).show();
    }

    //Xử lí sự kiện chọn item Trainer
    private void onItemSelectedHandlerTrainer(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Trainee trainee = (Trainee) adapter.getItem(position);
        trainerId = trainee.get_id();
        Toast.makeText(view.getContext(), "Selected Trainer: " + trainee.getUserName() ,Toast.LENGTH_SHORT).show();
    }

    //Xử lí hiển thị Dialog
    void ShowSuccessDialog(View root, Bundle bundle){
        //hiện dialog
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.success_dialog_layout, null);
        TextView note = (TextView) alertLayout.findViewById(R.id.txt_SingleMessage);
        note.setText("Add Successfully!");
        if(bundle == null){
            alertLayout = inflater.inflate(R.layout.failure_dialog_layout, null);
            note = (TextView) alertLayout.findViewById(R.id.txt_SingleErrorMessage);
            note.setText("Assignment already exist!");
        }
        final Button btnYes = (Button) alertLayout.findViewById(R.id.btn_OK);
        AlertDialog.Builder alert = new AlertDialog.Builder(this.getContext());
        alert.setView(alertLayout);
        alert.setCancelable(false);
        AlertDialog dialog = alert.create();
        btnYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
                if(bundle != null)
                Navigation.findNavController(root).navigate(R.id.add_assignment_to_assignment, bundle);
            }
        });
        dialog.show();
    }
}