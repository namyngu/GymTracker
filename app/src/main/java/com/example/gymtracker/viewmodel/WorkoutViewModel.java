package com.example.gymtracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.entity.Set;
import com.example.gymtracker.entity.User;
import com.example.gymtracker.entity.Workout;
import com.example.gymtracker.repository.WorkoutRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WorkoutViewModel extends AndroidViewModel {
    String userId;
    private WorkoutRepository workoutRepo;
    private LiveData<List<Workout>> allUserWorkouts;

    public WorkoutViewModel (@NonNull Application application) {
        super(application);
        workoutRepo = new WorkoutRepository(application);
    }

    public CompletableFuture<Workout> findByIdFuture(final int workoutId) {
        return workoutRepo.findByIdFuture(workoutId);
    }

    public LiveData<List<Workout>> getAllWorkoutsForAUser(String userId) {
        allUserWorkouts = workoutRepo.getAllWorkoutsForAUser(userId);
        return allUserWorkouts;
    }

    public List<Exercise> getAllExercisesForAWorkout(Workout workout) {
        List<Exercise> allExercisesForAWorkout = workoutRepo.getAllExercisesForAWorkout(workout);
        return allExercisesForAWorkout;
    }

    public List<Set> getSetsForAnExerciseLog (Workout workout, Exercise exercise) {
        List<Set> sets = workoutRepo.getSetsForAnExerciseLog(workout, exercise);
        return sets;
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
