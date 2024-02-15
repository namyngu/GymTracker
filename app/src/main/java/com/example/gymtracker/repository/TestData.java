package com.example.gymtracker.repository;

import com.example.gymtracker.entity.ExerciseLog;
import com.example.gymtracker.entity.Set;
import com.example.gymtracker.entity.Workout;

import java.util.Calendar;
import java.util.Date;

public class TestData {
    private Workout workout = new Workout("TestWorkout", "hhTW0ykOR0hdJf4Wms6YdWcPwEh1",Calendar.getInstance().getTime());
    private Set set;
    private ExerciseLog exerciseLog;

    public TestData() {
        set = new Set(1, workout.getWorkoutId(), 1, 8, 100, "This is a test data");

    }

    public Workout getWorkout() {
        return workout;
    }

    public Set getSet() {
        return set;
    }

    public ExerciseLog getExerciseLog() {
        return exerciseLog;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public void setExerciseLog(ExerciseLog exerciseLog) {
        this.exerciseLog = exerciseLog;
    }
}
