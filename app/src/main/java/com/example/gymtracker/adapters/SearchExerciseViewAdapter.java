package com.example.gymtracker.adapters;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymtracker.databinding.ItemExerciseBinding;
import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.interfaces.SelectListener;

import java.util.ArrayList;
import java.util.List;

public class SearchExerciseViewAdapter extends RecyclerView.Adapter<SearchExerciseViewAdapter.SearchExerciseViewHolder> {

    private List<Exercise> exercises = new ArrayList<>();
    private SelectListener listener;

    public SearchExerciseViewAdapter(SelectListener listener) {
        this.listener = listener;
    }

    // ViewHolder contains the layout for each item in the list
    @NonNull
    @Override
    public SearchExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemExerciseBinding binding = ItemExerciseBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new SearchExerciseViewHolder(binding);
    }

    // Bind data to view
    @Override
    public void onBindViewHolder(@NonNull SearchExerciseViewHolder holder, int position) {
        Exercise currentExercise = exercises.get(position);
        holder.binding.textViewExerciseName.setText(currentExercise.getName());
        holder.binding.textViewEquipment.setText(currentExercise.getEquipment());
        holder.binding.textViewMuscle.setText(currentExercise.getMuscle());

        holder.binding.exerciseContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(currentExercise);
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
        private ItemExerciseBinding binding;
        public View view;
        public Exercise currentExercise;

        // Constructor - ExerciseItemBinding binding is the card itself (single item)
        public SearchExerciseViewHolder(ItemExerciseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

//        // Handle click events for every card
//        public SearchExerciseViewHolder(View v) {
//            super(v);
//            view = v;
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // item is clicked
//                    Navigation.findNavController(view).navigate(R.id.action_nav_search_exercise_fragment_to_nav_exercise_fragment);
//                }
//            });
//        }
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }
}
