package com.example.feedbackapp.ui.feedback;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.constant.SystemConstant;
import com.example.feedbackapp.ui.feedback.Adapter.CustomAdapter;
import com.example.feedbackapp.ui.feedback.Adapter.QuestionAdapter;
import com.example.feedbackapp.ui.feedback.Adapter.TopicAdapter;
import com.example.feedbackapp.ui.feedback.Adapter.TopicEditAdapter;
import com.example.feedbackapp.ui.feedback.Interface.ICheckBoxListener;
import com.example.feedbackapp.ui.feedback.Model.FeedbackEditFeedbackList2;
import com.example.feedbackapp.ui.feedback.Model.FeedbackEditFilterId1;
import com.example.feedbackapp.ui.feedback.Model.FeedbackEditTopic3;
import com.example.feedbackapp.ui.feedback.Model.ListTypeFeedbackModel;
import com.example.feedbackapp.ui.feedback.Model.Question;
import com.example.feedbackapp.ui.feedback.Model.TopicModel;
import com.example.feedbackapp.ui.feedback.Model.TypeFeedbackModel;
import com.example.feedbackapp.ui.feedback.Model.TypeOfFeedbackModel;
import com.example.feedbackapp.ui.feedback.Service.APIService;
import com.example.feedbackapp.ui.feedback.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Edit_Feedback extends Fragment {
    private Button btnReviewFeedback;
    private String typeFeedback;
    private EditText feedbackName;
    private ImageView btn_Edit;
    private Button btn_BackFeedbackCreate;
    private String feedbackId="";

    //
    String feedbackTypeId;
    List<Question> questionList = new ArrayList<>();


    //private Spinner spinnerTypeFeedback;
    //private List<TypeOfFeedbackModel>typeOfFeedbackModels;
    private Spinner spinner;
    private List<TypeFeedbackModel> classes;

    // adt
    private TopicEditAdapter topicEditAdapter;
    private QuestionAdapter questionAdapter =  new QuestionAdapter();
    private TopicModel topicModel;
    private ICheckBoxListener iCheckBoxListener;
    private String stateFeedback;
    private ListTypeFeedbackModel listTypeFeedbackModel;
    public Fragment_Edit_Feedback() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__edit__feedback, container, false);
        DataService dataServiceFeedback = APIService.getService();
        spinner = (Spinner) view.findViewById(R.id.spn_Type_Feedback_edit);
        UserInfo userInfo = new UserInfo(getContext());
        Call<TypeOfFeedbackModel> callbackFeedback = dataServiceFeedback.GetDataTypeFeedback("Bearer "+userInfo.token());
        callbackFeedback.enqueue(new Callback<TypeOfFeedbackModel>()
        {
            @Override
            public void onResponse(Call<TypeOfFeedbackModel> call, Response<TypeOfFeedbackModel> response) {
                TypeOfFeedbackModel listTypeFeedbackModel = (TypeOfFeedbackModel) response.body();
                // Adapter"
                CustomAdapter adapter = new CustomAdapter(getActivity(),
                        R.layout.spinner_item_layout_type_feedback,
                        R.id.textView_item_name,
                        listTypeFeedbackModel.getListTypeFeedback());
                spinner.setAdapter(adapter);
                spinner.setSelected(true);

                Log.d("BBBB",listTypeFeedbackModel.getMessage());

            }

            @Override
            public void onFailure(Call<TypeOfFeedbackModel> call, Throwable t) {
                Log.d("DDD","Get Data Fail");
            }
        });
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent,view,position,id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        UserInfo userInfo1 = new UserInfo(getContext());
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rcv_Topic_Edit);
        DataService dataServiceTopic = APIService.getService();

        Call<TopicModel> callbackListTopic = dataServiceTopic.GetDataTopic("Bearer "+userInfo1.token());
        callbackListTopic.enqueue(new Callback<TopicModel>() {
            @Override
            public void onResponse(Call<TopicModel> call, Response<TopicModel> response) {
                topicModel = (TopicModel)response.body();
                topicEditAdapter = new TopicEditAdapter(topicModel.getTopic());

                recyclerView.setAdapter(topicEditAdapter);
                RecyclerView.LayoutManager layoutManagerTopic = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManagerTopic);
                Log.d("BTopic","Done");
            }

            @Override
            public void onFailure(Call<TopicModel> call, Throwable t) {
                Log.d("DTopic",t.toString());
            }
        });
        //Xử lý Review
        btnReviewFeedback =(Button)view.findViewById(R.id.btn_ReviewEditFeedback);
        feedbackName =(EditText)view.findViewById(R.id.edt_FeedbackTitleEdit);
        btn_BackFeedbackCreate =(Button)view.findViewById(R.id.btn_BackFeedbackEdit);
        Bundle bundlefeedback=getArguments();
        feedbackId = bundlefeedback.getString("feedbackId");
        feedbackName.setText(bundlefeedback.getString("feedbackTitle"));
        btnReviewFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //arr id question choose
                //questionAdapter = topicEditAdapter.GetQuestionAdapter();
                //List<Question> arrQuestion = topicAdapter.topic.getListQuestion();
                Log.i("QUESTION ADAPTER REPONSE", "123" + questionAdapter.onclicked().toString());
                questionList = questionAdapter.onclicked();
                Log.i("CHECK SPINNER",spinner.getSelectedItem().toString());
                Bundle bundle = new Bundle();
                bundle.putString("feedbackID",feedbackId);
                bundle.putString("typeFeedbackId",feedbackTypeId);
                bundle.putString("feedbackName", feedbackName.getText().toString().trim());
                bundle.putString("AdminId","60a8f233a86b7c42384e8bf9");
                if(feedbackName.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(),"You must fill feedback name",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(SystemConstant.save_state_edit.isEmpty())
                {
                    Toast.makeText(getContext(),"You must check every topic 1 question",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(SystemConstant.save_state_edit.size()<4)
                {
                    Toast.makeText(getContext(),"You must check every topic 1 question",Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_review_edit_feedback,bundle);
                }
            }
        });
        btn_BackFeedbackCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_feedback);
            }
        });


        return view;
    }

    public List<Question>GetListQuestion()
    {
        return this.questionList;
    }
    // handler click spinner
    // Class
    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        ListTypeFeedbackModel state = (ListTypeFeedbackModel) adapter.getItem(position);
        feedbackTypeId= state.getId();

    }
}
