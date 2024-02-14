package com.example.gymtracker.adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymtracker.databinding.ItemWorkoutBinding;
import com.example.gymtracker.entity.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutViewAdapter extends RecyclerView.Adapter<WorkoutViewAdapter.WorkoutViewHolder> {

    private List<Workout> workouts = new ArrayList<>();

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

        holder.binding.textViewWorkoutName.setText(currentWorkout.getName());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        // ViewBinding
        private ItemWorkoutBinding binding;
        public View view;

        public Workout currentWorkout;

        // Constructor
        public WorkoutViewHolder(ItemWorkoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
