package com.example.gymtracker.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "exercise_table")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    private int exerciseId;
    private String name;
    private String notes;
    private String muscle;
    private String equipment;
    private String category;


    @Ignore
    // Constructors
    public Exercise(String name, String notes, String muscle, String equipment) {
        this.name = name;
        this.notes = notes;
        this.muscle = muscle;
        this.equipment = equipment;
        this.category = "Other";
    }

    public Exercise(String name, String notes, String muscle, String equipment, String category) {
        this.name = name;
        this.notes = notes;
        this.muscle = muscle;
        this.equipment = equipment;
        this.category = category;
    }

    // Getter methods
    public int getExerciseId() {
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

    // Setter methods
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    // TODO: delete this method later - used to populate list for testing
    public static List<Exercise> createExercises() {
        List<Exercise> exercises = new ArrayList<Exercise>();
        exercises.add(new Exercise("Squats", "Lift Bar", "Glutes", "Barbell", "other"));
        exercises.add(new Exercise("Bench Press", "Lift Bar", "Chest", "Barbell", "other"));

        return exercises;
    }
}
