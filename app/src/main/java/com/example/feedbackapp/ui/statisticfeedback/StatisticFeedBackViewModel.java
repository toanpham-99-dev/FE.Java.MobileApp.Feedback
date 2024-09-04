package com.example.feedbackapp.ui.statisticfeedback;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassList;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.ListTopic;
import com.example.feedbackapp.repositories.ClassRepository;
import com.example.feedbackapp.repositories.ModuleRepository;
import com.example.feedbackapp.repositories.TopicRepository;
//import androidx.lifecycle.ViewModel;

public class StatisticFeedBackViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
   /* private MutableLiveData<Integer> integer = new MutableLiveData<>();

    public StatisticFeedackViewModel(@NonNull Application application) {
        super(application);
    }

    public void setInt(Integer input){
        integer.setValue(input);
    }
    public LiveData<Integer> getInt(){
        return integer;
    }
}*/
    //Class
    private ClassRepository classRepository;
    private LiveData<ClassList> classListLiveData;
    private MutableLiveData<Integer> classPosition = new MutableLiveData<>();
    //Module
    private ModuleRepository moduleRepository;
    private LiveData<ListModule> moduleListLiveData;
    private MutableLiveData<Integer> modulePosition  = new MutableLiveData<>();
    //Topic
    private TopicRepository topicRepository;
    private LiveData<ListTopic> topicListLiveData;

    public StatisticFeedBackViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        //class
        classRepository = new ClassRepository();
        classListLiveData = classRepository.getClassListLiveData();

        //module
        moduleRepository = new ModuleRepository();
        moduleListLiveData = moduleRepository.getModuleListLiveData();

        //topic
        topicRepository = new TopicRepository();
        topicListLiveData = topicRepository.getTopicListLiveData();
    }
//Get from service
    //Class
    public void getClas(String accessToken) {
        //Dotenv dotenv = Dotenv.configure().directory("/assets").filename("env").load();
        classRepository.getClas(accessToken);
    }
    //module
    public void getTopic(String accessToken) {
        //Dotenv dotenv = Dotenv.configure().directory("/assets").filename("env").load();
        topicRepository.getTopic(accessToken);
    }
    //topic
    public void getModule(String accessToken) {
        //Dotenv dotenv = Dotenv.configure().directory("/assets").filename("env").load();
        moduleRepository.getModule(accessToken);
    }
//Get
    //class
    public LiveData<ClassList> getClassListLiveData() {
        return classListLiveData;
    }
    public void setClassPosition(Integer input) {
        classPosition.setValue(input);
    }
    public LiveData<Integer> getClassPosition() {
        return classPosition;
    }
    //module
    public LiveData<ListModule> getModuleListLiveData() {
        return moduleListLiveData;
    }
    public void setModulePosition(Integer input) {
        modulePosition.setValue(input);
    }
    public LiveData<Integer> getModulePosition() {
        return modulePosition;
    }
    //Topic
    public LiveData<ListTopic> getTopicListLiveData() {
        return topicListLiveData;
    }
}