package com.example.gymtracker.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.entity.Workout;

import java.util.List;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Exercise> exercise = new MutableLiveData<>();

    // setValue is on the UI thread, postValue is on the background thread
    public void setText(Exercise input) {
        exercise.setValue(input);
    }

    // Return LiveData because we don't want to expose the setValue and postvalue to the outside
    public LiveData<Exercise> getExercise() {
        return exercise;
    }
}
