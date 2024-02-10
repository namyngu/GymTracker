package com.example.gymtracker.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.gymtracker.entity.ExerciseCategory;
import com.example.gymtracker.entity.ExerciseCategoryWithExercises;
import com.example.gymtracker.entity.UserWithWorkouts;

import java.util.List;

@Dao
public interface ExerciseCategoryDao {

    @Insert
    public void insert (ExerciseCategory exerciseCategory);

    @Update
    public void update (ExerciseCategory exerciseCategory);

    @Delete
    public void delete (ExerciseCategory exerciseCategory);

    @Query("SELECT * FROM exercise_category_table")
    public LiveData<List<ExerciseCategory>> getAllExerciseCategories();

    @Transaction
    @Query("SELECT * FROM exercise_category_table")
    public LiveData<List<ExerciseCategoryWithExercises>> getExerciseCategoryWithExercises();
}
