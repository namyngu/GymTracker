package com.example.gymtracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.entity.TrainingPlan;
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
        return workoutRepo.getAllExercisesForAWorkout(workout);
    }

    public LiveData<List<Exercise>> getLiveDataExercisesForAUser(String userId) {
        return workoutRepo.getLiveDataExercisesForAUser(userId);
    }

    public CompletableFuture<List<Exercise>> getAllExercisesForAUser(String userId) {
        return workoutRepo.getAllExercisesForAUser(userId);
    }

    public LiveData<List<TrainingPlan>> getAllTrainingPlans(String userId) {
        return workoutRepo.getAllTrainingPlans(userId);
    }



    public void insert(Workout workout) {
        workoutRepo.insert(workout);
    }

    public void insert(TrainingPlan trainingPlan) {workoutRepo.insert(trainingPlan);}

    public void insert(Workout workout, List<TrainingPlan> trainingPlans) {
        workoutRepo.insert(workout, trainingPlans);
    }

    public void update(Workout workout) {
        workoutRepo.update(workout);
    }

    public void delete(Workout workout) {
        workoutRepo.delete(workout);
    }


}
