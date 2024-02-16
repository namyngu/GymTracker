package com.example.gymtracker.fragment;

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
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.gymtracker.adapters.AddWorkoutViewAdapter;
import com.example.gymtracker.databinding.FragmentAddWorkoutBinding;
import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.viewmodel.WorkoutViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class AddWorkoutFragment extends Fragment {
    FragmentAddWorkoutBinding binding;
    WorkoutViewModel workoutViewModel;
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

        List<Exercise> addedExercises = new ArrayList<>();
        List<ExerciseTemplate> templates = new ArrayList<>();
        List<Exercise> yourExercises = new ArrayList<>();

//        yourExercises.observe(getViewLifecycleOwner(), new Observer<List<Exercise>>() {
//            @Override
//            public void onChanged(List<Exercise> exercises) {
//                //Setup adapter for spinner
//                ArrayAdapter<Exercise> spinnerAdapter = new ArrayAdapter<Exercise>(
//                        getContext(),
//                        android.R.layout.simple_spinner_item,
//                        exercises);
//                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                binding.spinnerExercise.setAdapter(spinnerAdapter);
//            }
//        });


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

        // Grab Exercise when button is clicked
        binding.buttonAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Error checks
                if (binding.spinnerExercise.getSelectedItem() == null) {
                    Toast.makeText(getContext(), "Please select an exercise", Toast.LENGTH_LONG).show();
                    return;
                }
                if (binding.editTextSets.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please set number of sets", Toast.LENGTH_LONG).show();
                    return;
                }
                if (binding.editTextReps.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please set number of reps", Toast.LENGTH_LONG).show();
                    return;
                }
                Exercise exercise = (Exercise) binding.spinnerExercise.getSelectedItem();
                addedExercises.add(exercise);
                yourExercises.remove(exercise); // A workout routine can only have one particular exercise.
                templates.add(new ExerciseTemplate(exercise, Integer.parseInt(binding.editTextSets.getText().toString()),
                        Integer.parseInt(binding.editTextReps.getText().toString()), binding.editTextNotes.getText().toString()));



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
        });

        // Set layout manager for recyclerview
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        // TODO: Handle delete button click event



        return view;
    }

    public void getSelectedExercise(View v) {
        Exercise exercise = (Exercise) binding.spinnerExercise.getSelectedItem();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
}