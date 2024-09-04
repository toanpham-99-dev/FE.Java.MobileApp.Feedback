package com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment;

/**
 * Id": "60ab5cf36d50a8379435dd43",
 *             "ClassId": "60a75ccbec1f7025c8d2fef9",
 *             "ClassName": "Class 01",
 *             "ModuleId": "60aa6bb5fdd5941f88e6aa61",
 *             "ModuleName": "Hibernate",
 *             "TrainerId": "60a724db957aa60c7c7c3ea3",
 *             "TrainerName": "toandpqn1234",
 *             "RegistrationCode": "CL6M6T1621843187410"
 * */

public class Assignment {
    private String Id;
    private String ClassId;
    private String ClassName;
    private String ModuleId;
    private String ModuleName;
    private String TrainerId;
    private String TrainerName;
    private String RegistrationCode;


    public Assignment(String id, String classId, String className, String moduleId, String moduleName, String trainerId, String trainerName, String registrationCode) {
        Id = id;
        ClassId = classId;
        ClassName = className;
        ModuleId = moduleId;
        ModuleName = moduleName;
        TrainerId = trainerId;
        TrainerName = trainerName;
        RegistrationCode = registrationCode;
    }


    public String getId() {
        return Id;
    }

    public String getClassId() {
        return ClassId;
    }

    public String getClassName() {
        return ClassName;
    }

    public String getModuleId() {
        return ModuleId;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public String getTrainerId() {
        return TrainerId;
    }

    public String getTrainerName() {
        return TrainerName;
    }

    public String getRegistrationCode() {
        return RegistrationCode;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setClassId(String classId) {
        ClassId = classId;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public void setModuleId(String moduleId) {
        ModuleId = moduleId;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    public void setTrainerId(String trainerId) {
        TrainerId = trainerId;
    }

    public void setTrainerName(String trainerName) {
        TrainerName = trainerName;
    }

    public void setRegistrationCode(String registrationCode) {
        RegistrationCode = registrationCode;
    }
}
