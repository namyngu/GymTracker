package com.example.gymtracker.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import java.util.List;

@Entity(tableName = "training_plan",
        primaryKeys = {"setNum","workoutId","exerciseId"},
        foreignKeys = @ForeignKey(
                entity = Workout.class,
                parentColumns = "workoutId",
                childColumns = "workoutId"),
        indices = @Index("workoutId"))
public class TrainingPlan {
    private int setNum;
    private long workoutId;
    private long exerciseId;
    private int reps;
    private String notes;

    @Ignore
    // Constructor
    public TrainingPlan(int setNum, int reps, String notes, long exerciseId) {
        this.setNum = setNum;
        this.reps = reps;
        this.notes = notes;
        this.exerciseId = exerciseId;
    }
    public TrainingPlan(int setNum, long workoutId, long exerciseId, int reps, String notes) {
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

    public long getWorkoutId() {
        return workoutId;
    }

    public long getExerciseId() {
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

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }
}
