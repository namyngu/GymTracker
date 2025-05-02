package com.example.gymtracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymtracker.R;
import com.example.gymtracker.databinding.ItemExerciseBinding;
import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.fragment.SearchExerciseFragment;
import com.example.gymtracker.fragment.SearchExerciseFragmentDirections;
import com.example.gymtracker.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class SearchExerciseViewAdapter extends RecyclerView.Adapter<SearchExerciseViewAdapter.SearchExerciseViewHolder> {

    private List<Exercise> exercises = new ArrayList<>();
    private ItemClickListener listener;

    public SearchExerciseViewAdapter(ItemClickListener listener) {
        this.listener = listener;
    }

    // ViewHolder contains the layout for each item in the list
    @NonNull
    @Override
    public SearchExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemExerciseBinding binding =
                ItemExerciseBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false);

        return new SearchExerciseViewHolder(binding);
    }

    // Bind data to view
    @Override
    public void onBindViewHolder(@NonNull SearchExerciseViewHolder holder, int position) {
        Exercise currentExercise = exercises.get(position);
        holder.itemExerciseBinding.textViewExerciseName.setText(currentExercise.getName());
        holder.itemExerciseBinding.textViewEquipment.setText(currentExercise.getEquipment());
        holder.itemExerciseBinding.textViewMuscle.setText(currentExercise.getMuscle());

        holder.itemExerciseBinding.exerciseContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(currentExercise, holder.itemView);
            }
        });
    }

    @Override
    public int getItemCount() {

        return exercises.size();
    }

    // Remember the ViewHolder class holds each item in the RecyclerView
    public static class SearchExerciseViewHolder extends RecyclerView.ViewHolder {
        // ViewBinding
        private ItemExerciseBinding itemExerciseBinding;

        // Constructor - ExerciseItemBinding binding is the card itself (single item)
        public SearchExerciseViewHolder(ItemExerciseBinding binding) {
            super(binding.getRoot());
            this.itemExerciseBinding = binding;


        }
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }
}
