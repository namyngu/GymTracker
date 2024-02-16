package com.example.gymtracker.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.interfaces.ExerciseDao;
import com.example.gymtracker.database.GymTrackerDB;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ExerciseRepository {

    private ExerciseDao exerciseDao;
    private LiveData<List<Exercise>> allExercisesForAUser;

    // Constructor
    public ExerciseRepository(Application application) {
        GymTrackerDB db = GymTrackerDB.getInstance(application);

        exerciseDao = db.exerciseDao();
    }

    public void insert(Exercise exercise) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                exerciseDao.insert(exercise);
            }
        });
    }

    public void update(Exercise exercise) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                exerciseDao.update(exercise);
            }
        });
    }

    public void delete(Exercise exercise) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                exerciseDao.delete(exercise);
            }
        });
    }

    public CompletableFuture<Exercise> findByIdFuture(final int exerciseId) {
        return CompletableFuture.supplyAsync(new Supplier<Exercise>() {
            @Override
            public Exercise get() {
                return exerciseDao.findByID(exerciseId);
            }
        }, GymTrackerDB.databaseWriteExecutor);
    }

    public LiveData<List<Exercise>> getAllExercisesForAUser(String userId) {
        allExercisesForAUser = exerciseDao.getLiveDataExercisesForAUser(userId);
        return allExercisesForAUser;
    }
}
