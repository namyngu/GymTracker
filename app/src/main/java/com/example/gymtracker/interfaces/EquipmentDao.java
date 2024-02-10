package com.example.gymtracker.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.gymtracker.entity.Equipment;
import com.example.gymtracker.entity.EquipmentWithExercises;

import java.util.List;

@Dao
public interface EquipmentDao {
    @Insert
    public void insert (Equipment equipment);

    @Update
    public void update (Equipment equipment);

    @Delete
    public void delete (Equipment equipment);

    @Query("SELECT * FROM equipment_table")
    public LiveData<List<Equipment>> getAllEquipments();

    @Transaction
    @Query("SELECT * FROM exercise_category_table")
    public LiveData<List<EquipmentWithExercises>> getEquipmentWithExercises();
}
