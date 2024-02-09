package com.example.gymtracker.entity;

import androidx.room.Entity;

import java.util.Date;

@Entity(tableName = "weight_table",
        primaryKeys = {"userId", "weight", "date"})
public class Weight {
    private String userId;
    private float weight;
    private Date date;

    // Constructor
    public Weight(String userId, float weight, Date date) {
        this.userId = userId;
        this.weight = weight;
        this.date = date;
    }

    // Getter methods
    public String getUserId() {
        return userId;
    }

    public float getWeight() {
        return weight;
    }

    public Date getDate() {
        return date;
    }
}
