package com.example.gymtracker.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.entity.TrainingPlan;
import com.example.gymtracker.entity.Workout;

import java.util.List;


@Dao
public abstract class TrainingPlanDao {

    @Insert
    public abstract void insert(TrainingPlan trainingPlan);
    @Insert
    public abstract long insert(Workout workout);
    @Update
    public abstract void update(TrainingPlan trainingPlan);
    @Delete
    public abstract void delete(TrainingPlan trainingPlan);

    @Query("SELECT * FROM training_plan ORDER BY workoutId")
    public abstract LiveData<List<TrainingPlan>> getAllTrainingPlans();


    // Get all TrainingPlans for a given exercise and workout.
    @Query("SELECT * FROM training_plan WHERE exerciseId = :exerciseId AND workoutId = :workoutId")
    public abstract List<TrainingPlan> getSetsForAnExerciseLog(int exerciseId, int workoutId);

    // Insert new workout and training plans
    // Need to make Dao abstract so we can include method body
    @Transaction
    public void insert(Workout workout, List<TrainingPlan> trainingPlans, int exerciseId) {
        // Save rowId of inserted workout as workoutId
        final long workoutId = insert(workout);

        // Set workoutId for all related trainingPlans
        for (TrainingPlan trainingPlan : trainingPlans) {
            trainingPlan.setWorkoutId(workoutId);
            trainingPlan.setExerciseId(exerciseId);
            insert(trainingPlan);
        }

    }

}
