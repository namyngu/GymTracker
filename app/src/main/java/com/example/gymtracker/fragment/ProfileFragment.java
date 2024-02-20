package com.example.gymtracker.fragment;

import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.registerReceiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gymtracker.R;
import com.example.gymtracker.activity.LoginActivity;
import com.example.gymtracker.databinding.FragmentProfileBinding;
import com.example.gymtracker.entity.DailyStep;
import com.example.gymtracker.entity.User;
import com.example.gymtracker.entity.Weight;
import com.example.gymtracker.viewmodel.ProfileViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


public class ProfileFragment extends Fragment implements SensorEventListener{

    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    FragmentProfileBinding binding;
    ProfileViewModel viewModel;
    User user;

    private BroadcastReceiver endOfDayReceiver;
    private static final int PERMISSION_REQUEST_BODY_SENSORS = 1001;
    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private int stepCount = 0;
    private boolean isPaused = false;
    private long timePaused = 0;
    private float stepLengthInMeters = 0.762f;
    private long startTime;
    private int stepCountTarget = 6000;

//    private Handler timerHandler = new Handler();

//    private Runnable timerRunnable = new Runnable() {
//        @Override
//        public void run() {
//
//            long milis = System.currentTimeMillis() - startTime;
//            int seconds = (int)(milis/1000);
//            int min = seconds / 60;
//
//
//
//            binding.textviewTime.setText(String.format(Locale.getDefault(), "Time: %02d:%02d", min, seconds));
//            timerHandler.postDelayed(this, 1000);
//        }
//    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Viewbinding for fragments
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initializeParams();
        getPermissions();


        startTime = System.currentTimeMillis();

        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        binding.progressBar.setMax(stepCountTarget);
        binding.textviewStepCount.setText("Steps: " + stepCount);

        if (stepCounterSensor == null) {
            binding.textviewStepCount.setText("Step counter not found");
        }

        binding.btnUpdateWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strWeight = binding.weight.getText().toString().trim();
                if (strWeight.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter your weight", Toast.LENGTH_SHORT).show();
                    return;
                }

                float weight = Float.parseFloat(strWeight);
                if (weight <= 0) {
                    Toast.makeText(getContext(), "Weight must be greater than 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveWeight(weight);
                Toast.makeText(getContext(), "Your weight is saved", Toast.LENGTH_SHORT).show();
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stepCounterSensor == null) {
                    Toast.makeText(getContext(), "Step sensor not detected - saving current step count", Toast.LENGTH_LONG).show();
                    saveDailySteps(stepCount);
                    return;
                }
                saveDailySteps(stepCount);
                Toast.makeText(getContext(), "Steps saved", Toast.LENGTH_SHORT).show();
            }
        });

        endOfDayReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                saveDailySteps(stepCount);
                stepCount = 0;
            }
        };
        registerReceiver(getContext(),endOfDayReceiver, new IntentFilter(Intent.ACTION_DATE_CHANGED),ContextCompat.RECEIVER_NOT_EXPORTED);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                goToLoginActivity();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        if (stepCounterSensor != null) {
            sensorManager.registerListener( this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);

//            timerHandler.postDelayed(timerRunnable,0);
        }
        else{
            Toast.makeText(getContext(), "Step sensor not available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (stepCounterSensor != null) {
            sensorManager.unregisterListener(this);
//            timerHandler.removeCallbacks(timerRunnable);
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

        if (stepCounterSensor != null) {
            sensorManager.unregisterListener(this);
//            timerHandler.removeCallbacks(timerRunnable);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            getContext().unregisterReceiver(endOfDayReceiver);
        }
        catch (Exception exception) {
            Log.i("ProfileFragment","Receiver was not registered to unregister - everything is fine");
        }

    }

    public void initializeParams() {
        //Initialize view Model
        viewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);

        // get firebase auth instance and get current user
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();


        CompletableFuture<User> userCompletableFuture = viewModel.findByIdFuture(firebaseUser.getUid());
        userCompletableFuture.thenAccept(result -> {
            user = result;
        });

        // Change actionbar Title
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Your Profile");
    }

    public void getPermissions(){

        // GET PERMISSION
        if(ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            // Ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 19);
        }

        // GET PERMISSION
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.BODY_SENSORS)
                != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.BODY_SENSORS}, PERMISSION_REQUEST_BODY_SENSORS);
        }
    }

    private void goToLoginActivity(){
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            stepCount = (int) sensorEvent.values[0];
            String str = "Step Count : " + stepCount;
            binding.textviewStepCount.setText(str);

            binding.progressBar.setProgress(stepCount);

            if (stepCount >= stepCountTarget) {
                binding.textViewStepGoal.setText("Congrats, daily Step goals achieved!");
            }

            float distanceInKm = stepCount * stepLengthInMeters / 1000;
            binding.textviewDistance.setText(String.format(Locale.getDefault(), "Distance: %.2f km", distanceInKm));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    // Method to retrieve steps for the day
    public DailyStep getDailySteps(String date) {

        CompletableFuture<DailyStep> dailyStepCompletableFuture = viewModel.findDailyStep(date);
        dailyStepCompletableFuture.thenApply(dailyStep -> {
            if (dailyStep == null) {
                Toast.makeText(getActivity(), "No daily steps found!", Toast.LENGTH_LONG).show();
                return null;
            }
            else {
                return dailyStep;
            }
        });
        Toast.makeText(getActivity(), "This shouldn't happen, no daily steps found!", Toast.LENGTH_SHORT).show();
        return null;
    }

    // Method to detect end of day and save steps
    private void detectEndOfDayAndSaveSteps() {
        // TODO: Implement logic to detect end of day, using AlarmManager and  BroadcastReceiver



        String currentDate = getCurrentDate();

        // When end of day is detected, save steps
        DailyStep dailySteps = getDailySteps(currentDate); // Get total steps for the day
        saveDailySteps(dailySteps.getSteps());

    }

    // Method to save steps each day
    public void saveDailySteps(int steps) {

        // Get current date - dd-MMM-yyyy
        String currentDate = getCurrentDate();

        DailyStep dailyStep = new DailyStep(user.getUserId(), steps, currentDate);

        //Check if dailySteps today already exists
        CompletableFuture<DailyStep> checkStepsFuture = viewModel.findDailyStep(currentDate);
        checkStepsFuture.thenApply(checkSteps -> {
            if (checkSteps != null) {
                Log.i("ProfileFragment","Daily Steps already exist update steps");
                DailyStep newStep = new DailyStep(user.getUserId(), dailyStep.getSteps(), currentDate);
                viewModel.update(newStep);
            }
            else {
                viewModel.insert(dailyStep);
            }
            return true;
        });

        stepCount = 0;
    }

    public void saveWeight (float weight) {
        String currentDate = getCurrentDate();
        Weight tmpWeight = new Weight(user.getUserId(), weight, currentDate);
        viewModel.insert(tmpWeight);
    }

    //Returns the current date in dd-MMM-yyyy
    public String getCurrentDate() {
        // Get current date - dd-MMM-yyyy
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        sdf.setTimeZone(TimeZone.getDefault());
        String currentDate = sdf.format(Calendar.getInstance().getTime());

        return currentDate;
    }
}