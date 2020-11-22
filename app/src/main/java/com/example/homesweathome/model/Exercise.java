package com.example.homesweathome.model;

import java.util.Date;

public class Exercise extends UserObject {
    private String workoutTitle;
    private Integer repetitions;
    private Integer sets;
    private Integer minutes;
    private Integer seconds;
    private Date addedDate = new Date();

    public Exercise() { super(); }

    public Exercise(String uid, String workoutTitle, Integer repetitions,
                    Integer sets, Integer minutes, Integer seconds) {
        super(uid);
        this.workoutTitle = workoutTitle;
        this.repetitions = repetitions;
        this.sets = sets;
        this.minutes = minutes;
        this.seconds = seconds;
    }


    public String getWorkoutTitle() { return workoutTitle; }

    public void setWorkoutTitle(String workoutTitle) {
        this.workoutTitle = workoutTitle;
    }

    public Integer getRepetitions() {return repetitions;}

    public void setRepetitions(Integer reps) {
        this.repetitions = reps;
    }

    public Integer getSets() {return sets;}

    public void setStudentLastName(Integer sets) {
        this.sets = sets;
    }

    public Integer getMinutes() {return minutes;}

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getSeconds() {return seconds;}

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

}
