package com.example.gymtracker.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymtracker.databinding.ItemWorkoutBinding;
import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.entity.TrainingPlan;
import com.example.gymtracker.entity.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutViewAdapter extends RecyclerView.Adapter<WorkoutViewAdapter.WorkoutViewHolder> {

    private List<Workout> workouts = new ArrayList<>();
    private List<Exercise> exercises = new ArrayList<>();
    private List<TrainingPlan> trainingPlans = new ArrayList<>();

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

        // Get all training plans and exercises for current workout
        List<TrainingPlan> trainingPlansForCurrentWorkout = new ArrayList<>();
        List<Exercise> exercisesForCurrentWorkout = new ArrayList<>();
        for (TrainingPlan tmp : trainingPlans) {
            if (tmp.getWorkoutId() == currentWorkout.getWorkoutId()) {
                trainingPlansForCurrentWorkout.add(tmp);
            }
            // Get all exercises for current workout
            for (Exercise tmpExercise : exercises) {
                if (tmpExercise.getExerciseId() == tmp.getExerciseId()) {
                    exercisesForCurrentWorkout.add(tmpExercise);
                }
            }
        }

        // Update UI for a single card
        holder.binding.textViewWorkoutName.setText(currentWorkout.getName());

        // Get first exercise and training plan
        Exercise exercise1 = exercisesForCurrentWorkout.get(0);
        TrainingPlan plan1 = null;
        for (TrainingPlan tmp : trainingPlans) {
            if (exercise1.getExerciseId() == tmp.getExerciseId()) {
                plan1 = tmp;
            }
        }

        holder.binding.textViewExerciseName1.setText(exercise1.getName());
        holder.binding.textViewEquipment1.setText(exercise1.getEquipment());
        assert plan1 != null;
        String str1 = plan1.getSetNum() + "x" + plan1.getReps() ;
        holder.binding.textViewVolume1.setText(str1);

        // if there's only 1 exercise in a workout - turn hide exercise 2 and 3
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

        // get 2nd exercise and training plan
        Exercise exercise2 = exercisesForCurrentWorkout.get(1);
        TrainingPlan plan2 = null;
        for (TrainingPlan tmp : trainingPlans) {
            if (exercise2.getExerciseId() == tmp.getExerciseId()) {
                plan2 = tmp;
            }
        }

        // Display exercise 2
        assert plan2 != null;
        String str2 = plan2.getSetNum() + "x" + plan2.getReps() ;
        holder.binding.textViewExerciseName2.setText(exercise2.getName());
        holder.binding.textViewEquipment2.setText(exercise2.getEquipment());
        holder.binding.textViewVolume2.setText(str2);

        // if there's 2 exercises in a workout - hide exercise 3
        if (exercisesForCurrentWorkout.size() == 2){
            holder.binding.textViewExerciseName3.setVisibility(View.INVISIBLE);
            holder.binding.divider3.setVisibility(View.INVISIBLE);
            holder.binding.textViewEquipment3.setVisibility(View.INVISIBLE);
            holder.binding.textViewVolume3.setVisibility(View.INVISIBLE);
            holder.binding.textViewEllipsis.setVisibility(View.INVISIBLE);
            return;
        }

        // get 2rd exercise and training plan
        Exercise exercise3 = exercisesForCurrentWorkout.get(2);
        TrainingPlan plan3 = null;
        for (TrainingPlan tmp : trainingPlans) {
            if (exercise3.getExerciseId() == tmp.getExerciseId()) {
                plan3 = tmp;
            }
        }

        // Display exercise 3
        assert plan3 != null;
        String str3 = plan3.getSetNum() + "x" + plan3.getReps() ;
        holder.binding.textViewExerciseName3.setText(exercise3.getName());
        holder.binding.textViewEquipment3.setText(exercise3.getEquipment());
        holder.binding.textViewVolume3.setText(str3);
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

    public void setTrainingPlans(List<TrainingPlan> trainingPlans) {
        this.trainingPlans = trainingPlans;
        notifyDataSetChanged();
    }
}
