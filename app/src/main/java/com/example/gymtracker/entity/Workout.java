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
    private long workoutId;
    private String name;
    private String userId;
    private Date date;

    public Workout(String name, @Nullable String userId, Date date) {
        this.name = name;
        this.userId = userId;
        this.date = date;
    }

    // Getter methods
    public long getWorkoutId() {
        return workoutId;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public String getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    //Setter methods

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(@Nullable String userId) {
        this.userId = userId;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
