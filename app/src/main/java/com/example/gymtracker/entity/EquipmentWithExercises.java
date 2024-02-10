package com.example.gymtracker.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class EquipmentWithExercises {
    @Embedded
    public Equipment equipment;

    @Relation(
            parentColumn = "equipmentId",
            entityColumn = "equipmentId"
    )
    public List<Exercise> exercises;
}
