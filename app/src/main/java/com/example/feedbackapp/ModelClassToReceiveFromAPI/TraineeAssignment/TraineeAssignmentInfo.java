package com.example.feedbackapp.ModelClassToReceiveFromAPI.TraineeAssignment;

public class TraineeAssignmentInfo {
    private boolean success = true;
    private String message = "";
    private TraineeAssignment traineeAssignment = new TraineeAssignment();

    // get
    public boolean getSuccess() { return success; }
    public String getMessage() { return message; }
    public TraineeAssignment getTraineeAssignment() { return traineeAssignment; }
}
