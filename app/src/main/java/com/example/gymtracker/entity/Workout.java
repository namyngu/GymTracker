package com.example.gymtracker.entity;


import androidx.annotation.Nullable;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.Date;
import java.util.List;

@Entity(tableName = "workout_table")
public class Workout {
    @PrimaryKey(autoGenerate = true)
    private int workoutId;
    @Nullable
    private String userId;
    private Date date;

    public Workout(@Nullable String userId, Date date) {
        this.userId = userId;
        this.date = date;
    }

    // Getter methods
    public int getWorkoutId() {
        return workoutId;
    }

    @Nullable
    public String getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    //Setter methods
    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public void setUserId(@Nullable String userId) {
        this.userId = userId;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
