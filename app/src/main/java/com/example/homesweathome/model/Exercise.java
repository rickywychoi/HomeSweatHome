package com.example.homesweathome.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Exercise extends UserObject implements Parcelable {
    private String workoutTitle;
    private String exerciseTitle;
    private Integer repetitions;
    private Integer sets;
    private Integer minutes;
    private Integer seconds;
    private Date addedDate = new Date();
    private boolean isRepBased = true;

    public Exercise() { super(); }

    public Exercise(String uid, String workoutTitle, String exerciseTitle, Integer repetitions,
                    Integer sets, Integer minutes, Integer seconds, boolean isRepBased) {
        super(uid);
        this.workoutTitle = workoutTitle;
        this.exerciseTitle = exerciseTitle;
        this.repetitions = repetitions;
        this.sets = sets;
        this.minutes = minutes;
        this.seconds = seconds;
        this.isRepBased = isRepBased;
    }

    protected Exercise(Parcel in) {
        workoutTitle = in.readString();
        exerciseTitle = in.readString();
        if (in.readByte() == 0) {
            repetitions = null;
        } else {
            repetitions = in.readInt();
        }
        if (in.readByte() == 0) {
            sets = null;
        } else {
            sets = in.readInt();
        }
        if (in.readByte() == 0) {
            minutes = null;
        } else {
            minutes = in.readInt();
        }
        if (in.readByte() == 0) {
            seconds = null;
        } else {
            seconds = in.readInt();
        }
        isRepBased = in.readByte() != 0;
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    public String getWorkoutTitle() {
        return workoutTitle;
    }

    public void setWorkoutTitle(String workoutTitle) {
        this.workoutTitle = workoutTitle;
    }

    public String getExerciseTitle() { return exerciseTitle; }

    public void setExerciseTitle(String exerciseTitle) {
        this.exerciseTitle = exerciseTitle;
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

    public boolean isRepBased() {
        return isRepBased;
    }

    public void setRepBased(boolean repBased) {
        isRepBased = repBased;
    }

    @Override
    public String toString() {
        return (uid + "_" + workoutTitle + "_" + exerciseTitle + "_" + addedDate.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(workoutTitle);
        dest.writeString(exerciseTitle);
        if (repetitions == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(repetitions);
        }
        if (sets == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sets);
        }
        if (minutes == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(minutes);
        }
        if (seconds == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(seconds);
        }
        dest.writeByte((byte) (isRepBased ? 1 : 0));
    }
}
