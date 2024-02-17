package com.example.gymtracker.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymtracker.R;
import com.example.gymtracker.activity.LoginActivity;
import com.example.gymtracker.adapters.WorkoutViewAdapter;
import com.example.gymtracker.databinding.FragmentWorkoutBinding;
import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.entity.TrainingPlan;
import com.example.gymtracker.entity.Workout;
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
    private LiveData<List<TrainingPlan>> allUserTrainingPlans;

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


        //TODO: wrap this in completable future
        // Initialize View Model
        workoutViewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);
        allUserWorkouts = workoutViewModel.getAllWorkoutsForAUser(userId);
        allUserExercises = workoutViewModel.getLiveDataExercisesForAUser(userId);
        allUserTrainingPlans = workoutViewModel.getAllTrainingPlans(userId);

        // Set layout manager for recycler view
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Attach RecyclerView to adapter
        adapter = new WorkoutViewAdapter();
        binding.recyclerView.setAdapter(adapter);

        // Floating action button click event
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigate_to_addWorkoutFragment);
            }
        });

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

        allUserTrainingPlans.observe(getViewLifecycleOwner(), new Observer<List<TrainingPlan>>() {
            @Override
            public void onChanged(List<TrainingPlan> trainingPlans) {
                adapter.setTrainingPlans(trainingPlans);
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