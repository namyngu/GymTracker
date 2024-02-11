package com.example.gymtracker.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "exercise_table")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    private int exerciseId;
    private String name;
    private String instructions;
    private String muscle;
    private String equipment;
    private String category;

    public Exercise(String name, String instructions, String muscle, String equipment, String category) {
        this.name = name;
        this.instructions = instructions;
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

    public String getInstructions() {
        return instructions;
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
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    // TODO: delete this method later - used to populate list for testing
    public static List<Exercise> createExercises() {
        List<Exercise> exercises = new ArrayList<Exercise>();
        exercises.add(new Exercise("Squats", "Lift Bar", "Glutes", "Barbell", "other"));

        return exercises;
    }
}
