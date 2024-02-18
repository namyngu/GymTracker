package com.example.gymtracker.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.gymtracker.entity.Weight;

import java.util.List;

@Dao
public interface WeightDao {
    @Insert
    public void insert(Weight weight);

    @Update
    public void update(Weight weight);

    @Delete
    public void delete (Weight weight);

    @Query("SELECT * FROM weight_table WHERE weight_table.userId =:userId")
    public List<Weight> getAllWeights(String userId);
}
