package com.example.homesweathome.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserObject {
    protected String uid;

    public UserObject() {}

    public UserObject(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }
}
