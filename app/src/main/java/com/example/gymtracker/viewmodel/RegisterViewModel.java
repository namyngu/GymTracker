package com.example.gymtracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.gymtracker.entity.User;
import com.example.gymtracker.entity.Weight;
import com.example.gymtracker.repository.ProfileRepository;

import java.util.concurrent.CompletableFuture;

public class RegisterViewModel extends AndroidViewModel {

    private ProfileRepository profileRepo;
    private User user;
    private String userId;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        profileRepo = new ProfileRepository(application);
    }

    public CompletableFuture<User> findByIdFuture(final String userId) {
        return profileRepo.findUserByIdFuture(userId);
    }

    public void insert(User user) {
        profileRepo.insert(user);
    }
    public void insert(Weight weight) {
        profileRepo.insert(weight);
    }

    public void update(User user) {
        profileRepo.update(user);
    }

    public void delete(User user) {
        profileRepo.delete(user);
    }

}
