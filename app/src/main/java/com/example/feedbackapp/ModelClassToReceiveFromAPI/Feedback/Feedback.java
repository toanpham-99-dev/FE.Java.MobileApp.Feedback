package com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback;

import java.util.ArrayList;

public class Feedback {
    private String Id = "";
    private String Title = "";
    private String AdminId = "";
    private String AdminName = "";
    private String TypeFeedbackId = "";
    private String TypeFeedbackName = "";
    private String ClassId = "";
    private String ClassName = "";
    private String ModuleId = "";
    private String ModuleName = "";
    private String EndTime = "";
    private boolean isCompleted = true;
    private ArrayList<Question> listQuestion = new ArrayList<Question>();


    // GET SET
    public String getId() {return this.Id;}
    public String getTitle() {return this.Title;}
    public String getAdminId() {return this.AdminId;}
    public String getAdminName() {return this.AdminName;}
    public String getTypeFeedbackId() {return this.TypeFeedbackId;}
    public String getTypeFeedbackName() {return this.TypeFeedbackName;}
    public String getClassId() {return this.ClassId;}
    public String getClassName() {return this.ClassName;}
    public String getModuleId() {return this.ModuleId;}
    public String getModuleName() {return this.ModuleName;}
    public String getEndTime() {return this.EndTime;}
    public boolean getIsCompleted() {return this.isCompleted;}
    public ArrayList<Question> getListQuestion() {return this.listQuestion;}

    public void setId(String Id){this.Id = Id;}
    public void setTitle(String Title){this.Title = Title;}
    public void setAdminId(String AdminId){this.AdminId = AdminId;}
    public void setAdminName(String AdminName){this.AdminName = AdminName;}
    public void setTypeFeedbackId(String TypeFeedbackId){this.TypeFeedbackId = TypeFeedbackId;}
    public void setTypeFeedbackName(String TypeFeedbackName){this.TypeFeedbackName = TypeFeedbackName;}
    public void setClassId(String ClassId){this.ClassId = ClassId;}
    public void setClassName(String ClassName){this.ClassName = ClassName;}
    public void setModuleId(String ModuleId){this.ModuleId = ModuleId;}
    public void setModuleName(String ModuleName){this.ModuleName = ModuleName;}
    public void setEndTime(String EndTime){this.EndTime = EndTime;}


}
