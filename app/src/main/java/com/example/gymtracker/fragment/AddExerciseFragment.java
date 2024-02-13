package com.example.gymtracker.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
import com.example.gymtracker.databinding.FragmentAddExerciseBinding;
import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.viewmodel.ExerciseViewModel;

public class AddExerciseFragment extends Fragment {
    FragmentAddExerciseBinding binding;
    ExerciseViewModel exerciseViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddExerciseBinding.inflate(inflater, container, false);

        exerciseViewModel = new ViewModelProvider(requireActivity()).get(ExerciseViewModel.class);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.muscles_array,      // Add list of possible muscles for spinner
                android.R.layout.simple_spinner_item
        );

        // Specify the layout to use when the list of choices appears.
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerMuscle.setAdapter(spinnerAdapter);

        // Check other radio button by default
        binding.radioGroupEquipment.check(R.id.radio_button_other);

        // Show X in the top left corner and change title name
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        activity.getSupportActionBar().setTitle("Add Exercise");

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void saveExercise() {
        String name = binding.editTextName.getText().toString().trim();
        String notes = "";
        if (binding.editTextNotes.getText() != null) {
            notes = binding.editTextNotes.getText().toString().trim();
        }
        String muscle = binding.spinnerMuscle.getSelectedItem().toString();

        String equipment = "";
        int buttonId = binding.radioGroupEquipment.getCheckedRadioButtonId();
        if (buttonId == R.id.radio_button_barbell) {
            equipment = "Barbell";
        }
        else if (buttonId == R.id.radio_button_dumbbell) {
            equipment = "Dumbbell";
        }
        else if (buttonId == R.id.radio_button_bodyweight) {
            equipment = "Bodyweight";
        }
        else if (buttonId == R.id.radio_button_stabilityball) {
            equipment = "Cable";
        }
        else if (buttonId == R.id.radio_button_medicineball) {
            equipment = "Medicine Ball";
        }
        else if (buttonId == R.id.radio_button_bench) {
            equipment = "Bench";
        }
        else if (buttonId == R.id.radio_button_hangingbar) {
            equipment = "Hanging Bar";
        }
        else if (buttonId == R.id.radio_button_machine) {
            equipment = "Machine";
        }
        else {
            equipment = "Other";
        }

        // Validate input
        if (name.isEmpty()) {
            Toast.makeText(getContext(), "Please insert name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Accept input, save exercise, insert exercise into database and close activity.
        Exercise exercise = new Exercise(name, notes, muscle, equipment);
        exerciseViewModel.insert(exercise);

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
            saveExercise();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}