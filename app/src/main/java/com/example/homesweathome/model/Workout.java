package com.example.homesweathome.model;

import java.util.Date;
import java.util.List;

public class Workout extends UserObject {
    private String name;
    private List<Exercise> exerciseList;
    private Date addedDate = new Date();

    public Workout(String uid, String name) {
        super(uid);
        this.name = name;
    }

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

    public Date getAddedDate() {
        return addedDate;
    }
}
