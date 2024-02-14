package com.example.gymtracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymtracker.databinding.ItemExerciseBinding;
import com.example.gymtracker.entity.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseViewAdapter extends RecyclerView.Adapter<ExerciseViewAdapter.ExerciseViewHolder> {

    private List<Exercise> exercises = new ArrayList<>();

    public ExerciseViewAdapter() {

    }

    public ExerciseViewAdapter(List<Exercise> exercises) {
        this.exercises = exercises;
    }


    // ViewHolder contains the layout for each item in the list
    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemExerciseBinding binding = ItemExerciseBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ExerciseViewHolder(binding);
    }

    // Bind data to view
    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise currentExercise = exercises.get(position);
        holder.binding.textViewExerciseName.setText(currentExercise.getName());
        holder.binding.textViewEquipment.setText(currentExercise.getEquipment());
        holder.binding.textViewMuscle.setText(currentExercise.getMuscle());
    }

    @Override
    public int getItemCount() {

        return exercises.size();
    }

    // Remember the ViewHolder class holds each item in the RecyclerView
    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        // ViewBinding
        private ItemExerciseBinding binding;
        public View view;
        public Exercise currentExercise;

        // Constructor - ExerciseItemBinding binding is the card itself (single item)
        public ExerciseViewHolder(ItemExerciseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        // Handle click events for every card
        public ExerciseViewHolder(View v) {
            super(v);
            view = v;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // item is clicked
                    //TODO: navigate to an exercise page to update exercises and delete them.
                }
            });
        }
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }
}
