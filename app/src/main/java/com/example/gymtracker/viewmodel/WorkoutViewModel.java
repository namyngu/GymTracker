package com.example.gymtracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gymtracker.entity.Workout;
import com.example.gymtracker.repository.GymTrackerRepository;

import java.util.List;

public class WorkoutViewModel extends AndroidViewModel {
    private GymTrackerRepository repository;
    private LiveData<List<Workout>> allWorkouts;

    public WorkoutViewModel (@NonNull Application application) {
        super(application);
        repository = new GymTrackerRepository(application);
        allWorkouts = repository.getAllWorkouts();
    }

    public void insert(Workout workout) {
        repository.insert(workout);
    }

    public void update(Workout workout) {
        repository.update(workout);
    }

    public void delete(Workout workout) {
        repository.delete(workout);
    }

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }
}
