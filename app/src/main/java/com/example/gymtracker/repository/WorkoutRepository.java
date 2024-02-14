package com.example.gymtracker.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.gymtracker.database.GymTrackerDB;
import com.example.gymtracker.entity.User;
import com.example.gymtracker.entity.Workout;
import com.example.gymtracker.interfaces.UserDao;
import com.example.gymtracker.interfaces.WorkoutDao;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class WorkoutRepository {

    private WorkoutDao workoutDao;
    private LiveData<List<Workout>> allWorkouts;

    public WorkoutRepository(Application application, String userId) {
        GymTrackerDB db = GymTrackerDB.getInstance(application);

        workoutDao = db.workoutDao();
        LiveData<List<Workout>> workouts = getAllWorkouts(userId);
    }

    // Need to only return workouts that belong to the user
    // CompletableFuture may be optional
    public LiveData<List<Workout>> getAllWorkouts(String userId) {
        LiveData<List<Workout>> allWorkouts = null;

        CompletableFuture<LiveData<List<Workout>>> completableWorkouts =
                CompletableFuture.supplyAsync(new Supplier<LiveData<List<Workout>>>() {
            @Override
            public LiveData<List<Workout>> get() {
                return workoutDao.getAllWorkouts(userId);
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
