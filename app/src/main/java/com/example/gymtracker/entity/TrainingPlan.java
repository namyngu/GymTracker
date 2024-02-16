package com.example.gymtracker.entity;

import androidx.room.Entity;

import java.util.List;

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

    public Exercise getExercise(List<Exercise> exercises) {
        Exercise exercise = null;
        for (Exercise tmp : exercises) {
            if (tmp.getExerciseId() == exerciseId) {
                exercise = tmp;
            }
        }
        return exercise;
    }

    public Workout getWorkout(List<Workout> workouts) {
        Workout workout = null;
        for (Workout tmp : workouts) {
            if (tmp.getWorkoutId() == workoutId) {
                workout = tmp;
            }
        }
        return workout;
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
