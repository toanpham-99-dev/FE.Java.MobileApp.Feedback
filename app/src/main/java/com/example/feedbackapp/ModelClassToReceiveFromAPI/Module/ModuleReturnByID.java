package com.example.feedbackapp.ModelClassToReceiveFromAPI.Module;

public class ModuleReturnByID {
    private boolean isSuccess;
    private String message;
    private Module module;

    public Module getModule() {
        return module;
    }

    public ModuleReturnByID(boolean isSuccess, String message, Module module) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.module = module;
    }
}
