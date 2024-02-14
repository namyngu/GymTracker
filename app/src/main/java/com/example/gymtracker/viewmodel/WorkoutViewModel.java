package com.example.gymtracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gymtracker.entity.User;
import com.example.gymtracker.entity.Workout;
import com.example.gymtracker.repository.WorkoutRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WorkoutViewModel extends AndroidViewModel {
    String userId;
    private WorkoutRepository workoutRepo;
    private LiveData<List<Workout>> allWorkouts;

    public WorkoutViewModel (@NonNull Application application, String userId) {
        super(application);
        workoutRepo = new WorkoutRepository(application, userId);
        allWorkouts = workoutRepo.getAllWorkouts(userId);
    }

    public CompletableFuture<Workout> findByIdFuture(final int workoutId) {
        return workoutRepo.findByIdFuture(workoutId);
    }

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }

    public void insert(Workout workout) {
        workoutRepo.insert(workout);
    }

    public void update(Workout workout) {
        workoutRepo.update(workout);
    }

    public void delete(Workout workout) {
        workoutRepo.delete(workout);
    }


}
