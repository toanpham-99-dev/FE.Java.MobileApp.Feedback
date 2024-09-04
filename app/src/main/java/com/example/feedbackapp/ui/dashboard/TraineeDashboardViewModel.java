package com.example.feedbackapp.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TraineeDashboardViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public TraineeDashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
