package com.example.gymtracker.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymtracker.R;
import com.example.gymtracker.activity.LoginActivity;
import com.example.gymtracker.adapters.WorkoutViewAdapter;
import com.example.gymtracker.databinding.FragmentProfileBinding;
import com.example.gymtracker.databinding.FragmentWorkoutBinding;
import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.entity.ExerciseLog;
import com.example.gymtracker.entity.Set;
import com.example.gymtracker.entity.Workout;
import com.example.gymtracker.repository.TestData;
import com.example.gymtracker.viewmodel.WorkoutViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.Objects;


public class WorkoutFragment extends Fragment {

    FragmentWorkoutBinding binding;
    private WorkoutViewModel workoutViewModel;
    private WorkoutViewAdapter adapter;
    private LiveData<List<Workout>> allUserWorkouts;
    private LiveData<List<Exercise>> allUserExercises;
    private LiveData<List<Set>> allUserSets;

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWorkoutBinding.inflate(inflater, container, false);

        // get firebase auth instance and get current user
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String userId = user.getUid();

        // Change actionbar Title
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Workout Routines");



        // Initialize View Model
        workoutViewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);
        allUserWorkouts = workoutViewModel.getAllWorkoutsForAUser(userId);
        allUserExercises = workoutViewModel.getAllExercisesForAUser(userId);
        allUserSets = workoutViewModel.getAllSetsForAUser(userId);

        // Set layout manager for recycler view
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Attach RecyclerView to adapter
        adapter = new WorkoutViewAdapter();
        binding.recyclerView.setAdapter(adapter);

        allUserWorkouts.observe(getViewLifecycleOwner(), new Observer<List<Workout>>() {
            @Override
            public void onChanged(List<Workout> workouts) {
                adapter.setWorkouts(workouts);
            }
        });

        allUserExercises.observe(getViewLifecycleOwner(), new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                adapter.setExercises(exercises);
            }
        });

        allUserSets.observe(getViewLifecycleOwner(), new Observer<List<Set>>() {
            @Override
            public void onChanged(List<Set> sets) {
                adapter.setSets(sets);
            }
        });

        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void goToLoginActivity(){
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}