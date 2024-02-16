package com.example.gymtracker.adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymtracker.databinding.ItemExerciseAddBinding;
import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.fragment.AddWorkoutFragment;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class AddWorkoutViewAdapter extends RecyclerView.Adapter<AddWorkoutViewAdapter.AddWorkoutViewHolder>{
    private List<AddWorkoutFragment.ExerciseTemplate> templates = new ArrayList<>();

    @NonNull
    @Override
    public AddWorkoutViewAdapter.AddWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemExerciseAddBinding binding = ItemExerciseAddBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AddWorkoutViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddWorkoutViewAdapter.AddWorkoutViewHolder holder, int position) {
        AddWorkoutFragment.ExerciseTemplate currentTemplate = templates.get(position);
        holder.binding.textViewExerciseName.setText(currentTemplate.getExercise().getName());
        holder.binding.textViewEquipment.setText(currentTemplate.getExercise().getEquipment());
        holder.binding.textViewMuscle.setText(currentTemplate.getExercise().getMuscle());
        String volume = currentTemplate.getSets() + "x" + currentTemplate.getReps();
        holder.binding.textViewVolume.setText(volume);
    }

    @Override
    public int getItemCount() {
        return templates.size();
    }

    public static class AddWorkoutViewHolder extends RecyclerView.ViewHolder {
        private ItemExerciseAddBinding binding;
        public View view;

        // Constructor
        public AddWorkoutViewHolder(ItemExerciseAddBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setTemplates(List<AddWorkoutFragment.ExerciseTemplate> templates) {
        this.templates = templates;
        notifyDataSetChanged();
    }
}
