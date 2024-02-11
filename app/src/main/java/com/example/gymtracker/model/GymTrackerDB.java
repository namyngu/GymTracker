package com.example.gymtracker.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.gymtracker.entity.*;
import com.example.gymtracker.interfaces.DailyStepDao;
import com.example.gymtracker.interfaces.EquipmentDao;
import com.example.gymtracker.interfaces.ExerciseCategoryDao;
import com.example.gymtracker.interfaces.ExerciseDao;
import com.example.gymtracker.interfaces.ExerciseLogDao;
import com.example.gymtracker.interfaces.SetDao;
import com.example.gymtracker.interfaces.UserDao;
import com.example.gymtracker.interfaces.WeightDao;
import com.example.gymtracker.interfaces.WorkoutDao;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {DailyStep.class, Equipment.class, EquipmentWithExercises.class, Exercise.class,
        ExerciseCategory.class, ExerciseCategoryWithExercises.class, ExerciseLog.class,
        ExerciseLogWithSets.class, ExerciseWithExerciseLogs.class, Set.class, User.class,
        UserWithWorkouts.class, Weight.class, Workout.class, WorkoutWithExerciseLogs.class}, version = 1)
@TypeConverters({TypeConverter.class})
public abstract class GymTrackerDB extends RoomDatabase {

    private static GymTrackerDB instance;

    // Later use this to access our Dao
    public abstract DailyStepDao dailyStepDao();

    public abstract EquipmentDao equipmentDao();

    public abstract ExerciseDao exerciseDao();

    public abstract ExerciseCategoryDao exerciseCategoryDao();

    public abstract ExerciseLogDao exerciseLogDao();

    public abstract SetDao setDao();

    public abstract UserDao userDao();

    public abstract WeightDao weightDao();

    public abstract WorkoutDao workoutDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Create single instance of db
    public static synchronized GymTrackerDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            GymTrackerDB.class, "GymTrackerDatabase")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomDatabaseCallback)
                    .build();
        }
        return instance;
    }

    // Populate Data When Database is Created
    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Populate initial data
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    // Empty for now - maybe add Data from API?

                }
            });
        }
    };
}

