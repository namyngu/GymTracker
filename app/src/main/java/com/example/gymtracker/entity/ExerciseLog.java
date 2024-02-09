package com.example.gymtracker.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(tableName = "exercise_log_table")
public class ExerciseLog {
    @PrimaryKey(autoGenerate = true)
    int logId;
    int workoutId;
    int exerciseId;

    // Constructor
    public ExerciseLog(int workoutId, int exerciseId) {
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
    }

    // Getter methods
    public int getLogId() {
        return logId;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public int getExerciseId() {
        return exerciseId;
    }
}
