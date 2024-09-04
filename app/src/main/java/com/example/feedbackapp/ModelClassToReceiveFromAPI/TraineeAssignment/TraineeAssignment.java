package com.example.feedbackapp.ModelClassToReceiveFromAPI.TraineeAssignment;

public class TraineeAssignment {
    private String Id = "";
    private String RegistrationCode = "";
    private String TraineeId = "";

    // get
    public String getId() { return Id; }
    public String getRegistrationCode() { return RegistrationCode;}
    public String getTraineeId() { return TraineeId; }

    // set
    public void setId(String id) {Id = id;}
    public void setRegistrationCode(String registrationCode) {RegistrationCode = registrationCode;}
    public void setTraineeId(String traineeId) {TraineeId = traineeId;}
}
