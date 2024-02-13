package com.example.gymtracker.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.gymtracker.R;
import com.example.gymtracker.databinding.ActivityAddExerciseBinding;

import java.util.ArrayList;
import java.util.List;

public class AddExerciseActivity extends AppCompatActivity {
    ActivityAddExerciseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddExerciseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Add list of possible muscles for spinner
        ArrayAdapter<CharSequence> muscleSpinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.muscles_array,
                android.R.layout.simple_spinner_item
        );

        // Specify the layout to use when the list of choices appears.
        muscleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerMuscle.setAdapter(muscleSpinnerAdapter);


        //TODO: retrieve muscle group from spinner.
        binding.spinnerMuscle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // An item is selected. You can retrieve the selected item using
                // adapterView.getItemAtPosition(pos).
                String selectedMuscle = adapterView.getItemAtPosition(position).toString();

                Toast.makeText(adapterView.getContext(), "Muscle selected is" + selectedMuscle, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Default muscle group selected is "other"
                binding.spinnerMuscle.setSelection(15);
            }
        });

        // Show X in the top left corner and change title name
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Exercise");
    }

    private void saveExercise() {
        String name = binding.spinnerMuscle.getSelectedItem().toString();
        String notes = "";
        if (binding.editTextNotes.getText() != null) {
            notes = binding.editTextNotes.getText().toString().trim();
        }
        String muscle = binding.spinnerMuscle.getSelectedItem().toString();

        // Accept input, save exercise, insert exercise into database and close activity.


    }

    // Display save icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_exercise_menu, menu);
        return true;
    }

    // Save exercise when save menu icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_exercise) {
            saveExercise();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}