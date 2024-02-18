package com.example.gymtracker.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.Date;

@Entity(tableName = "weight_table",
        primaryKeys = {"userId", "weight", "date"})
public class Weight {
    @NonNull
    private String userId;
    @NonNull
    private float weight;
    @NonNull
    private String date;

    // Constructor
    public Weight(String userId, float weight, String date) {
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

    public String getDate() {
        return date;
    }

}
