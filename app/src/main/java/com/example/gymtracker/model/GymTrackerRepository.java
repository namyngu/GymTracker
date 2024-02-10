package com.example.gymtracker.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.gymtracker.entity.DailyStep;
import com.example.gymtracker.entity.Equipment;
import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.entity.ExerciseCategory;
import com.example.gymtracker.entity.ExerciseLog;
import com.example.gymtracker.entity.Set;
import com.example.gymtracker.entity.User;
import com.example.gymtracker.entity.Weight;
import com.example.gymtracker.entity.Workout;
import com.example.gymtracker.interfaces.DailyStepDao;
import com.example.gymtracker.interfaces.EquipmentDao;
import com.example.gymtracker.interfaces.ExerciseCategoryDao;
import com.example.gymtracker.interfaces.ExerciseDao;
import com.example.gymtracker.interfaces.ExerciseLogDao;
import com.example.gymtracker.interfaces.SetDao;
import com.example.gymtracker.interfaces.UserDao;
import com.example.gymtracker.interfaces.WeightDao;
import com.example.gymtracker.interfaces.WorkoutDao;

import java.util.List;

public class GymTrackerRepository {

    private DailyStepDao dailyStepDao;
    private EquipmentDao equipmentDao;
    private ExerciseDao exerciseDao;
    private ExerciseCategoryDao exerciseCategoryDao;
    private ExerciseLogDao exerciseLogDao;
    private SetDao setDao;
    private UserDao userDao;
    private WeightDao weightDao;
    private WorkoutDao workoutDao;

    private LiveData<List<DailyStep>> allDailySteps;
    private LiveData<List<Equipment>> allEquipments;
    private LiveData<List<Exercise>> allExercises;
    private LiveData<List<ExerciseCategory>> allExerciseCategories;
    private LiveData<List<ExerciseLog>> allExerciseLogs;
    private LiveData<List<Set>> allSets;
    private LiveData<List<Weight>> allWeights;
    private LiveData<List<Workout>> allWorkouts;

    // Constructor
    public GymTrackerRepository(Application application) {
        GymTrackerDB db = GymTrackerDB.getInstance(application);

        dailyStepDao = db.dailyStepDao();
        allDailySteps = dailyStepDao.getAllDailySteps();

        equipmentDao = db.equipmentDao();
        allEquipments = equipmentDao.getAllEquipments();

        exerciseDao = db.exerciseDao();
        allExercises = exerciseDao.getAllExercises();

        exerciseCategoryDao = db.exerciseCategoryDao();
        allExerciseCategories = exerciseCategoryDao.getAllExerciseCategories();

        exerciseLogDao = db.exerciseLogDao();
        allExerciseLogs = exerciseLogDao.getAllExerciseLogs();

        setDao = db.setDao();
        allSets = setDao.getAllSets();

        weightDao = db.weightDao();
        allWeights = weightDao.getAllWeights();

        workoutDao = db.workoutDao();
        allWorkouts = workoutDao.getAllWorkouts();
    }

    public void insert (Workout workout) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                workoutDao.insert(workout);
            }
        });
    }

    public void update (Workout workout) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                workoutDao.update(workout);
            }
        });
    }

    public void delete (Workout workout) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                workoutDao.delete(workout);
            }
        });
    }

    public void deleteAllWorkouts () {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                workoutDao.deleteAllWorkouts();
            }
        });
    }

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }
}
