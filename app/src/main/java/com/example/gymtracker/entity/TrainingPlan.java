package com.example.gymtracker.entity;

import androidx.room.Entity;

@Entity(tableName = "training_plan",
        primaryKeys = {"setNum","workoutId","exerciseId"})
public class TrainingPlan {
    private int setNum;
    private int workoutId;
    private int exerciseId;
    private int reps;
    private String notes;


    // Constructor
    public TrainingPlan(int setNum, int workoutId, int exerciseId, int reps, String notes) {
        this.setNum = setNum;
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.reps = reps;
        this.notes = notes;
    }

    // Getter methods
    public int getSetNum() {
        return setNum;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public int getReps() {
        return reps;
    }

    public String getNotes() {
        return notes;
    }


    // Setter methods
    public void setSetNum(int setNum) {
        this.setNum = setNum;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
