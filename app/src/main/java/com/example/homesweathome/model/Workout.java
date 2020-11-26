package com.example.homesweathome.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

public class Workout extends UserObject implements Parcelable {
    private String name;
    private List<Exercise> exerciseList;
    private List<DayOfWeek> dayOfWeekList;
    private Date addedDate = new Date();

    public Workout() { super(); }

    public Workout(String uid, String name, List<DayOfWeek> dayOfWeekList) {
        super(uid);
        this.name = name;
        this.dayOfWeekList = dayOfWeekList;
    }

    protected Workout(Parcel in) {
        name = in.readString();
        exerciseList = in.readArrayList(null);
        dayOfWeekList = in.readArrayList(null);
        addedDate = (Date) in.readSerializable();
    }

    public static final Creator<Workout> CREATOR = new Creator<Workout>() {
        @Override
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }

        @Override
        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public List<DayOfWeek> getDayOfWeekList() {
        return dayOfWeekList;
    }

    public void setDayOfWeekList(List<DayOfWeek> dayOfWeekList) {
        this.dayOfWeekList = dayOfWeekList;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    @Override
    public String toString() {
        return (uid + "_" + name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeList(exerciseList);
        dest.writeList(dayOfWeekList);
        dest.writeSerializable(addedDate);
    }
}
