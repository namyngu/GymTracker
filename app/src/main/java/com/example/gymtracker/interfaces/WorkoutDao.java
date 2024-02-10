package com.example.gymtracker.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.gymtracker.entity.Workout;
import com.example.gymtracker.entity.WorkoutWithExerciseLogs;

import java.util.List;

@Dao
public interface WorkoutDao {

    @Insert
    public void insert(Workout workout);

    @Update
    public void update(Workout workout);

    @Delete
    public void delete(Workout workout);

    @Query("SELECT * FROM workout_table ORDER BY workoutId ASC")
    public LiveData<List<Workout>> getAllWorkouts();

    @Transaction
    @Query("SELECT * FROM workout_table")
    public LiveData<List<WorkoutWithExerciseLogs>> getWorkoutWithExerciseLogs ();
}
