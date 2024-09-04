package com.example.feedbackapp.ui.statisticfeedback;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.feedbackapp.Adapter.CustomAdapter;
import com.example.feedbackapp.Adapter.CustomApdapterModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Answer.ListAnswerInfor;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassList;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.ModelClassToSendAPI.Answer.Answer;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.AnswerService;
import com.example.feedbackapp.model.Class;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//chart

public class FeedbackRightFragment extends Fragment {
    //
    // Viewmodel
    private StatisticFeedBackViewModel mviewModel;
    private CustomAdapter adapter;
    private CustomApdapterModule adapterModule;
    private String accessToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRiYTk1N2FhNjBjN2M3YzNlYTEiLCJ0eXBlVXNlciI6ImFkbWluIiwiaWF0IjoxNjIxODU5NDMwfQ.-GljSrlUF4b3nl8ojzpk1xK1O-_MX5B6a31g8u5eTp8";
    //swipe left
    float x1, x2, y1 , y2;
    //chart
    PieChart pieChart, pieChart1, pieChart2, pieChart3 ;
    //declare for spinner
    private Spinner spinner;// for clss
    private Spinner spinnerModule;
    private List<Classs> classes;
    private List<com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module> modules;
    private StatisticFeedBackViewModel mViewModel;
    private Button showDetail;
    //to send data
// get positon item of spinner
    private Classs classChoosed;
    private Module moduleChoosed;
    private ArrayList<Answer> answerArrayList;

    public static FeedbackRightFragment newInstance() {
        return new FeedbackRightFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // adapter = new CustomAdapter();

        mviewModel = ViewModelProviders.of(this).get(StatisticFeedBackViewModel.class);
        mviewModel.init();
        mviewModel.getClassListLiveData().observe(this, new Observer<ClassList>() {
            @Override
            public void onChanged(ClassList classList) {
                //link: https://viblo.asia/p/android-architecture-components-viewmodel-xu-ly-configuration-changes-chua-bao-gio-don-gian-den-the-ByEZk3A4ZQ0
                //if (classList != null) {

                adapter = new CustomAdapter(getActivity(),
                        R.layout.spinner_item_layout,
                        R.id.textView_item_name,
                        classList.getListClass());
                //spinner = (Spinner) v.findViewById(R.id.spinner_class);
                spinner.setAdapter(adapter);
                spinner.setSelection(getArguments().getInt("class", 0));
                // }

            }
        });
        //Module
        mviewModel.getModuleListLiveData().observe(this, new Observer<ListModule>() {
            @Override
            public void onChanged(ListModule listModule) {
                //link: https://viblo.asia/p/android-architecture-components-viewmodel-xu-ly-configuration-changes-chua-bao-gio-don-gian-den-the-ByEZk3A4ZQ0
                //if (classList != null) {

                adapterModule = new CustomApdapterModule(getActivity(),
                        R.layout.spinner_item_layout,
                        R.id.textView_item_name,
                        listModule.getListModule());
                //spinner = (Spinner) v.findViewById(R.id.spinner_class);
                spinnerModule.setAdapter(adapterModule);
                spinnerModule.setSelection(getArguments().getInt("module", 0));

                // }

            }
        });
        mviewModel.getClassPosition().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {

                spinner.setSelection(integer);
            }
        });
        mviewModel.getModulePosition().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                spinnerModule.setSelection(integer);
            }
        });
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.feedback_right_fragment, container, false);
        // Ra ho
        // Code for spinner class
        // Data:

        this.spinner = (Spinner) v.findViewById(R.id.txt_className);
        mviewModel.getClas(accessToken);
        this.spinnerModule = (Spinner) v.findViewById(R.id.spinner_module);
        mviewModel.getModule(accessToken);
        // get data from feedbackfragment

        this.spinner.setSelection(getArguments().getInt("class", 0));
        this.spinnerModule.setSelection(getArguments().getInt("module", 0));
