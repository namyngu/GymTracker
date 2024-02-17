package com.example.gymtracker.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
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
import com.example.gymtracker.api.RetrofitClient;
import com.example.gymtracker.api.RetrofitInterface;
import com.example.gymtracker.databinding.FragmentSearchExerciseBinding;
import com.example.gymtracker.entity.Exercise;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchExerciseFragment extends Fragment {
    FragmentSearchExerciseBinding binding;
    private String keyword;
    private String filter;
    private RetrofitInterface retrofitInterface;
    private List<Exercise> exercises = new ArrayList<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchExerciseBinding.inflate(inflater,container,false);

        applyAdapters();

        // Change actionbar Title
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Search Exercises");

        retrofitInterface = RetrofitClient.getRetrofitService();
        binding.fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter = binding.spinnerFilter.getSelectedItem().toString().toLowerCase();
                keyword = binding.edittextKeyword.getText().toString().trim().toLowerCase();
                if (keyword.isEmpty()) {
                    Toast.makeText(activity, "Must enter keywords", Toast.LENGTH_SHORT).show();
                    return;
                }

                Call<List<Exercise>> callAsync = retrofitInterface.getExercises(filter,keyword);
                //makes an async request & invokes callback methods when the response returns
                callAsync.enqueue(new Callback<List<Exercise>>() {
                    @Override
                    public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                        if (response.isSuccessful()) {
                            exercises = response.body();

                            //TODO display exercises in recyclerview
                            Objects.requireNonNull(activity.getSupportActionBar()).setTitle(exercises.get(0).getName());
                        }
                        else {
                            Log.i("SearchExerciseFragment","Response failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Exercise>> call, Throwable t) {
                        Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("SearchExerciseFragment",t.getMessage());
                    }
                });
            }
        });







        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Hide Bottom Navigation Bar
        BottomNavigationView navBar = requireActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.INVISIBLE);

        // Show bottom navbar once we exit the screen
        NavController navController = Navigation.findNavController(requireView());      // This will only work once onCreateView method returns a view - otherwise null pointer exception.
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() != R.id.nav_search_exercise_fragment) {
                    navBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void setupActionBar() {
        // Change actionbar Title
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Add Workout");
    }

    public void applyAdapters() {
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.filter_array,      // Add list of possible muscles for spinner
                android.R.layout.simple_spinner_item
        );

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerFilter.setAdapter(spinnerAdapter);

    }

}