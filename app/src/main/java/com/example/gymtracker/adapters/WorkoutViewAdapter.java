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
        List<TrainingPlan> currentTrainingPlans = new ArrayList<>();
        List<Exercise> currentExercises = new ArrayList<>();
        for (TrainingPlan tmp : trainingPlans) {
            if (tmp.getWorkoutId() == currentWorkout.getWorkoutId()) {
                currentTrainingPlans.add(tmp);
                currentExercises.add(tmp.getExercise(exercises));
            }
        }

        // Update UI for a single card
        holder.binding.textViewWorkoutName.setText(currentWorkout.getName());

        // Get first exercise and training plan
        if (currentExercises.size() == 0) {
            holder.binding.llRow1.setVisibility(View.GONE);
            holder.binding.llRow2.setVisibility(View.GONE);
            holder.binding.llRow3.setVisibility(View.GONE);
            holder.binding.textViewEllipsis.setVisibility(View.GONE);
            return;
        }

        Exercise exercise1 = currentExercises.get(0);
        TrainingPlan plan1 = findTrainingPlanFromExercise(exercise1);

        holder.binding.textViewExerciseName1.setText(exercise1.getName());
        holder.binding.textViewEquipment1.setText(exercise1.getEquipment());
        assert plan1 != null;
        String str1 = plan1.getSetNum() + "x" + plan1.getReps() ;
        holder.binding.textViewVolume1.setText(str1);

        // if there's only 1 exercise in a workout - hide exercise 2 and 3
        if (currentExercises.size() == 1) {
            holder.binding.llRow2.setVisibility(View.GONE);
            holder.binding.llRow3.setVisibility(View.GONE);
            holder.binding.textViewEllipsis.setVisibility(View.GONE);
            return;
        }

        // get 2nd exercise and training plan
        Exercise exercise2 = currentExercises.get(1);
        TrainingPlan plan2 = findTrainingPlanFromExercise(exercise2);

        // Display exercise 2
        assert plan2 != null;
        String str2 = plan2.getSetNum() + "x" + plan2.getReps() ;
        holder.binding.textViewExerciseName2.setText(exercise2.getName());
        holder.binding.textViewEquipment2.setText(exercise2.getEquipment());
        holder.binding.textViewVolume2.setText(str2);

        // if there's 2 exercises in a workout - hide exercise 3
        if (currentExercises.size() == 2){
            holder.binding.llRow3.setVisibility(View.GONE);
            holder.binding.textViewEllipsis.setVisibility(View.GONE);
            return;
        }

        // Hide ellipsis for 3 or less exercises.
        if (currentExercises.size() <= 3) {
            holder.binding.textViewEllipsis.setVisibility(View.GONE);
        }

        // get 2rd exercise and training plan
        Exercise exercise3 = currentExercises.get(2);
        TrainingPlan plan3 = findTrainingPlanFromExercise(exercise3);

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

    public TrainingPlan findTrainingPlanFromExercise(Exercise exercise) {
        TrainingPlan plan = null;
        for (TrainingPlan tmp : trainingPlans ) {
            if (tmp.getExerciseId() == exercise.getExerciseId()) {
                plan = tmp;
            }
        }
        return plan;
    }
}
