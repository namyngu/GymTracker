package com.example.gymtracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.model.GymTrackerRepository;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {

    private GymTrackerRepository repository;
    private LiveData<List<Exercise>> allExercises;

    public ExerciseViewModel (@NonNull Application application) {
        super(application);
        repository = new GymTrackerRepository(application);
        allExercises = repository.getAllExercises();
    }

    public void insert(Exercise exercise) {
        repository.insert(exercise);
    }

    public void update(Exercise exercise) {
        repository.update(exercise);
    }

    public void delete(Exercise exercise) {
        repository.delete(exercise);
    }

    public LiveData<List<Exercise>> getAllExercises() {
        return allExercises;
    }
}
