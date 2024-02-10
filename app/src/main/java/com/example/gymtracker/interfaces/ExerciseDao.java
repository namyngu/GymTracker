package com.example.gymtracker.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.entity.ExerciseWithExerciseLogs;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert
    public void insert(Exercise exercise);

    @Update
    public void update(Exercise exercise);

    @Delete
    public void delete(Exercise exercise);

    @Query("SELECT * FROM exercise_table")
    public LiveData<List<Exercise>> getAllExercises();

    @Transaction
    public LiveData<List<ExerciseWithExerciseLogs>> getAllExerciseWithExerciseLogs();
}
