package com.example.gymtracker.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gymtracker.entity.Set;

import java.util.List;

@Dao
public interface SetDao {

    @Insert
    public void insert (Set set);

    @Update
    public void update(Set set);

    @Delete
    public void delete(Set set);

    @Query("SELECT * FROM set_table ORDER BY logId")
    public LiveData<List<Set>> getAllSets();
}
