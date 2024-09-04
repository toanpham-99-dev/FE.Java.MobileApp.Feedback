package com.example.feedbackapp.ui.question;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.ErrorResponse;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.Topic;
import com.example.feedbackapp.ModelClassToSendAPI.Question.AddQuestionInfo;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.QuestionAPIServices;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuestionFragment extends Fragment {

    private QuestionViewModel questionViewModel;

    // TODO: Control Varible
    Spinner spinner_Topic;
    EditText editText_questionContent;
    TextView textView_note;
    Button btn_Save, btn_Back;

    //TODO: topicList
    ArrayList<Topic> topicList;

    //todo: accessToken, bien dau vao
    String accessToken, topicId, questionContent;

    public AddQuestionFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddQuestionFragment newInstance(String param1, String param2) {
        AddQuestionFragment fragment = new AddQuestionFragment();
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
        questionViewModel =
                new ViewModelProvider(this).get(QuestionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_question, container, false);
        addEvents(root);
        accessToken = "Bearer "+ new UserInfo(root.getContext()).token();
        // Lấy listTopic từ QuestionFragment
        Bundle bundle = getArguments();
        if(bundle != null){
            // handle your code here.
            topicList = (ArrayList<Topic>) bundle.getSerializable("topicList");
        }

        // Lấy danh sách topic đổ lên spinner
        setTopicSpinner(root);
        return root;
    }

    //Đổ topic list vào spinner
    private void setTopicSpinner(View root){
        ArrayAdapter<Topic> adapter =
                new ArrayAdapter<Topic>(root.getContext(),  android.R.layout.simple_spinner_dropdown_item, topicList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner_Topic.setAdapter(adapter);
    }

    private void addEvents(View root) {
        addControls(root);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                AddQuestionFragment addQuestionFragment = new AddQuestionFragment();
                addQuestionFragment.setArguments(bundle);

                //Lấy dl đầu vào và xác thực
                questionContent = editText_questionContent.getText().toString();
                if(questionContent.equals(""))
                    textView_note.setText("Please enter the question");
                else{
                    textView_note.setText("");
                    //Gọi API thêm Question
                    QuestionAPIServices.QUESTION_API_SERVICES.addNewQuestion(accessToken,new AddQuestionInfo(questionContent,topicId))
                            .enqueue(new Callback<ResponseBody>() {
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
                                    Log.e("TAG", "onFailure: ", t);
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

                AddQuestionFragment addQuestionFragment = new AddQuestionFragment();
                addQuestionFragment.setArguments(bundle);

                Navigation.findNavController(root).navigate(R.id.add_question_to_question, bundle);
            }
        });

        //Events khi chọn item trên Spinner
        // When user select a List-Item.
        this.spinner_Topic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Topic topic = (Topic) adapter.getItem(position);
        topicId = topic.getId();
        Toast.makeText(view.getContext(), "Selected Topic: " + topic.getTopicName() ,Toast.LENGTH_SHORT).show();
    }

    private void addControls(View root) {
        editText_questionContent = root.findViewById(R.id.editText_questionContent);
        textView_note = root.findViewById(R.id.textView_note);
        spinner_Topic = root.findViewById(R.id.spinner_Trainer);
        btn_Save = (Button) root.findViewById(R.id.btn_Save);
        btn_Back = (Button) root.findViewById(R.id.btn_Back);
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
                    Navigation.findNavController(root).navigate(R.id.add_question_to_question, bundle);
            }
        });
        dialog.show();
    }
}