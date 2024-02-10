package com.example.gymtracker.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gymtracker.entity.DailyStep;

import java.util.List;

@Dao
public interface DailyStepDao {

    @Insert
    public void insert(DailyStep steps);

    @Update
    public void update(DailyStep steps);

    @Delete
    public void delete (DailyStep steps);

    @Query("SELECT * FROM daily_step_table ORDER BY userId")
    public LiveData<List<DailyStep>> getAllDailySteps();

}
