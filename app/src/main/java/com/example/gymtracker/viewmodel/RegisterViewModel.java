package com.example.gymtracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.gymtracker.entity.User;
import com.example.gymtracker.repository.ProfileRepository;

import java.util.concurrent.CompletableFuture;

public class RegisterViewModel extends AndroidViewModel {

    private ProfileRepository userRepo;
    private User user;
    private String userId;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepo = new ProfileRepository(application);
    }

    public CompletableFuture<User> findByIdFuture(final String userId) {
        return userRepo.findByIdFuture(userId);
    }

    public void insert(User user) {
        userRepo.insert(user);
    }

    public void update(User user) {
        userRepo.update(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }

}
