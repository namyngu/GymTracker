package com.example.gymtracker.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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

import java.util.Objects;

public class AddExerciseFragment extends Fragment {
    FragmentAddExerciseBinding binding;
    ExerciseViewModel exerciseViewModel;

    int buttonId;

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
        // Handle Click events of Radio Buttons - RadioGroup doesn't work unless radio buttons are a direct child of it.
        binding.radioButtonBarbell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.radioButtonBarbell.setChecked(true);
                binding.radioButtonDumbbell.setChecked(false);
                binding.radioButtonBodyweight.setChecked(false);
                binding.radioButtonCable.setChecked(false);
                binding.radioButtonMedicineball.setChecked(false);
                binding.radioButtonBench.setChecked(false);
                binding.radioButtonHangingbar.setChecked(false);
                binding.radioButtonMachine.setChecked(false);
                binding.radioButtonOther.setChecked(false);

                buttonId = binding.radioButtonBarbell.getId();
            }
        });

        binding.radioButtonDumbbell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.radioButtonBarbell.setChecked(false);
                binding.radioButtonDumbbell.setChecked(true);
                binding.radioButtonBodyweight.setChecked(false);
                binding.radioButtonCable.setChecked(false);
                binding.radioButtonMedicineball.setChecked(false);
                binding.radioButtonBench.setChecked(false);
                binding.radioButtonHangingbar.setChecked(false);
                binding.radioButtonMachine.setChecked(false);
                binding.radioButtonOther.setChecked(false);

                buttonId = binding.radioButtonDumbbell.getId();
            }
        });

        binding.radioButtonBodyweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.radioButtonBarbell.setChecked(false);
                binding.radioButtonDumbbell.setChecked(false);
                binding.radioButtonBodyweight.setChecked(true);
                binding.radioButtonCable.setChecked(false);
                binding.radioButtonMedicineball.setChecked(false);
                binding.radioButtonBench.setChecked(false);
                binding.radioButtonHangingbar.setChecked(false);
                binding.radioButtonMachine.setChecked(false);
                binding.radioButtonOther.setChecked(false);

                buttonId = binding.radioButtonBodyweight.getId();
            }
        });

        binding.radioButtonCable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.radioButtonBarbell.setChecked(false);
                binding.radioButtonDumbbell.setChecked(false);
                binding.radioButtonBodyweight.setChecked(false);
                binding.radioButtonCable.setChecked(true);
                binding.radioButtonMedicineball.setChecked(false);
                binding.radioButtonBench.setChecked(false);
                binding.radioButtonHangingbar.setChecked(false);
                binding.radioButtonMachine.setChecked(false);
                binding.radioButtonOther.setChecked(false);

                buttonId = binding.radioButtonCable.getId();
            }
        });

        binding.radioButtonMedicineball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.radioButtonBarbell.setChecked(false);
                binding.radioButtonDumbbell.setChecked(false);
                binding.radioButtonBodyweight.setChecked(false);
                binding.radioButtonCable.setChecked(false);
                binding.radioButtonMedicineball.setChecked(true);
                binding.radioButtonBench.setChecked(false);
                binding.radioButtonHangingbar.setChecked(false);
                binding.radioButtonMachine.setChecked(false);
                binding.radioButtonOther.setChecked(false);

                buttonId = binding.radioButtonMedicineball.getId();
            }
        });

        binding.radioButtonBench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.radioButtonBarbell.setChecked(false);
                binding.radioButtonDumbbell.setChecked(false);
                binding.radioButtonBodyweight.setChecked(false);
                binding.radioButtonCable.setChecked(false);
                binding.radioButtonMedicineball.setChecked(false);
                binding.radioButtonBench.setChecked(true);
                binding.radioButtonHangingbar.setChecked(false);
                binding.radioButtonMachine.setChecked(false);
                binding.radioButtonOther.setChecked(false);

                buttonId = binding.radioButtonBench.getId();
            }
        });

        binding.radioButtonHangingbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.radioButtonBarbell.setChecked(false);
                binding.radioButtonDumbbell.setChecked(false);
                binding.radioButtonBodyweight.setChecked(false);
                binding.radioButtonCable.setChecked(false);
                binding.radioButtonMedicineball.setChecked(false);
                binding.radioButtonBench.setChecked(false);
                binding.radioButtonHangingbar.setChecked(true);
                binding.radioButtonMachine.setChecked(false);
                binding.radioButtonOther.setChecked(false);

                buttonId = binding.radioButtonHangingbar.getId();
            }
        });

        binding.radioButtonMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.radioButtonBarbell.setChecked(false);
                binding.radioButtonDumbbell.setChecked(false);
                binding.radioButtonBodyweight.setChecked(false);
                binding.radioButtonCable.setChecked(false);
                binding.radioButtonMedicineball.setChecked(false);
                binding.radioButtonBench.setChecked(false);
                binding.radioButtonHangingbar.setChecked(false);
                binding.radioButtonMachine.setChecked(true);
                binding.radioButtonOther.setChecked(false);

                buttonId = binding.radioButtonMachine.getId();
            }
        });

        binding.radioButtonOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.radioButtonBarbell.setChecked(false);
                binding.radioButtonDumbbell.setChecked(false);
                binding.radioButtonBodyweight.setChecked(false);
                binding.radioButtonCable.setChecked(false);
                binding.radioButtonMedicineball.setChecked(false);
                binding.radioButtonBench.setChecked(false);
                binding.radioButtonHangingbar.setChecked(false);
                binding.radioButtonMachine.setChecked(false);
                binding.radioButtonOther.setChecked(true);

                buttonId = binding.radioButtonOther.getId();
            }
        });

        // Show X in the top left corner and change title name
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);
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

        String equipment;
        if (buttonId == binding.radioButtonBarbell.getId()) {
            equipment = "Barbell";
        }
        else if (buttonId == binding.radioButtonDumbbell.getId()) {
            equipment = "Dumbbell";
        }
        else if (buttonId == binding.radioButtonBodyweight.getId()) {
            equipment = "Bodyweight";
        }
        else if (buttonId == binding.radioButtonCable.getId()) {
            equipment = "Cable";
        }
        else if (buttonId == binding.radioButtonMedicineball.getId()) {
            equipment = "Medicine Ball";
        }
        else if (buttonId == binding.radioButtonBench.getId()) {
            equipment = "Bench";
        }
        else if (buttonId == binding.radioButtonHangingbar.getId()) {
            equipment = "Hanging Bar";
        }
        else if (buttonId == binding.radioButtonMachine.getId()) {
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
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setTitle("Your Exercises");
        Navigation.findNavController(getView()).navigate(R.id.navigate_to_exercise_fragment);

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