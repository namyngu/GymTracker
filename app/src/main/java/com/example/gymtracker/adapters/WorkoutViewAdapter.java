package com.example.gymtracker.adapters;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymtracker.databinding.ItemWorkoutBinding;
import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.entity.ExerciseLog;
import com.example.gymtracker.entity.Set;
import com.example.gymtracker.entity.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutViewAdapter extends RecyclerView.Adapter<WorkoutViewAdapter.WorkoutViewHolder> {

    private List<Workout> workouts = new ArrayList<>();
    private List<Exercise> exercises = new ArrayList<>();
    private List<Set> sets = new ArrayList<>();
    private List<ExerciseLog> exerciseLogs = new ArrayList<>();

    // View Holder contains the layout for each item in the list
    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWorkoutBinding binding = ItemWorkoutBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new WorkoutViewHolder(binding);
    }

    // Bind data to view
    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        Workout currentWorkout = workouts.get(position);

        // Get all sets for current workout
        List<Set> setsForCurrentWorkout = new ArrayList<>();
        for (Set tmp : sets) {
            if (tmp.getWorkoutId() == currentWorkout.getWorkoutId()) {
                setsForCurrentWorkout.add(tmp);
            }
        }

        if (setsForCurrentWorkout.size() == 0)
            Log.e("WorkoutViewAdapter", "all exercises in a workout must have sets");

        // Get all exerciseLogs and exercises for current workout
        List<ExerciseLog> exerciseLogsForCurrentWorkout = new ArrayList<>();
        List<Exercise> exercisesForCurrentWorkout = new ArrayList<>();
        for (ExerciseLog tmp : exerciseLogs) {
            // Find exercise logs for current workout
            if (tmp.getWorkoutId() == currentWorkout.getWorkoutId()) {
                exerciseLogsForCurrentWorkout.add(tmp);

                // Find exercises for current workout
                for (Exercise tmpExercise : exercises) {
                    if (tmpExercise.getExerciseId() == tmp.getExerciseId()) {
                        exercisesForCurrentWorkout.add(tmpExercise);
                    }
                }
            }
        }

        // Update UI for a single card
        holder.binding.textViewWorkoutName.setText(currentWorkout.getName());

        Exercise exercise1 = exercisesForCurrentWorkout.get(0);
        // get number of sets for exercise 1
        int setNum1 = 0;
        for (Set tmp : setsForCurrentWorkout) {
            if (tmp.getExerciseId() == exercise1.getExerciseId()) {
                setNum1++;
            }
        }
        holder.binding.textViewExerciseName1.setText(exercise1.getName());
        holder.binding.textViewEquipment1.setText(exercise1.getEquipment());
        holder.binding.textViewVolume1.setText(setNum1 + " sets");

        // if there's only 1 exercise in a workout
        if (exercisesForCurrentWorkout.size() == 1) {
            holder.binding.textViewExerciseName2.setVisibility(View.INVISIBLE);
            holder.binding.divider2.setVisibility(View.INVISIBLE);
            holder.binding.textViewEquipment2.setVisibility(View.INVISIBLE);
            holder.binding.textViewVolume2.setVisibility(View.INVISIBLE);

            holder.binding.textViewExerciseName3.setVisibility(View.INVISIBLE);
            holder.binding.divider3.setVisibility(View.INVISIBLE);
            holder.binding.textViewEquipment3.setVisibility(View.INVISIBLE);
            holder.binding.textViewVolume3.setVisibility(View.INVISIBLE);
            holder.binding.textViewEllipsis.setVisibility(View.INVISIBLE);
            return;
        }

        Exercise exercise2 = exercisesForCurrentWorkout.get(1);
        // get number of sets for exercise 2
        int setNum2 = 0;
        for (Set tmp : setsForCurrentWorkout) {
            if (tmp.getExerciseId() == exercise2.getExerciseId()) {
                setNum2++;
            }
        }
        holder.binding.textViewExerciseName2.setText(exercise2.getName());
        holder.binding.textViewEquipment2.setText(exercise2.getEquipment());
        holder.binding.textViewVolume2.setText(setNum2 + " sets");

        // if there's 2 exercises in a workout
        if (exercisesForCurrentWorkout.size() == 2){
            holder.binding.textViewExerciseName3.setVisibility(View.INVISIBLE);
            holder.binding.divider3.setVisibility(View.INVISIBLE);
            holder.binding.textViewEquipment3.setVisibility(View.INVISIBLE);
            holder.binding.textViewVolume3.setVisibility(View.INVISIBLE);
            holder.binding.textViewEllipsis.setVisibility(View.INVISIBLE);
            return;
        }

        Exercise exercise3 = exercisesForCurrentWorkout.get(2);
        // get number of sets for exercise 2
        int setNum3 = 0;
        for (Set tmp : setsForCurrentWorkout) {
            if (tmp.getExerciseId() == exercise3.getExerciseId()) {
                setNum3++;
            }
        }
        holder.binding.textViewExerciseName3.setText(exercise3.getName());
        holder.binding.textViewEquipment3.setText(exercise3.getEquipment());
        holder.binding.textViewVolume3.setText(setNum3 + " sets");

    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        // ViewBinding
        private ItemWorkoutBinding binding;
        public View view;

        // Constructor
        public WorkoutViewHolder(ItemWorkoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
        notifyDataSetChanged();
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
        notifyDataSetChanged();
    }

    public void setExerciseLog(List<ExerciseLog> exerciseLogs) {
        this.exerciseLogs = exerciseLogs;
        notifyDataSetChanged();
    }

}
