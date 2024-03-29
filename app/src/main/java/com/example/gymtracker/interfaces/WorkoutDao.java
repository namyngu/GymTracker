package com.example.gymtracker.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.gymtracker.entity.TrainingPlan;
import com.example.gymtracker.entity.Workout;
import com.example.gymtracker.entity.WorkoutWithExercises;

import java.util.List;

@Dao
public interface WorkoutDao {

    @Insert
    public long insert(Workout workout);

    @Update
    public void update(Workout workout);

    @Delete
    public void delete(Workout workout);

    @Query("SELECT * FROM workout_table WHERE userId = :userId")
    public LiveData<List<Workout>> getAllWorkoutsForAUser(String userId);

    @Query("SELECT * FROM workout_table WHERE workoutId = :workoutId")
    Workout findById(int workoutId);

    @Query("DELETE FROM workout_table")
    void deleteAllWorkouts();


    //TODO: DELETE LATER
//    // This query joins 3 tables - Set, ExerciseLog and Workout
//    // It gets a list of all sets that belong to a user.
//    @Query("SELECT * FROM TrainingPlan " +
//    "INNER JOIN exercise_log_table ON exercise_log_table.workoutId = TrainingPlan.workoutId  " +
//    "INNER JOIN workout_table ON exercise_log_table.workoutId = workout_table.workoutId " +
//    "WHERE workout_table.userId = :userId")
//    public LiveData<List<TrainingPlan>> getAllSetsForAUser(String userId);


    // Many-to-many
    // Get all exercises that belong to a workout
    @Transaction
    @Query("SELECT * FROM workout_table WHERE workoutId = :workoutId")
    public List<WorkoutWithExercises> getWorkoutWithExercises(long workoutId);

    @Transaction
    @Query("SELECT * FROM training_plan " +
    "INNER JOIN workout_table On workout_table.workoutId = training_plan.workoutId " +
    "WHERE workout_table.userId = :userId")
    public LiveData<List<TrainingPlan>> getAllTrainingPlans(String userId);

}
