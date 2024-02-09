package com.example.gymtracker.entity;

import androidx.room.Entity;

import java.util.Date;

@Entity(tableName = "daily_step_table",
        primaryKeys = {"userId", "steps", "date"})
public class DailyStep {
    private String userId;
    private int steps;
    private Date date;

    // Constructor
    public DailyStep(String userId, int steps, Date date) {
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

    public Date getDate() {
        return date;
    }
}
