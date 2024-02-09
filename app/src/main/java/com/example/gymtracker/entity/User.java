package com.example.gymtracker.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    private String userId;
    private String displayName;
    private int age;
    private String userGroup;

    // Constructor
    public User(String displayName, int age, String userGroup) {
        this.displayName = displayName;
        this.age = age;
        this.userGroup = userGroup;
    }

    // Setter methods
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUserGroup(String userGroup) {
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

    public String getUserGroup() {
        return userGroup;
    }
}
