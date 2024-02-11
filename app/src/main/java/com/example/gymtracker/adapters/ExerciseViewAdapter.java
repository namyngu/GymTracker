package com.example.gymtracker.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymtracker.databinding.ExerciseItemBinding;
import com.example.gymtracker.entity.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseViewAdapter extends RecyclerView.Adapter<ExerciseViewAdapter.ExerciseViewHolder> {

    private List<Exercise> exercises = new ArrayList<>();

    public ExerciseViewAdapter(List<Exercise> exercises) {
        this.exercises = exercises;
    }


    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ExerciseItemBinding binding = ExerciseItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ExerciseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise currentExercise = exercises.get(position);
        holder.binding.textViewExerciseName.setText(currentExercise.getName());
        //TODO: convert equipment ID to its name or change to String
        holder.binding.textViewEquipment.setText(currentExercise.getEquipmentId());
        holder.binding.textViewMuscle.setText(currentExercise.getMuscle());
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        // ViewBinding
        private ExerciseItemBinding binding;

        // Constructor - ExerciseItemBinding binding is the card itself (single item)
        public ExerciseViewHolder(ExerciseItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}