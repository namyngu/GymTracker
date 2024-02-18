package com.example.gymtracker.repository;

import android.app.Application;

import com.example.gymtracker.database.GymTrackerDB;
import com.example.gymtracker.entity.DailyStep;
import com.example.gymtracker.entity.Weight;
import com.example.gymtracker.interfaces.DailyStepDao;
import com.example.gymtracker.interfaces.WeightDao;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ReportRepository {

    private DailyStepDao dailyStepDao;
    private WeightDao weightDao;

    // Constructor
    public ReportRepository(Application application) {
        GymTrackerDB db = GymTrackerDB.getInstance(application);

        dailyStepDao = db.dailyStepDao();
        weightDao = db.weightDao();
    }

    public void insert(DailyStep dailyStep) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dailyStepDao.insert(dailyStep);
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

    public void delete(DailyStep dailyStep) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dailyStepDao.insert(dailyStep);
            }
        });
    }

    public DailyStep findDailyStep(String date) {
        return dailyStepDao.findDailyStep(date);
    }

    public CompletableFuture<List<DailyStep>> getAllDailySteps(String userId) {
        return CompletableFuture.supplyAsync(new Supplier<List<DailyStep>>() {
            @Override
            public List<DailyStep> get() {
                return dailyStepDao.getAllDailySteps(userId);
            }
        }, GymTrackerDB.databaseWriteExecutor);
    }

    public CompletableFuture<List<Weight>> getAllWeights(String userId) {
        return CompletableFuture.supplyAsync(new Supplier<List<Weight>>() {
            @Override
            public List<Weight> get() {
                return weightDao.getAllWeights(userId);
            }
        }, GymTrackerDB.databaseWriteExecutor);
    }

}
