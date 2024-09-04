package com.example.feedbackapp.ui.feedback.Model;

import java.util.ArrayList;
import java.util.List;

public class ClassDataUtilsFeedback {

    public static List<TypeFeedbackModel> getClasss( ) {
        TypeFeedbackModel class1 = new TypeFeedbackModel(1, "Online");
        TypeFeedbackModel class2 = new TypeFeedbackModel(2, "Offline");


        List<TypeFeedbackModel> list = new ArrayList<TypeFeedbackModel>();
        list.add(class1);
        list.add(class2);

        return list;
    }
}
