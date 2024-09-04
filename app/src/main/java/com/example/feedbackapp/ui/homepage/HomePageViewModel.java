package com.example.feedbackapp.ui.homepage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomePageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomePageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}