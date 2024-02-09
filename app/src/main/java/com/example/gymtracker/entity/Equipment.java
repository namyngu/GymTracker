package com.example.gymtracker.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "equipment_table")
public class Equipment {
    @PrimaryKey(autoGenerate = true)
    private int equipmentId;
    private String equipment;

    // Constructor
    public Equipment(String equipment) {
        this.equipment = equipment;
    }

    // Getter methods
    public int getEquipmentId() {
        return equipmentId;
    }

    public String getEquipment() {
        return equipment;
    }

    // Setter methods
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
