package com.example.gymtracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.gymtracker.database.GymTrackerDB;
import com.example.gymtracker.entity.User;
import com.example.gymtracker.repository.UserRepository;

import java.util.concurrent.CompletableFuture;

public class ProfileViewModel extends AndroidViewModel {

    private UserRepository userRepo;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepository(application);
    }

    public CompletableFuture<User> findByIdFuture(final String userId) {
        return userRepo.findByIdFuture(userId);
    }

    public void update(User user) {
        userRepo.update(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }

}
