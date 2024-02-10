package com.example.gymtracker.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class WorkoutWithExerciseLogs {
    @Embedded public Workout workout;
    @Relation(
            parentColumn = "workoutId",
            entityColumn = "workoutId"
    )

    List<ExerciseLog> exerciseLogs;
}
