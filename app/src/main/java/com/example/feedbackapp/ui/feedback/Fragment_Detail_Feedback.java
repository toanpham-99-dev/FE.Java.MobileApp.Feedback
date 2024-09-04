package com.example.feedbackapp.ui.feedback;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.feedbackapp.R;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.ui.feedback.Adapter.TopicDetailAdapter;
import com.example.feedbackapp.ui.feedback.Model.TopicModel;
import com.example.feedbackapp.ui.feedback.Service.APIService;
import com.example.feedbackapp.ui.feedback.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Detail_Feedback#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Detail_Feedback extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    UserInfo userInfo = new UserInfo(getContext());

    public Fragment_Detail_Feedback() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Detail_Feedback.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Detail_Feedback newInstance(String param1, String param2) {
        Fragment_Detail_Feedback fragment = new Fragment_Detail_Feedback();
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
        View view = inflater.inflate(R.layout.fragment__detail__feedback, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rcv_detail_edit);
        DataService dataServiceTopic = APIService.getService();
        Call<TopicModel> callbackListTopic = dataServiceTopic.GetDataTopic("Bearer "+userInfo.token());
        callbackListTopic.enqueue(new Callback<TopicModel>() {
            @Override
            public void onResponse(Call<TopicModel> call, Response<TopicModel> response) {
                TopicModel topicModel = (TopicModel)response.body();
                TopicDetailAdapter topicAdapter = new TopicDetailAdapter(topicModel.getTopic());

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
        return view;
    }

}