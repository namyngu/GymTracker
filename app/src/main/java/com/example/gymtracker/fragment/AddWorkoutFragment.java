package com.example.gymtracker.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.gymtracker.R;
import com.example.gymtracker.adapters.AddWorkoutViewAdapter;
import com.example.gymtracker.databinding.FragmentAddWorkoutBinding;
import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.entity.TrainingPlan;
import com.example.gymtracker.entity.Workout;
import com.example.gymtracker.viewmodel.WorkoutViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class AddWorkoutFragment extends Fragment {
    FragmentAddWorkoutBinding binding;
    WorkoutViewModel workoutViewModel;

    private AppCompatActivity activity = (AppCompatActivity) getActivity();
    private List<Exercise> addedExercises = new ArrayList<>();
    private List<ExerciseTemplate> templates = new ArrayList<>();
    private List<Exercise> yourExercises = new ArrayList<>();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddWorkoutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        workoutViewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);

        // Change actionbar Title
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Add Workout");

        CompletableFuture<List<Exercise>> exercisesCompletableFuture = workoutViewModel.getAllExercisesForAUser(user.getUid());
        exercisesCompletableFuture.thenApply(exercises -> {
            if (exercises == null) {
                Toast.makeText(activity, "You have no exercises!", Toast.LENGTH_SHORT).show();
            } else {
                yourExercises.addAll(exercises);
            }
            return exercises;
        }).thenAccept(result -> {
            //Setup adapter for spinner
            ArrayAdapter<Exercise> spinnerAdapter = new ArrayAdapter<Exercise>(
                    getContext(),
                    android.R.layout.simple_spinner_item,
                    yourExercises);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerExercise.setAdapter(spinnerAdapter);
        });

        // Set layout manager for recyclerview
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Handle add exercise button click event
        binding.buttonAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Error checks
                if (binding.spinnerExercise.getSelectedItem() == null) {
                    Toast.makeText(getContext(), "Please select an exercise", Toast.LENGTH_LONG).show();
                    return;
                }
                if (binding.editTextSets.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(), "Please set number of sets", Toast.LENGTH_LONG).show();
                    return;
                }
                if (binding.editTextReps.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(), "Please set number of reps", Toast.LENGTH_LONG).show();
                    return;
                }
                Exercise exercise = (Exercise) binding.spinnerExercise.getSelectedItem();
                yourExercises.remove(exercise); // A workout routine can only have one particular exercise.
                addedExercises.add(exercise);
                templates.add(new ExerciseTemplate(exercise, Integer.parseInt(binding.editTextSets.getText().toString()),
                        Integer.parseInt(binding.editTextReps.getText().toString()), binding.editTextNotes.getText().toString()));

                reApplyAdapters();
            }
        });

        binding.buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearExercises();
            }
        });

        return view;
    }

    public void getSelectedExercise(View v) {
        Exercise exercise = (Exercise) binding.spinnerExercise.getSelectedItem();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Hide Bottom Navigation Bar
        BottomNavigationView navBar = requireActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);

        // Show bottom navbar once we exit the screen
        NavController navController = Navigation.findNavController(requireView());      // This will only work once onCreateView method returns a view - otherwise null pointer exception.
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() != R.id.nav_add_workout_fragment) {
                    navBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Add exercise back to yourExercise
    // Remove exercise from addedExercises and templates
    // Re-apply adapters
    public void clearExercises() {
        yourExercises.addAll(addedExercises);
        addedExercises.clear();
        templates.clear();
        reApplyAdapters();
    }

    public void reApplyAdapters() {
        // Re-apply adapters - spinner and recyclerview
        ArrayAdapter<Exercise> spinnerAdapter = new ArrayAdapter<Exercise>(
                getContext(),
                android.R.layout.simple_spinner_item,
                yourExercises);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerExercise.setAdapter(spinnerAdapter);

        //Attach RecyclerView to adapter
        AddWorkoutViewAdapter recyclerViewAdapter = new AddWorkoutViewAdapter();
        binding.recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setTemplates(templates);
    }

    public void saveWorkout() {
        if (binding.editTextName.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Workout name cannot be empty", Toast.LENGTH_SHORT).show();
        }
        String name = binding.editTextName.getText().toString().trim();

        // Create Workout and TrainingPlan object to insert into db
        Workout workout = new Workout(name, user.getUid(), Calendar.getInstance().getTime());
        List<TrainingPlan> trainingPlans = new ArrayList<>();
        for (ExerciseTemplate tmp : templates) {
            TrainingPlan newPlan = new TrainingPlan(tmp.getSets(), tmp.getReps(), tmp.getNotes(), tmp.getExercise().getExerciseId());
            trainingPlans.add(newPlan);
        }
        // The TrainingPlanDao has methods to add both workout and a list of training plans.
        workoutViewModel.insert(workout, trainingPlans);

        Navigation.findNavController(getView()).navigate(R.id.navigate_to_WorkoutFragment);
    }

    //Template class to pass data into AddWorkoutViewAdapter
    public static class ExerciseTemplate {
        private Exercise exercise;
        private int reps;
        private int sets;
        private String notes;

        // Constructor
        public ExerciseTemplate() {}
        public ExerciseTemplate(Exercise exercise, int sets, int reps, String notes) {
            this.exercise = exercise;
            this.sets = sets;
            this.reps = reps;
            this.notes = notes;
        }
        public Exercise getExercise() {
            return exercise;
        }

        public int getReps() {
            return reps;
        }

        public int getSets() {
            return sets;
        }

        public String getNotes() {
            return notes;
        }

        public void setExercise(Exercise exercise) {
            this.exercise = exercise;
        }

        public void setReps(int reps) {
            this.reps = reps;
        }

        public void setSets(int sets) {
            this.sets = sets;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }
    }

    // Display save icon
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("onOptionsItemSelected","yes");
        if (item.getItemId() == R.id.save_exercise) {
            saveWorkout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}