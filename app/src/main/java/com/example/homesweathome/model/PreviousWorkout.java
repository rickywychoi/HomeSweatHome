package com.example.homesweathome.model;

import android.os.Parcel;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

public class PreviousWorkout extends UserObject{
    private String name;
    private Date playedDate = new Date();

    public PreviousWorkout() {super();};

    public PreviousWorkout(String uid, String namein) {
        super(uid);
        name = namein;
    }

    public String getPrevName() {
        return name;
    }

    public void setPrevName(String newName) {
        this.name = newName;
    }

    @Override
    public String toString() {
        return (uid + "_" + name + "_" + playedDate.getTime());
    }

    public Date getPlayedDate() { return playedDate; }
    public void setPlayedDate(Date newDate) {
        this.playedDate = newDate;
    }


}
