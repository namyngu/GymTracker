package com.example.gymtracker.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.Date;

@Entity(tableName = "daily_step_table",
        primaryKeys = {"userId", "date"})
public class DailyStep {
    @NonNull
    private String userId;
    @NonNull
    private int steps;
    @NonNull
    private String date;

    // Constructor
    public DailyStep(String userId, int steps, String date) {
        this.userId = userId;
        this.steps = steps;
        this.date = date;
    }

    // Getter methods
    public String getUserId() {
        return userId;
    }

    public int getSteps() {
        return steps;
    }

    public String getDate() {
        return date;
    }

    // Setter methods
    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }
}
