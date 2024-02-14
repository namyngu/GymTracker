package com.example.gymtracker.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.entity.Workout;
import com.example.gymtracker.entity.WorkoutWithExercises;

import java.util.List;

@Dao
public interface WorkoutDao {

    @Insert
    public void insert(Workout workout);

    @Update
    public void update(Workout workout);

    @Delete
    public void delete(Workout workout);

    @Query("SELECT * FROM workout_table WHERE userId = :userId")
    public LiveData<List<Workout>> getAllWorkouts(String userId);

    @Query("SELECT * FROM workout_table WHERE workoutId = :workoutId")
    Workout findById(int workoutId);

    @Query("DELETE FROM workout_table")
    void deleteAllWorkouts();


    // Many-to-many
    // Get all exercises that belong to a workout
    @Transaction
    @Query("SELECT * FROM workout_table WHERE workoutId = :workoutId")
    public List<WorkoutWithExercises> getWorkoutWithExercises(int workoutId);

}
