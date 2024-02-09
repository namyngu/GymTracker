package com.example.gymtracker.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "set_table")
public class Set {
    @PrimaryKey(autoGenerate = true)
    private int setId;
    private int logId;
    private int reps;
    private int weight;
    private String notes;
    private int setNum;

    // Constructor
    public Set(int logId, int reps, int weight, String notes, int setNum) {
        this.logId = logId;
        this.reps = reps;
        this.weight = weight;
        this.notes = notes;
        this.setNum = setNum;
    }

    // Getter methods
    public int getSetId() {
        return setId;
    }

    public int getLogId() {
        return logId;
    }

    public int getReps() {
        return reps;
    }

    public int getWeight() {
        return weight;
    }

    public String getNotes() {
        return notes;
    }

    public int getSetNum() {
        return setNum;
    }

    // Setter methods
    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setSetNum(int setNum) {
        this.setNum = setNum;
    }
}
