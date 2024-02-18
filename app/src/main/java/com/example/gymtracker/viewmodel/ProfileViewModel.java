package com.example.gymtracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.gymtracker.entity.DailyStep;
import com.example.gymtracker.entity.User;
import com.example.gymtracker.entity.Weight;
import com.example.gymtracker.repository.ProfileRepository;

import java.util.concurrent.CompletableFuture;

public class ProfileViewModel extends AndroidViewModel {

    private ProfileRepository profileRepo;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        profileRepo = new ProfileRepository(application);
    }

    public CompletableFuture<DailyStep> findDailyStep (String date) {
        return profileRepo.findDailyStep(date);
    }

    public CompletableFuture<User> findByIdFuture(final String userId) {
        return profileRepo.findUserByIdFuture(userId);
    }

    public void insert(User user) {
        profileRepo.insert(user);
    }

    public void insert(DailyStep dailyStep) {
        profileRepo.insert(dailyStep);
    }
    public void insert(Weight weight) {
        profileRepo.insert(weight);
    }

    public void update(DailyStep dailyStep) {
        profileRepo.update(dailyStep);
    }

    public void update(User user) {
        profileRepo.update(user);
    }

    public void delete(User user) {
        profileRepo.delete(user);
    }

    public void delete(DailyStep dailyStep) {
        profileRepo.delete(dailyStep);
    }

}
