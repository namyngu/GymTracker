//package com.example.gymtracker.repository;
//
//import android.app.Application;
//
//import androidx.lifecycle.LiveData;
//
//import com.example.gymtracker.entity.DailyStep;
//import com.example.gymtracker.entity.Equipment;
//import com.example.gymtracker.entity.Exercise;
//// import com.example.gymtracker.entity.ExerciseCategory;
//import com.example.gymtracker.entity.ExerciseLog;
//import com.example.gymtracker.entity.Set;
//import com.example.gymtracker.entity.Weight;
//import com.example.gymtracker.entity.Workout;
//import com.example.gymtracker.interfaces.DailyStepDao;
//import com.example.gymtracker.interfaces.EquipmentDao;
////import com.example.gymtracker.interfaces.ExerciseCategoryDao;
//import com.example.gymtracker.interfaces.ExerciseLogDao;
//import com.example.gymtracker.interfaces.SetDao;
//import com.example.gymtracker.interfaces.UserDao;
//import com.example.gymtracker.interfaces.WeightDao;
//import com.example.gymtracker.interfaces.WorkoutDao;
//import com.example.gymtracker.database.GymTrackerDB;
//
//import java.util.List;
//
//public class GymTrackerRepository {
//
//    private DailyStepDao dailyStepDao;
//    private EquipmentDao equipmentDao;
//    //private ExerciseDao exerciseDao;
////    private ExerciseCategoryDao exerciseCategoryDao;
//    private ExerciseLogDao exerciseLogDao;
//    private SetDao setDao;
//    private UserDao userDao;
//    private WeightDao weightDao;
//    private WorkoutDao workoutDao;
//
//    private LiveData<List<DailyStep>> allDailySteps;
//    private LiveData<List<Equipment>> allEquipments;
//    private LiveData<List<Exercise>> allExercises;
////    private LiveData<List<ExerciseCategory>> allExerciseCategories;
//    private LiveData<List<ExerciseLog>> allExerciseLogs;
//    private LiveData<List<Set>> allSets;
//    private LiveData<List<Weight>> allWeights;
//    private LiveData<List<Workout>> allWorkouts;
//
//    // Constructor
//    public GymTrackerRepository(Application application) {
//        GymTrackerDB db = GymTrackerDB.getInstance(application);
//
//        dailyStepDao = db.dailyStepDao();
//        allDailySteps = dailyStepDao.getAllDailySteps();
//
//        equipmentDao = db.equipmentDao();
//        allEquipments = equipmentDao.getAllEquipments();
//
//       // exerciseDao = db.exerciseDao();
//       // allExercises = exerciseDao.getAllExercises();
//
////        exerciseCategoryDao = db.exerciseCategoryDao();
////        allExerciseCategories = exerciseCategoryDao.getAllExerciseCategories();
//
//        exerciseLogDao = db.exerciseLogDao();
//        allExerciseLogs = exerciseLogDao.getAllExerciseLogs();
//
//        setDao = db.setDao();
//        allSets = setDao.getAllSets();
//
//        weightDao = db.weightDao();
//        allWeights = weightDao.getAllWeights();
//
//        workoutDao = db.workoutDao();
//        allWorkouts = workoutDao.getAllWorkouts();
//    }
//
//    public void insert(DailyStep dailyStep) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                dailyStepDao.insert(dailyStep);
//            }
//        });
//    }
//
//    public void insert(Equipment equipment) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                equipmentDao.insert(equipment);
//            }
//        });
//    }
//
////    public void insert(Exercise exercise) {
////        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
////            @Override
////            public void run() {
////                exerciseDao.insert(exercise);
////            }
////        });
////    }
//
//    public void insert(ExerciseLog exerciseLog) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                exerciseLogDao.insert(exerciseLog);
//            }
//        });
//    }
//
//    public void insert(Set set) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                setDao.insert(set);
//            }
//        });
//    }
//
//    public void insert(Weight weight) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                weightDao.insert(weight);
//            }
//        });
//    }
//
//    public void insert(Workout workout) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                workoutDao.insert(workout);
//            }
//        });
//    }
//
//    public void update(DailyStep dailyStep) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                dailyStepDao.update(dailyStep);
//            }
//        });
//    }
//
//    public void update(Equipment equipment) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                equipmentDao.update(equipment);
//            }
//        });
//    }
//
////    public void update(Exercise exercise) {
////        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
////            @Override
////            public void run() {
////                exerciseDao.update(exercise);
////            }
////        });
////    }
//
//    public void update(ExerciseLog exerciseLog) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                exerciseLogDao.update(exerciseLog);
//            }
//        });
//    }
//
//    public void update(Set set) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                setDao.update(set);
//            }
//        });
//    }
//
//    public void update(Weight weight) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                weightDao.update(weight);
//            }
//        });
//    }
//
//    public void update(Workout workout) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                workoutDao.update(workout);
//            }
//        });
//    }
//
//    public void delete(DailyStep dailyStep) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                dailyStepDao.delete(dailyStep);
//            }
//        });
//    }
//
//    public void delete(Equipment equipment) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                equipmentDao.delete(equipment);
//            }
//        });
//    }
//
////    public void delete(Exercise exercise) {
////        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
////            @Override
////            public void run() {
////                exerciseDao.delete(exercise);
////            }
////        });
////    }
//
//    public void delete(ExerciseLog exerciseLog) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                exerciseLogDao.delete(exerciseLog);
//            }
//        });
//    }
//
//    public void delete(Set set) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                setDao.delete(set);
//            }
//        });
//    }
//
//    public void delete(Weight weight) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                weightDao.delete(weight);
//            }
//        });
//    }
//
//    public void delete(Workout workout) {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                workoutDao.delete(workout);
//            }
//        });
//    }
//
//    public void deleteAllWorkouts() {
//        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                workoutDao.deleteAllWorkouts();
//            }
//        });
//    }
//
////    public CompletableFuture<Exercise> findByIdFuture(final int exerciseId) {
////        return CompletableFuture.supplyAsync(new Supplier<Exercise>() {
////            @Override
////            public Exercise get() {
////                return exerciseDao.findByID(exerciseId);
////            }
////        }, GymTrackerDB.databaseWriteExecutor);
////    }
//
//    public LiveData<List<Workout>> getAllWorkouts() {
//        return allWorkouts;
//    }
//
//    public LiveData<List<Exercise>> getAllExercises() {
//        return allExercises;
//    }
//}
