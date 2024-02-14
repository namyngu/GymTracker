//package com.example.gymtracker.entity;
//
//import androidx.room.Embedded;
//import androidx.room.Relation;
//
//import java.util.List;
//
//public class ExerciseWithExerciseLogs {
//    @Embedded public Exercise exercise;
//
//    @Relation(
//            parentColumn = "exerciseId",
//            entityColumn = "exerciseId"
//    )
//
//    public List<ExerciseLog> exerciseLogs;
//
//}
