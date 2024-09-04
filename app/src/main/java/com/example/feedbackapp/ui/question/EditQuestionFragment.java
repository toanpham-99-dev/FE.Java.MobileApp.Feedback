package com.example.feedbackapp.ui.question;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.ErrorResponse;
import com.example.feedbackapp.ModelClassToSendAPI.Question.AddQuestionInfo;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.QuestionAPIServices;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.model.Trainee;
import com.example.feedbackapp.ui.assignment.AssignmentFragment;
import com.example.feedbackapp.ui.assignment.AssignmentViewModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditQuestionFragment extends Fragment {

    private QuestionViewModel questionViewModel;

    // TODO: Control Varible
    TextView txt_topicName, editText_questionContent, textView_note;
    Button btn_Save, btn_Back;

    //TODO: AccessToken Varible
    String accessToken = "";

    //TODO: Biến thông tin Question
    String questionId, topicId, topicName, questionContent;

    public EditQuestionFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EditQuestionFragment newInstance(String param1, String param2) {
        EditQuestionFragment fragment = new EditQuestionFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        questionViewModel =
                new ViewModelProvider(this).get(QuestionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_edit_question, container, false);
        addEvents(root);
        accessToken = "Bearer "+ new UserInfo(root.getContext()).token();
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        if(bundle != null){
            //Lấy questionId, topicId, topicName, questionContent
            questionId = bundle.getString("questionId");
            topicId = bundle.getString("topicId");
            topicName = bundle.getString("topicName");
            questionContent = bundle.getString("questionContent");

            //Đổ dữ liệu
            txt_topicName.setText(topicName);
            editText_questionContent.setText(questionContent);
        }
        return root;
    }

    private void addEvents(View root) {
        addControls(root);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","abc"); // Put anything what you want

                QuestionFragment questionFragment = new QuestionFragment();
                questionFragment.setArguments(bundle);

                //Lấy dl đầu vào và xác thực
                questionContent = editText_questionContent.getText().toString();
                if(questionContent.equals(""))
                    textView_note.setText("Please enter the question");
                else {
                    textView_note.setText("");
                    QuestionAPIServices.QUESTION_API_SERVICES
                            .editQuestion(accessToken,questionId,new AddQuestionInfo(questionContent,topicId))
                            .enqueue(new Callback<ResponseBody>(){
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if(response.body() != null){
                                        Gson gson = new Gson();
                                        try {
                                            ErrorResponse errorResponse = gson.fromJson(
                                                    response.body().string(),
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
                                }
                            });
                }
            }
        });
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","abc"); // Put anything what you want

                QuestionFragment questionFragment = new QuestionFragment();
                questionFragment.setArguments(bundle);

                Navigation.findNavController(root).navigate(R.id.edit_question_to_question, bundle);
            }
        });
    }

    private void addControls(View root) {
        txt_topicName = (TextView) root.findViewById(R.id.txt_topicName);
        textView_note = (TextView) root.findViewById(R.id.textView_note);
        editText_questionContent = (EditText) root.findViewById(R.id.editText_questionContent);

        btn_Save = (Button) root.findViewById(R.id.btn_Save);
        btn_Back = (Button) root.findViewById(R.id.btn_Back);
    }

    //Xử lí hiển thị Dialog
    void ShowSuccessDialog(View root, Bundle bundle){
        //hiện dialog
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.success_dialog_layout, null);
        TextView note = (TextView) alertLayout.findViewById(R.id.txt_SingleMessage);
        note.setText("Edit Successfully!");
        if(bundle == null){
            alertLayout = inflater.inflate(R.layout.failure_dialog_layout, null);
            note = (TextView) alertLayout.findViewById(R.id.txt_SingleErrorMessage);
            note.setText("Question already exist!");
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
                    Navigation.findNavController(root).navigate(R.id.edit_question_to_question, bundle);
            }
        });
        dialog.show();
    }
}