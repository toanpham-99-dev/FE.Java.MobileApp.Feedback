package com.example.feedbackapp.ui.feedback;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedbackapp.R;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.constant.SystemConstant;
import com.example.feedbackapp.ui.feedback.Adapter.TopicReviewAdapter;
import com.example.feedbackapp.ui.feedback.Adapter.TopicReviewEditAdapter;
import com.example.feedbackapp.ui.feedback.Model.AddFeedback;
import com.example.feedbackapp.ui.feedback.Model.Question;
import com.example.feedbackapp.ui.feedback.Model.TopicModel;
import com.example.feedbackapp.ui.feedback.Service.APIService;
import com.example.feedbackapp.ui.feedback.Service.DataService;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Review_Edit_Feedback#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Review_Edit_Feedback extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button btnReview;
    ArrayList<Question> listQuestions;
    private String idTypeFeedback;
    private String edt_feedbacktitle="";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btn_Save_Review;
    private Button btn_back_review;
    private String feedbackId;
    ArrayList<Question>questions=new ArrayList<>();


    public Review_Edit_Feedback() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Review_NewFeedbackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Review_NewFeedbackFragment newInstance(String param1, String param2) {
        Review_NewFeedbackFragment fragment = new Review_NewFeedbackFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review__edit__feedback, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rcv_detail_edit);
        DataService dataServiceTopic = APIService.getService();
        UserInfo userInfo = new UserInfo(getContext());
        Call<TopicModel> callbackListTopic = dataServiceTopic.GetDataTopic("Bearer "+userInfo.token());
        callbackListTopic.enqueue(new Callback<TopicModel>() {
            @Override
            public void onResponse(Call<TopicModel> call, Response<TopicModel> response) {
                TopicModel topicModel = (TopicModel)response.body();
                TopicReviewEditAdapter topicAdapter = new TopicReviewEditAdapter(topicModel.getTopic());

                recyclerView.setAdapter(topicAdapter);
                RecyclerView.LayoutManager layoutManagerTopic = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManagerTopic);
                Log.d("BTopic","Done");
            }

            @Override
            public void onFailure(Call<TopicModel> call, Throwable t) {
                Log.d("DTopic",t.toString());
            }
        });
        Bundle bundle = getArguments();
        TextView txt_adminId;
        btn_Save_Review =(Button)view.findViewById(R.id.btn_Save_Review_edit);
        TextView txt_feedbacktitle =(TextView)view.findViewById(R.id.txt_Edit_feedbacktitle);
        txt_adminId =(TextView)view.findViewById(R.id.txt_edit_adminid);
        if(bundle !=null) {
            feedbackId=bundle.getString("feedbackID");
            edt_feedbacktitle = bundle.getString("feedbackName");
            txt_adminId.setText(bundle.getString("AdminId"));
            idTypeFeedback = bundle.getString("typeFeedbackId");
            txt_feedbacktitle.setText(edt_feedbacktitle);
        }
        else
            Toast.makeText(this.getContext(),"Lỗi",Toast.LENGTH_LONG).show();

        btn_Save_Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),SystemConstant.id_question.toString(),Toast.LENGTH_LONG);
                DataService dataService = APIService.getService();
                UserInfo userInfo = new UserInfo(getContext());
                dataService.PutDataFeedback("Bearer "+userInfo.token(),new AddFeedback(edt_feedbacktitle.trim(),
                        idTypeFeedback,SystemConstant.save_state_edit),feedbackId).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getContext(),"Edit was successfull",Toast.LENGTH_LONG).show();
                        Navigation.findNavController(view).navigate(R.id.nav_feedback);
                        Log.i("PUT","PUT OK");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("PUT","PUT NOT OK");
                    }
                });


            }
        });
        btn_back_review = (Button)view.findViewById(R.id.btn_back_review_edit);
        btn_back_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.nav_feedback);
            }
        });
        return view;
    }
}