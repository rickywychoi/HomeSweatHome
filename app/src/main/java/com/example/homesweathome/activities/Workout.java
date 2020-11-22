package com.example.homesweathome.activities;

public class Workout {
    String workoutTitle;
    Integer repetitions;
    Integer sets;
    Integer minutes;
    Integer seconds;

    public Workout() {}

    public Workout(String workoutTitle, Integer repetitions,
                   Integer sets, Integer minutes, Integer seconds) {
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
