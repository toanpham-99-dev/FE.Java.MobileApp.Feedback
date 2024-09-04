package com.example.feedbackapp.model;

public class HeaderRecycleView {
    String name;
    String module;
    String clas;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    String classId;
    String moduleId;
    public HeaderRecycleView(String name, String module, String clas) {
        this.name = name;
        this.module = module;
        this.clas = clas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }
}
