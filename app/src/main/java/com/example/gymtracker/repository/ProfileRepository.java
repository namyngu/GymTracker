package com.example.gymtracker.repository;

import android.app.Application;

import com.example.gymtracker.database.GymTrackerDB;
import com.example.gymtracker.entity.DailyStep;
import com.example.gymtracker.entity.User;
import com.example.gymtracker.interfaces.DailyStepDao;
import com.example.gymtracker.interfaces.UserDao;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ProfileRepository {

    private UserDao userDao;
    DailyStepDao dailyStepDao;
    private User user;

    // Constructor
    public ProfileRepository(Application application) {
        GymTrackerDB db = GymTrackerDB.getInstance(application);

        userDao = db.userDao();
    }


    public void insert(User user) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insert(user);
            }
        });
    }

    public void insert(DailyStep dailyStep) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dailyStepDao.insert(dailyStep);
            }
        });
    }

    public void update(User user) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.update(user);
            }
        });
    }

    public void update(DailyStep dailyStep) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dailyStepDao.update(dailyStep);
            }
        });
    }

    public void delete(User user) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.delete(user);
            }
        });
    }

    public void delete(DailyStep dailyStep) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dailyStepDao.insert(dailyStep);
            }
        });
    }

    public CompletableFuture<DailyStep> findDailyStep(String date) {
        DailyStep dailyStep = null;
        return CompletableFuture.supplyAsync(new Supplier<DailyStep>() {
            @Override
            public DailyStep get() {
                return dailyStepDao.findDailyStep(date);
            }
        });
    }

    public CompletableFuture<User> findUserByIdFuture(final String userId) {
        return CompletableFuture.supplyAsync(new Supplier<User>() {
            @Override
            public User get() {
                return userDao.findById(userId);
            }
        }, GymTrackerDB.databaseWriteExecutor);
    }
}
