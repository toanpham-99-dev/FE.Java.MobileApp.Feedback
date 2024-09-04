package com.example.feedbackapp.ui.feedback.Service;

import com.example.feedbackapp.ui.feedback.Model.AddFeedback;
import com.example.feedbackapp.ui.feedback.Model.FeedbackEditFilterId1;
import com.example.feedbackapp.ui.feedback.Model.ListFeedbackModel;
import com.example.feedbackapp.ui.feedback.Model.ListTypeFeedbackModel;
import com.example.feedbackapp.ui.feedback.Model.TopicModel;
import com.example.feedbackapp.ui.feedback.Model.TypeOfFeedbackModel;
import com.google.ads.mediation.MediationServerParameters;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataService {
    @GET("api/feedback")
    Call<ListFeedbackModel>GetDataListFeedback(@Header("Authorization") String token);

    @GET("api/type-feedback")
    Call<TypeOfFeedbackModel>GetDataTypeFeedback(@Header("Authorization") String token);

    @GET("api/topic")
    Call<TopicModel>GetDataTopic(@Header("Authorization") String token);

    @POST("api/feedback")
    Call<ResponseBody>PostData(@Header("Authorization") String token, @Body AddFeedback addFeedback);

    @GET("api/feedback/{id}")
    Call<FeedbackEditFilterId1>GetDataFilterIdFeedback(@Header("Authorization") String token, @Path("id") String id);

    @PUT("api/feedback/{id}")
    Call<ResponseBody>PutDataFeedback(@Header("Authorization") String token, @Body AddFeedback addFeedback,@Path("id") String id);

    //service xóa 1 assignment theo id
    @DELETE("api/feedback/{id}")
    Call<ResponseBody> DeleteFeedback(@Header("Authorization") String token, @Path("id") String id);
}
