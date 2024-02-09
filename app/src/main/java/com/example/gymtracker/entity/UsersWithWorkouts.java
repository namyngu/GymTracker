package com.example.gymtracker.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UsersWithWorkouts {
    @Embedded public User user;

    @Relation(
            parentColumn = "userId",
            entityColumn = "userId"
    )

    public List<Workout> workouts;
}
