package com.example.feedbackapp.ui.assignment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.ErrorResponse;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Login.ListAccount;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.ModelClassToSendAPI.Assignment.EditAssignmentInfo;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.AssignmentAPIServices;
import com.example.feedbackapp.RetrofitAPISetvice.LoginAPIServices;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.model.Trainee;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAssignmentFragment extends Fragment {

    private AssignmentViewModel assignmentViewModel;

    // TODO: Control Varible
    TextView txt_classId, txt_className, txt_moduleId, txt_moduleName;
    Spinner spinner_Trainer;
    Button btn_Save, btn_Back;

    // TODO: Khai báo các list và Adapter
    ArrayList<Trainee> trainerList;
    Trainee trainee;

    //TODO: AccessToken Varible
    String accessToken = "";

    //TODO: Biến thông tin Assignment
    String assignmentId, classId, className, moduleId, moduleName, trainerId, trainerName;

    public EditAssignmentFragment() {
        // Required empty public constructor
    }

    public static EditAssignmentFragment newInstance(String param1, String param2) {
        EditAssignmentFragment fragment = new EditAssignmentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        assignmentViewModel =
                new ViewModelProvider(this).get(AssignmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_edit_assignment, container, false);
        addEvents(root);
        accessToken = "Bearer "+ new UserInfo(root.getContext()).token();
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        if(bundle != null){
            //Lấy classId, className, moduleId, moduleName, trainerId
            assignmentId = bundle.getString("assignmentId");
            classId = bundle.getString("classId");
            className = bundle.getString("className");
            moduleId = bundle.getString("moduleId");
            moduleName = bundle.getString("moduleName");
            trainerId = bundle.getString("trainerId");
            trainerName = bundle.getString("trainerName");

            //Đổ dữ liệu
            txt_classId.setText(classId);
            txt_className.setText(className);
            txt_moduleId.setText(moduleId);
            txt_moduleName.setText(moduleName);
        }
        //Lấy ds Trainer
        LoadAllTrainer(root);
        return root;
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

    //Đổ ds trainer lên spinner
    private void setTrainerSpinner(View root) {
        ArrayAdapter<Trainee> adapter =
                new ArrayAdapter<Trainee>(root.getContext(),  android.R.layout.simple_spinner_dropdown_item, trainerList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner_Trainer.setAdapter(adapter);
        if(trainerName.equals("toan"))
            spinner_Trainer.setSelection(1);
    }

    private void addEvents(View root) {
        addControls(root);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","abc"); // Put anything what you want

                AddAssignmentFragment addAssignmentFragment = new AddAssignmentFragment();
                addAssignmentFragment.setArguments(bundle);

                AssignmentAPIServices.ASSIGNMENT_API_SERVICES
                        .editAssignment(accessToken, assignmentId, new EditAssignmentInfo(moduleId,classId,trainerId))
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
                                    ShowSuccessDialog(true);
                                else
                                    ShowSuccessDialog(false);
                                Toast toast = Toast.makeText(getContext(),  errorResponse.getMessage(),Toast.LENGTH_LONG);
                                toast.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        ShowSuccessDialog(false);
                    }
                    //Xử lí hiển thị ShowSuccessDialog
                    void ShowSuccessDialog(Boolean isSuccess){
                        //hiện dialog
                        LayoutInflater inflater = LayoutInflater.from(getContext());
                        View alertLayout = inflater.inflate(R.layout.success_dialog_layout, null);
                        TextView note = (TextView) alertLayout.findViewById(R.id.txt_SingleMessage);
                        note.setText("Edit Successfully!");
                        if(isSuccess == false){
                            alertLayout = inflater.inflate(R.layout.failure_dialog_layout, null);
                            note = (TextView) alertLayout.findViewById(R.id.txt_SingleErrorMessage);
                            note.setText("Assignment already exist!");
                        }
                        final Button btnYes = (Button) alertLayout.findViewById(R.id.btn_OK);
                        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                        alert.setView(alertLayout);
                        alert.setCancelable(false);
                        AlertDialog dialog = alert.create();
                        btnYes.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                dialog.dismiss();
                                if(isSuccess == true)
                                    Navigation.findNavController(root).navigate(R.id.edit_assignment_to_assignment, bundle);
                            }
                        });
                        dialog.show();
                    }
                });
            }
        });
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","abc"); // Put anything what you want

                AssignmentFragment assignmentFragment = new AssignmentFragment();
                assignmentFragment.setArguments(bundle);

                Navigation.findNavController(root).navigate(R.id.edit_assignment_to_assignment, bundle);
            }
        });
        //Events khi chọn item trên Spinner Trainer
        // When user select a List-Item.
        this.spinner_Trainer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandlerTrainer(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //trainee = trainerList.get
    }

    //Xử lí sự kiện chọn item Trainer
    private void onItemSelectedHandlerTrainer(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Trainee trainee = (Trainee) adapter.getItem(position);
        trainerId = trainee.get_id();
        Toast.makeText(view.getContext(), "Selected Trainer: " + trainee.getUserName() ,Toast.LENGTH_SHORT).show();
    }

    private void addControls(View root) {
        txt_classId = (TextView) root.findViewById(R.id.txt_classId);
        txt_className = (TextView) root.findViewById(R.id.txt_className);
        txt_moduleId = (TextView) root.findViewById(R.id.txt_moduleId);
        txt_moduleName = (TextView) root.findViewById(R.id.txt_moduleName);

        spinner_Trainer = (Spinner) root.findViewById(R.id.spinner_Trainer);
        btn_Save = (Button) root.findViewById(R.id.btn_Save);
        btn_Back = (Button) root.findViewById(R.id.btn_Back);
    }
}