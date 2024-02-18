package com.example.gymtracker.repository;

import android.app.Application;

import com.example.gymtracker.database.GymTrackerDB;
import com.example.gymtracker.entity.DailyStep;
import com.example.gymtracker.interfaces.DailyStepDao;

public class ReportRepository {

    DailyStepDao dailyStepDao;

    // Constructor
    public ReportRepository(Application application) {
        GymTrackerDB db = GymTrackerDB.getInstance(application);

        dailyStepDao = db.dailyStepDao();
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
        DailyStep dailyStep = null;
        return dailyStep = dailyStepDao.findDailyStep(date);
    }

}
