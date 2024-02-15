package com.example.gymtracker.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gymtracker.entity.TrainingPlan;

import java.util.List;

@Dao
public interface TrainingPlanDao {

    @Insert
    public void insert(TrainingPlan trainingPlan);
    @Update
    public void update(TrainingPlan trainingPlan);
    @Delete
    public void delete(TrainingPlan trainingPlan);

    @Query("SELECT * FROM training_plan ORDER BY workoutId")
    public LiveData<List<TrainingPlan>> getAllTrainingPlans();


    // Get all TrainingPlans for a given exercise and workout.
    @Query("SELECT * FROM training_plan WHERE exerciseId = :exerciseId AND workoutId = :workoutId")
    public List<TrainingPlan> getSetsForAnExerciseLog(int exerciseId, int workoutId);

}
