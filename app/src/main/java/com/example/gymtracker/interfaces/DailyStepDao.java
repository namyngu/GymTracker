package com.example.gymtracker.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gymtracker.entity.DailyStep;

import java.util.List;

@Dao
public interface DailyStepDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(DailyStep steps);

    @Update
    public void update(DailyStep steps);

    @Delete
    public void delete (DailyStep steps);

    @Query("SELECT * FROM daily_step_table WHERE daily_step_table.userId =:userId")
    public List<DailyStep> getAllDailySteps(String userId);

    // Date format should be dd-MMM-yyyy
    @Query("SELECT * FROM daily_step_table WHERE date == :date")
    public DailyStep findDailyStep(String date);

}
