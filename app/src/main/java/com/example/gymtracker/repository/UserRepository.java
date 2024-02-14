package com.example.gymtracker.repository;

import android.app.Application;

import com.example.gymtracker.database.GymTrackerDB;
import com.example.gymtracker.entity.User;
import com.example.gymtracker.interfaces.UserDao;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class UserRepository {

    private UserDao userDao;
    private User user;

    // Constructor
    public UserRepository(Application application) {
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

    public void update(User user) {
        GymTrackerDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.update(user);
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

    public CompletableFuture<User> findByIdFuture(final String userId) {
        return CompletableFuture.supplyAsync(new Supplier<User>() {
            @Override
            public User get() {
                return userDao.findById(userId);
            }
        }, GymTrackerDB.databaseWriteExecutor);
    }
}
