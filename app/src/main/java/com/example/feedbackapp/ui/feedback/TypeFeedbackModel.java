package com.example.feedbackapp.ui.feedback;

public class TypeFeedbackModel {
    int typeId;

    public TypeFeedbackModel() {
    }

    String typeName;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }



    public TypeFeedbackModel(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }


}
