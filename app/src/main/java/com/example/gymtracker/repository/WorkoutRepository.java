package com.example.gymtracker.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.gymtracker.database.GymTrackerDB;
import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.entity.TrainingPlan;
import com.example.gymtracker.entity.Workout;
import com.example.gymtracker.entity.WorkoutWithExercises;
import com.example.gymtracker.interfaces.ExerciseDao;
import com.example.gymtracker.interfaces.TrainingPlanDao;
import com.example.gymtracker.interfaces.WorkoutDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class WorkoutRepository {

    private WorkoutDao workoutDao;
    private ExerciseDao exerciseDao;
    private TrainingPlanDao trainingPlanDao;
    private LiveData<List<Workout>> allWorkouts;

    public WorkoutRepository(Application application) {
        GymTrackerDB db = GymTrackerDB.getInstance(application);

        workoutDao = db.workoutDao();
        exerciseDao = db.exerciseDao();
        trainingPlanDao = db.trainingPlanDao();
    }

    // Need to only return workouts that belong to the user
    // CompletableFuture may be optional
    public LiveData<List<Workout>> getAllWorkoutsForAUser(String userId) {
        LiveData<List<Workout>> allWorkouts = null;

        CompletableFuture<LiveData<List<Workout>>> completableWorkouts =
                CompletableFuture.supplyAsync(new Supplier<LiveData<List<Workout>>>() {
            @Override
            public LiveData<List<Workout>> get() {
                return workoutDao.getAllWorkoutsForAUser(userId);
            }
        }, GymTrackerDB.databaseWriteExecutor);

        try {
            allWorkouts = completableWorkouts.get();
        }
        catch (Exception e) {
            Log.e("WorkoutRepository", "Couldn't find any workouts");
        }
        return allWorkouts;
    }

    // Get all the exercises for a given workout
    // TODO: TEST IF THIS WORKS
    public List<Exercise> getAllExercisesForAWorkout(Workout workout) {

        List<WorkoutWithExercises> tmpData = workoutDao.getWorkoutWithExercises(workout.getWorkoutId());

        List<Exercise> exercises = new ArrayList<>();
        for (WorkoutWithExercises tmp : tmpData) {
            exercises.addAll(tmp.getExercises());
        }

        return exercises;
    }

    public LiveData<List<Exercise>> getLiveDataExercisesForAUser(String userId) {
        return exerciseDao.getLiveDataExercisesForAUser(userId);
    }

    public CompletableFuture<List<Exercise>> getAllExercisesForAUser(String userId) {
        return CompletableFuture.supplyAsync(new Supplier<List<Exercise>>() {
            @Override
            public List<Exercise> get() {
                return exerciseDao.getAllExercisesForAUser(userId);
            }
        }, GymTrackerDB.databaseWriteExecutor);
    }

    public LiveData<List<TrainingPlan>> getAllTrainingPlans(String userId) {
        return workoutDao.getAllTrainingPlans(userId);
    }

    public CompletableFuture<Workout> findByIdFuture(final int workoutId) {
        return CompletableFuture.supplyAsync(new Supplier<Workout>() {
            @Override
            public Workout get() {
                return workoutDao.findById(workoutId);
            }
        }, GymTrackerDB.databaseWriteExecutor);
    }

    public void insert(Workout workout) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
               workoutDao.insert(workout);
            }
        });
    }

    public void insert(TrainingPlan trainingPlan) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                trainingPlanDao.insert(trainingPlan);
            }
        });
    }

    public void insert(Workout workout, List<TrainingPlan> trainingPlans) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                trainingPlanDao.insert(workout, trainingPlans);
            }
        });
    }

    public void update(Workout workout) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                workoutDao.update(workout);
            }
        });
    }

    public void delete(Workout workout) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                workoutDao.delete(workout);
            }
        });
    }
}
