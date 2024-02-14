package com.example.gymtracker.fragment;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymtracker.R;
import com.example.gymtracker.adapters.ExerciseViewAdapter;
import com.example.gymtracker.databinding.FragmentExerciseBinding;
import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.viewmodel.ExerciseViewModel;
import com.example.gymtracker.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExerciseFragment extends Fragment {
    FragmentExerciseBinding binding;
    private SharedViewModel sharedViewModelModel;
    private ExerciseViewModel exerciseViewModel;
    private ExerciseViewAdapter adapter;
    private LiveData<List<Exercise>> allExercises;
    private List<Exercise> exerciseList;

    public ExerciseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExerciseBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Change actionbar Title
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Your Exercises");

        // Floating action button click event
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigate_to_addExerciseFragment);
            }
        });

        // Initialize view model
        exerciseViewModel = new ViewModelProvider(requireActivity()).get(ExerciseViewModel.class);
        allExercises = exerciseViewModel.getAllExercises();

        // set layout manager for recycler view
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Attach RecyclerView to adapter
        adapter = new ExerciseViewAdapter();
        binding.recyclerView.setAdapter(adapter);


        // We pass getViewLifecycleOwner instead of "this", because we want the lifecycle of the fragment view, not the fragment instance
        exerciseViewModel.getAllExercises().observe(getViewLifecycleOwner(), new Observer<List<Exercise>>() {
            @Override
            public void onChanged(@Nullable final List<Exercise> exercises) {
                // Update LiveData in response to changes from other fragments
                // Requires setting up SharedViewModel & SharedViewAdapter

                // Update RecyclerView adapter and display it again.
                adapter.setExercises(exercises);

            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}