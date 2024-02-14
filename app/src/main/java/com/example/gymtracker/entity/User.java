package com.example.gymtracker.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey
    @NonNull
    private String userId;
    private String displayName;
    private int age;
    private double weight;
    private String userGroup;

    // Constructor
    public User(String userId, String displayName, int age, double weight, String userGroup) {
        this.userId = userId;
        this.displayName = displayName;
        this.age = age;
        this.weight = weight;
        this.userGroup = userGroup;
    }

    // Getter methods
    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getAge() {
        return age;
    }
    public double getWeight() {
        return weight;
    }

    public String getUserGroup() {
        return userGroup;
    }

    // Setter methods
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }


}
