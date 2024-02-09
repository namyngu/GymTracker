package com.example.gymtracker.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_table")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    private int exerciseId;
    private String name;
    private String instructions;
    private String muscle;
    private int equipmentId;
    private String difficulty;
    private int categoryId;

    public Exercise(String name, String instructions, String muscle, int equipmentId, String difficulty, int typeId) {
        this.name = name;
        this.instructions = instructions;
        this.muscle = muscle;
        this.equipmentId = equipmentId;
        this.difficulty = difficulty;
        this.categoryId = categoryId;
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

    public int getEquipmentId() {
        return equipmentId;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getCategoryId() {
        return categoryId;
    }

    // Setter methods
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
