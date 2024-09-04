package com.example.feedbackapp.ModelClassToSendAPI.Assignment;

public class EditAssignmentInfo {
    private String ModuleId;
    private String ClassId;
    private String TrainerId;

    public EditAssignmentInfo(String moduleId, String classId, String trainerId) {
        ModuleId = moduleId;
        ClassId = classId;
        TrainerId = trainerId;
    }

    public String getModuleId() {
        return ModuleId;
    }

    public void setModuleId(String moduleId) {
        ModuleId = moduleId;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String classId) {
        ClassId = classId;
    }

    public String getTrainerId() {
        return TrainerId;
    }

    public void setTrainerId(String trainerId) {
        TrainerId = trainerId;
    }
}
