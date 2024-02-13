//package com.example.gymtracker.activity;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.widget.ArrayAdapter;
//import android.widget.Toast;
//
//import com.example.gymtracker.R;
//import com.example.gymtracker.databinding.ActivityAddExerciseBinding;
//import com.example.gymtracker.entity.Exercise;
//import com.example.gymtracker.viewmodel.ExerciseViewModel;
//
//public class AddExerciseActivity extends AppCompatActivity {
//    ActivityAddExerciseBinding binding;
//    private ExerciseViewModel exerciseViewModel;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityAddExerciseBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        // Add list of possible muscles for spinner
//        ArrayAdapter<CharSequence> muscleSpinnerAdapter = ArrayAdapter.createFromResource(
//                this,
//                R.array.muscles_array,
//                android.R.layout.simple_spinner_item
//        );
//
//        // Specify the layout to use when the list of choices appears.
//        muscleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.spinnerMuscle.setAdapter(muscleSpinnerAdapter);
//
//        // Default radio button checked
//        binding.radioGroupEquipment.check(R.id.radio_button_other);
//
////        //TODO: retrieve muscle group from spinner.
////        binding.spinnerMuscle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////            @Override
////            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
////                // An item is selected. You can retrieve the selected item using
////                // adapterView.getItemAtPosition(pos).
////                String selectedMuscle = adapterView.getItemAtPosition(position).toString();
////
////                Toast.makeText(adapterView.getContext(), "Muscle selected is" + selectedMuscle, Toast.LENGTH_LONG).show();
////
////            }
////
////            @Override
////            public void onNothingSelected(AdapterView<?> adapterView) {
////                // Default muscle group selected is "other"
////                binding.spinnerMuscle.setSelection(15);
////            }
////        });
//
//        // Show X in the top left corner and change title name
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
//        setTitle("Add Exercise");
//    }
//
//    private void saveExercise() {
//        String name = binding.editTextName.getText().toString().trim();
//        String notes = "";
//        if (binding.editTextNotes.getText() != null) {
//            notes = binding.editTextNotes.getText().toString().trim();
//        }
//        String muscle = binding.spinnerMuscle.getSelectedItem().toString();
//
//        String equipment = "";
//        int buttonId = binding.radioGroupEquipment.getCheckedRadioButtonId();
//        if (buttonId == R.id.radio_button_barbell) {
//            equipment = "Barbell";
//        }
//        else if (buttonId == R.id.radio_button_dumbbell) {
//            equipment = "Dumbbell";
//        }
//        else if (buttonId == R.id.radio_button_bodyweight) {
//            equipment = "Bodyweight";
//        }
//        else if (buttonId == R.id.radio_button_stabilityball) {
//            equipment = "Stability Ball";
//        }
//        else if (buttonId == R.id.radio_button_medicineball) {
//            equipment = "Medicine Ball";
//        }
//        else if (buttonId == R.id.radio_button_bench) {
//            equipment = "Bench";
//        }
//        else if (buttonId == R.id.radio_button_hangingbar) {
//            equipment = "Hanging Bar";
//        }
//        else if (buttonId == R.id.radio_button_machine) {
//            equipment = "Machine";
//        }
//        else {
//            equipment = "Other";
//        }
//
//        // Validate input
//        if (name.isEmpty()) {
//            Toast.makeText(this, "Please insert name", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Accept input, save exercise, insert exercise into database and close activity.
//        Exercise exercise = new Exercise(name, notes, muscle, equipment);
//        exerciseViewModel.insert(exercise);
//        finish();
//
//    }
//
//    // Display save icon
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.save_menu, menu);
//        return true;
//    }
//
//    // Save exercise when save menu icon is clicked
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.save_exercise) {
//            saveExercise();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}