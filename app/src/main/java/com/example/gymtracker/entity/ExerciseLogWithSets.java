package com.example.gymtracker.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ExerciseLogWithSets {
    @Embedded
    public ExerciseLog exerciseLog;

    @Relation(
            parentColumn = "logId",
            entityColumn = "logId"
    )

    public List<Set> sets;
}
