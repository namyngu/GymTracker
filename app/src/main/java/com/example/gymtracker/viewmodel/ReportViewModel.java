package com.example.gymtracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.gymtracker.entity.DailyStep;
import com.example.gymtracker.entity.Weight;
import com.example.gymtracker.repository.ReportRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ReportViewModel extends AndroidViewModel {

    private ReportRepository reportRepo;

    public ReportViewModel(@NonNull Application application) {
        super(application);
        reportRepo = new ReportRepository(application);
    }

    public CompletableFuture<List<DailyStep>> getAllSteps (String userId) {
        return reportRepo.getAllDailySteps(userId);
    }

    public CompletableFuture<List<Weight>> getAllWeights (String userId) {
        return reportRepo.getAllWeights(userId);
    }

}
