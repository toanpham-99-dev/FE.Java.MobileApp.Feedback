package com.example.feedbackapp.ModelClassToReceiveFromAPI.Module;

public class ModuleInfo {
    private boolean isSuccess = false;
    private String message = "";
    private Module module = new Module();

    // get
    public boolean getIsSuccess() { return isSuccess; }
    public String getMessage() { return message; }
    public Module getModule() { return module; }
}
