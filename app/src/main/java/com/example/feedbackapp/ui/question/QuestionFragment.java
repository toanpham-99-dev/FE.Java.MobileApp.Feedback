package com.example.feedbackapp.ui.question;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.feedbackapp.Adapter.QuestionAdapter;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Question.QuestionInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Question.Question;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.ListTopic;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.Topic;
import com.example.feedbackapp.ModelClassToSendAPI.Question.LoadQuestionByTopicIdInfo;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.QuestionAPIServices;
import com.example.feedbackapp.RetrofitAPISetvice.TopicAPIServices;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionFragment extends Fragment {

    // TODO: Model variable
    private QuestionViewModel questionViewModel;

    // TODO: Control Variable
    FloatingActionButton btn_Add_Question;
    Spinner spinner_Topic;

    //TODO: AccessToken Variable
    String accessToken = "";

    //TODO: questionsList, topicList
    ArrayList<Question> questionsList;
    ArrayList<Topic> topicList;

    //TODO: Define RecyclerView and Adapter variable
    RecyclerView questionListRecycler;
    QuestionAdapter questionAdapter;

    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        questionViewModel =
                new ViewModelProvider(this).get(QuestionViewModel.class);
        View root = inflater.inflate(R.layout.question_fragment, container, false);
        addEvents(root);

        accessToken = "Bearer "+ new UserInfo(root.getContext()).token();

        //Lấy danh sách tất cả question
        LoadQuestionList(root);
        // Lấy danh sách topic đổ lên spinner
        LoadAllTopic(root);
        return root;
    }

    private void addEvents(View root) {
        addControls(root);
        btn_Add_Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("topicList",topicList);

                AddQuestionFragment addQuestionFragment = new AddQuestionFragment();
                addQuestionFragment.setArguments(bundle);

                Navigation.findNavController(root).navigate(R.id.question_to_add_question, bundle);
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
        LoadQuestionListByTopicId(view, topic.getId());
        Toast.makeText(view.getContext(), "Selected Topic: " + topic.getTopicName() ,Toast.LENGTH_SHORT).show();
    }

    private void addControls(View root) {
        btn_Add_Question = root.findViewById(R.id.btn_add_question);
        spinner_Topic = root.findViewById(R.id.spinner_Trainer);
        questionListRecycler = root.findViewById(R.id.questionList);
    }

    //Lấy danh sách tất cả question
    public void LoadQuestionList(View root){
        QuestionAPIServices.QUESTION_API_SERVICES.getQuestionList(accessToken).enqueue(new Callback<QuestionInfo>() {
            @Override
            public void onResponse(Call<QuestionInfo> call, Response<QuestionInfo> response) {
                questionsList = response.body().getListQuestion();
                QuestionAdapterFilter(root);
                Log.d("AAAAAAAAAAAAAAAA", "onResponse()");
                Toast.makeText(getActivity(),"Load thành công!",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<QuestionInfo> call, Throwable t) {
                Toast.makeText(getActivity(),"Có lỗi xảy ra!",Toast.LENGTH_LONG).show();
                Log.d("TAG", "onFailure()");
            }
        });
    }

    //Lấy danh sách tất cả question
    private void LoadAllTopic(View root) {
        TopicAPIServices.TOPIC_API_SERVICES.getTopicList(accessToken).enqueue(new Callback<ListTopic>() {
            @Override
            public void onResponse(Call<ListTopic> call, Response<ListTopic> response) {
                //moduleList = new ArrayList<Module>(Arrays.asList(response.body().getListModule()));
                topicList = response.body().getListTopic();
                setTopicSpinner(root);
            }
            @Override
            public void onFailure(Call<ListTopic> call, Throwable t) {
            }
        });
    }

    //Lấy danh sách tất cả question theo topicId
    private void LoadQuestionListByTopicId(View root, String topicId){
        QuestionAPIServices.QUESTION_API_SERVICES.getQuestionListByTopicId(accessToken, new LoadQuestionByTopicIdInfo(topicId)).enqueue(new Callback<QuestionInfo>() {
            @Override
            public void onResponse(Call<QuestionInfo> call, Response<QuestionInfo> response) {
                if(response.isSuccessful()){
                    questionsList = response.body().getListQuestion();
                    QuestionAdapterFilter(root);
                    Log.d("Lấy được danh sách Qestion theo Topic ID", topicId);
                    Toast.makeText(getActivity(),topicId,Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<QuestionInfo> call, Throwable t) {
                Toast.makeText(getActivity(),"Có lỗi xảy ra!",Toast.LENGTH_LONG).show();
                Log.d("TAG", "onFailure()");
            }
        });
    }
    //Đổ topic list vào spinner
    private void setTopicSpinner(View root){
        ArrayAdapter<Topic> adapter =
                new ArrayAdapter<Topic>(root.getContext(),  android.R.layout.simple_spinner_dropdown_item, topicList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner_Topic.setAdapter(adapter);
    }
    //Get question List for Adapter
    public void QuestionAdapterFilter(View root){
        questionAdapter = new QuestionAdapter(root.getContext(),questionsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        questionListRecycler.setLayoutManager(layoutManager);
        //questionListRecycler.setHasFixedSize(true);
        questionListRecycler.setAdapter(questionAdapter);
    }
}