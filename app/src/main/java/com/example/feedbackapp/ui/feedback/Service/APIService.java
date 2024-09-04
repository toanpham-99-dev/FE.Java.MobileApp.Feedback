package com.example.feedbackapp.ui.feedback.Service;

public class APIService {
    private static String base_url = "https://androidserverr2s.herokuapp.com/";
    public static DataService getService()
    {
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
