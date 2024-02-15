package com.example.gymtracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.repository.ExerciseRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ExerciseViewModel extends AndroidViewModel {

    private ExerciseRepository eRepository;
    private LiveData<List<Exercise>> allExercisesForAUser;
    private MutableLiveData<Exercise> exercise = new MutableLiveData<>();

    public ExerciseViewModel (@NonNull Application application) {
        super(application);
        eRepository = new ExerciseRepository(application);

    }

    public CompletableFuture<Exercise> findByIDFuture(final int exerciseId) {
        return eRepository.findByIdFuture(exerciseId);
    }

    public LiveData<List<Exercise>> getAllExercisesForAUser(String userId) {
        allExercisesForAUser = eRepository.getAllExercisesForAUserExercises(userId);
        return allExercisesForAUser;
    }

    // Return LiveData because we don't want to expose the setValue and postvalue to the outside
    public LiveData<Exercise> getExercise() {
        return exercise;
    }

    public void insert(Exercise exercise) {
        eRepository.insert(exercise);
    }

    public void update(Exercise exercise) {
        eRepository.update(exercise);
    }

    public void delete(Exercise exercise) {
        eRepository.delete(exercise);
    }




}
