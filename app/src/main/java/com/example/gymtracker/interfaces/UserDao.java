package com.example.gymtracker.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.gymtracker.entity.User;
import com.example.gymtracker.entity.UserWithWorkouts;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user_table ORDER BY userId ASC")
    public List<User> getAllUsers();


    @Transaction
    @Query("SELECT * FROM user_table")
    public List<UserWithWorkouts> getUserWithWorkouts();
}
