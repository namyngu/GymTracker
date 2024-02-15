package com.example.gymtracker.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class WorkoutWithExercises {
    @Embedded public Workout workout;

    @Relation(
            parentColumn = "workoutId",
            entityColumn = "exerciseId",
            associateBy = @Junction(TrainingPlan.class)
    )
    public List<Exercise> exercises;

    // Getter methods
    public List<Exercise> getExercises() {
        return exercises;
    }
}
