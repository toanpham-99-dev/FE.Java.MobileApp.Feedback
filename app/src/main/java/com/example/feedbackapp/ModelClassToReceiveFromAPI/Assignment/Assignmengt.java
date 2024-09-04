package com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment;

import androidx.annotation.NonNull;

/**
 * "_id": "60b4b2c4f176040015d2a584",
 *         "Class": "60a75f9f62beee0015a00b14",
 *         "Module": "60aa6a1501e23f0b98f0679d",
 *         "Trainer": "60a724db957aa60c7c7c3ea3",
 *         "RegistrationCode": "CL6M6T1622454980072",
 *         "__v": 0
 * */
public class Assignmengt {
    private String _id;
    private String Class;
    private String Module;
    private String Trainer;
    private String RegistrationCode;
    private String __v;

    public Assignmengt(String _id, String aClass, String module, String trainer, String registrationCode, String __v) {
        this._id = _id;
        Class = aClass;
        Module = module;
        Trainer = trainer;
        RegistrationCode = registrationCode;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getClassId() {
        return Class;
    }

    public void setClass(String aClass) {
        Class = aClass;
    }

    public String getModule() {
        return Module;
    }

    public void setModule(String module) {
        Module = module;
    }

    public String getTrainer() {
        return Trainer;
    }

    public void setTrainer(String trainer) {
        Trainer = trainer;
    }

    public String getRegistrationCode() {
        return RegistrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        RegistrationCode = registrationCode;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }
}
