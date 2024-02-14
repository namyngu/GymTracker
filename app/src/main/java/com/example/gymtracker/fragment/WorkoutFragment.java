package com.example.gymtracker.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymtracker.R;
import com.example.gymtracker.adapters.WorkoutViewAdapter;
import com.example.gymtracker.databinding.FragmentProfileBinding;
import com.example.gymtracker.databinding.FragmentWorkoutBinding;
import com.example.gymtracker.entity.Workout;
import com.example.gymtracker.viewmodel.WorkoutViewModel;

import java.util.List;
import java.util.Objects;


public class WorkoutFragment extends Fragment {

    FragmentWorkoutBinding binding;
    private WorkoutViewModel workoutViewModel;
    private WorkoutViewAdapter adapter;
    private LiveData<List<Workout>> allWorkouts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWorkoutBinding.inflate(inflater, container, false);

        // Change actionbar Title
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Workout Routines");

        // Initialize View Model
        workoutViewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);
        allWorkouts = workoutViewModel.getAllWorkouts();

        // Set layout manager for recycler view
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Attach RecyclerView to adapter
        adapter = new WorkoutViewAdapter();
        binding.recyclerView.setAdapter(adapter);




        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}