// Click event for button View Detail
        showDetail = (Button) v.findViewById(R.id.show_detail);
        this.showDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("class", spinner.getSelectedItemPosition());
                bundle.putInt("module",spinnerModule.getSelectedItemPosition());
                //Navigation.findNavController(v).navigate(R.id.action_feedbackright_to_feedbackdetail, bundle);
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_feedbackdetail, bundle);
            }
        });

        // When user select a List-Item on spinner Class
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //spinner.setSelection(o_onchange);
        // When user select a List-Item on spinner Module
        this.spinnerModule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandlerModule(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //Chart using MPAndroidChart
        //pieChart
        pieChart = (PieChart)v.findViewById(R.id.PieChart);
        String titlepieChart = "";
        //pieChart.Titles.add
        pieChart.setDescription(titlepieChart);
        pieChart.setRotationEnabled(false);  //cho phép xoay
        pieChart.setHoleRadius(0f);         //tên của chart, được viết trong 1 vòng tròn ở giữa chart với bán kính này
        pieChart.setTransparentCircleAlpha(0);  // vòng tròng trong suốt, chắc để tạo thêm hiệu ứng cho đẹp?
        pieChart.setDrawEntryLabels(false);// hide lable
        pieChart.getLegend().setEnabled(false);

        //addDataSet();
        //pieChart2
        pieChart2 = (PieChart)v.findViewById(R.id.PieChart2);
        pieChart2.setDescription(titlepieChart);
        pieChart2.setRotationEnabled(false);
        pieChart2.setHoleRadius(0f);
        pieChart2.setTransparentCircleAlpha(0);
        pieChart2.setDrawEntryLabels(false);
        pieChart2.getLegend().setEnabled(false);
        //pieChart3
        pieChart3 = (PieChart)v.findViewById(R.id.PieChart3);
        pieChart3.setDescription(titlepieChart);
        pieChart3.setRotationEnabled(false);
        pieChart3.setHoleRadius(0f);
        pieChart3.setTransparentCircleAlpha(0);
        pieChart3.setDrawEntryLabels(false);
        pieChart3.getLegend().setEnabled(false);

        //pieChart4
        pieChart1 = (PieChart)v.findViewById(R.id.PieChart1);
        pieChart1.setDescription(titlepieChart);
        pieChart1.setRotationEnabled(false);
        pieChart1.setHoleRadius(0f);
        pieChart1.setTransparentCircleAlpha(0);
        pieChart1.setDrawEntryLabels(false);
        pieChart1.getLegend().setEnabled(false);
       //set data
       //addDataSet();
        //Swipe right
        //onTouch1(v,MotionEvent );
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x1 = event.getX();
                    y1 = event.getY();
                }
                //return true;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    x2 = event.getX();
                    y2 = event.getY();
                    if(x1 < x2)
                    {  Bundle bundle = new Bundle();
                        bundle.putInt("class", spinner.getSelectedItemPosition());
                        bundle.putInt("module",spinnerModule.getSelectedItemPosition());
                        //Navigation.findNavController(v).navigate(R.id.action_fragment2_to_fragment1, bundle);
                        NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_statisticdofeedback, bundle);
                    }

                }
                return true;
            }
        });
        // return view
        return v;
    }
    // handler click spinner
    // Class
    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Classs clas = (Classs) adapter.getItem(position);
        String itemName = clas.getClassName();
        classChoosed =(Classs) adapter.getItem(position);
        addDataSet();
    }

    // module
    private void onItemSelectedHandlerModule(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Module module = (Module) adapter.getItem(position);
        String itemModule =module.getModuleName();
        moduleChoosed = (Module) adapter.getItem(position);
        addDataSet();
    }


    //Set data for chart
    private void addDataSet() {
        ArrayList<String> status = new ArrayList<>();
        status.add("Strongly Disagree");
        status.add("Disgree");
        status.add("Neutral");
        status.add("Agree");
        status.add("Strong Agree");
        AnswerService.answerService.getAnswer(
                "Bear eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRiYTk1N2FhNjBjN2M3YzNlYTEiLCJ0eXBlVXNlciI6ImFkbWluIiwiaWF0IjoxNjIxODU5NDMwfQ.-GljSrlUF4b3nl8ojzpk1xK1O-_MX5B6a31g8u5eTp8"

        ).enqueue(new Callback<ListAnswerInfor>() {
            @Override
            public void onResponse(Call<ListAnswerInfor> call, Response<ListAnswerInfor> response) {

                ListAnswerInfor listAnswerInfor = response.body();
                if(listAnswerInfor.isSuccess()){
                    answerArrayList = listAnswerInfor.getListAnswer();
                    pieChart.setData(createPieData(answerArrayList, status,"Training program & content (nội dung đào tạo)"));
                    pieChart.invalidate();
                    //pieChart1
                    pieChart2.setData(createPieData(answerArrayList, status,"Trainer/Coach"));
                    pieChart2.invalidate();
                    //pieChart2
                    pieChart1.setData(createPieData(answerArrayList, status, "Course Organization (tổ chức khóa học)" ));
                    pieChart1.invalidate();
                    //pieChart4
                    pieChart3.setData(createPieData(answerArrayList, status, "Other"));
                    pieChart3.invalidate();
                }


            }
            @Override
            public void onFailure(Call<ListAnswerInfor> call, Throwable t) {
                Log.d("TAG", "Fail");
            }
        });

    }
    public PieData createPieData(ArrayList<Answer> answerArrayList, ArrayList<String> status, String topicName){
        int indexCount0 =0,indexCount1 =0, indexCount2 =0, indexCount3 =0, indexCount4 =0;
        for(int i = 0; i< answerArrayList.size(); i++){
            // add condition getNameToppic =
            if((answerArrayList.get(i).getClassId().equals(classChoosed.getId())) && (answerArrayList.get(i).getModuleId().equals( moduleChoosed.getId()))&&(answerArrayList.get(i).getTopicName().equals(topicName))){
                switch (answerArrayList.get(i).getValue()){
                    case 0:indexCount0++;break;
                    case 1:indexCount1++;break;
                    case 2:indexCount2++;break;
                    case 3: indexCount3++;break;
                    default:indexCount4++;break;
                }
            }
        }
        ArrayList<Integer> count = new ArrayList<>();
        count.add(indexCount0);
        count.add(indexCount1);
        count.add(indexCount2);
        count.add(indexCount3);
        count.add(indexCount4);
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        Integer sum = indexCount0 + indexCount1 + indexCount2 + indexCount3 +indexCount4;


        for(int i=0;i<status.size();i++){
            yEntrys.add(new PieEntry( (float)count.get(i)/sum*100,status.get(i)));
        }
        PieDataSet pieDataSet = new PieDataSet(yEntrys,"");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(5);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#f2afa9"));
        colors.add(Color.parseColor("#f27c71"));
        colors.add(Color.parseColor("#FF6600"));
        colors.add(Color.parseColor("#F75536"));
        colors.add(Color.parseColor("#FC2E05"));
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextColor(Color.WHITE);
        pieData.setValueTextSize(13);
        return pieData;
    }

}