package com.example.gymtracker.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.gymtracker.entity.ExerciseLog;
import com.example.gymtracker.entity.ExerciseLogWithSets;

import java.util.List;

@Dao
public interface ExerciseLogDao {

    @Insert
    public void insert(ExerciseLog exerciseLog);
    @Update
    public void update(ExerciseLog exerciseLog);
    @Delete
    public void delete(ExerciseLog exerciseLog);

    @Query("SELECT * FROM exercise_log_table ORDER BY workoutId")
    public LiveData<List<ExerciseLog>> getAllExerciseLogs();

    @Transaction
    @Query("SELECT * FROM exercise_log_table")
    public LiveData<List<ExerciseLogWithSets>> getAllExerciseLogWithSets();
}
