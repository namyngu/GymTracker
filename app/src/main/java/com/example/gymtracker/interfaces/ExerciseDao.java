package com.example.gymtracker.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.gymtracker.entity.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert
    public void insert(Exercise exercise);

    @Update
    public void update(Exercise exercise);

    @Delete
    public void delete(Exercise exercise);

    @Query("SELECT * FROM exercise_table ORDER BY name ASC")
    public LiveData<List<Exercise>> getAllExercises();

    @Query("SELECT * FROM exercise_table WHERE exercise_table.userId =:userId")
    public LiveData<List<Exercise>> getLiveDataExercisesForAUser(String userId);

    @Query("SELECT * FROM exercise_table WHERE exercise_table.userId =:userId")
    public List<Exercise> getAllExercisesForAUser(String userId);

    @Query("SELECT * FROM exercise_table WHERE exerciseId = :exerciseId LIMIT 1")
    Exercise findByID(int exerciseId);

    @Query("DELETE FROM exercise_table")
    void deleteAllExercises();

}
