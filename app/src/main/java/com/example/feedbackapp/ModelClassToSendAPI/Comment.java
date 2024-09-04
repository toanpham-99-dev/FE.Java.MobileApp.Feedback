package com.example.feedbackapp.ModelClassToSendAPI;

public class Comment {
    private String ClassId;
    private String ModuleId;
    private String Comment;
    private String Id;
    private String ClassName;
    private String ModuleName;
    public Comment(String classId, String moduleId, String comment, String id, String className, String moduleName) {
        ClassId = classId;
        ModuleId = moduleId;
        Comment = comment;
        Id = id;
        ClassName = className;
        ModuleName = moduleName;
    }



    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }


    public Comment(String classId, String moduleId, String comment) {
        ClassId = classId;
        ModuleId = moduleId;
        Comment = comment;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String classId) {
        ClassId = classId;
    }

    public String getModuleId() {
        return ModuleId;
    }

    public void setModuleId(String moduleId) {
        ModuleId = moduleId;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
