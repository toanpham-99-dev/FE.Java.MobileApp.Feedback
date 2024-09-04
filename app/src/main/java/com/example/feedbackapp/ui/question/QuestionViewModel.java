package com.example.feedbackapp.ui.question;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuestionViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<String> mText;

    public QuestionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Question List");
    }

    public LiveData<String> getText() {
        return mText;
    }
}