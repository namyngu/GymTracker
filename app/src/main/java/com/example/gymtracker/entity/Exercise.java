package com.example.gymtracker.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "exercise_table")
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    private long exerciseId;

    @SerializedName("name")
    private String name;

    @SerializedName("instructions")
    private String notes;

    @SerializedName("muscle")
    private String muscle;

    @SerializedName("equipment")
    private String equipment;

    @SerializedName("type")
    private String category;
    private boolean visibility;
    private String userId;

    @Ignore
    // Constructors
    public Exercise(String name, String notes, String muscle, String equipment, String userId) {
        this.name = name;
        this.notes = notes;
        this.muscle = muscle;
        this.equipment = equipment;
        this.category = "Other";
        this.visibility = true;
        this.userId = userId;
    }

    public Exercise(String name, String notes, String muscle, String equipment, String category, String userId) {
        this.name = name;
        this.notes = notes;
        this.muscle = muscle;
        this.equipment = equipment;
        this.category = category;
        this.visibility = true;
        this.userId = userId;
    }

    // Getter methods

    public String getUserId() {
        return userId;
    }
    public long getExerciseId() {
        return exerciseId;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public String getMuscle() {
        return muscle;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getCategory() {
        return category;
    }

    public boolean getVisibility() {
        return visibility;
    }

    // Setter methods
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " - " + equipment;
    }
}
