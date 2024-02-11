package com.example.gymtracker.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymtracker.R;
import com.example.gymtracker.adapters.SharedViewAdapter;
import com.example.gymtracker.databinding.FragmentExerciseBinding;
import com.example.gymtracker.entity.Exercise;
import com.example.gymtracker.viewmodel.SharedViewModel;

import java.util.List;

public class ExerciseFragment extends Fragment {
    FragmentExerciseBinding binding;
    private SharedViewModel sharedViewModelModel;
    private SharedViewAdapter adapter;
    private List<Exercise> exercises;

    public ExerciseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExerciseBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // set layout manager
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        // Attach RecyclerView to adapter
        adapter = new SharedViewAdapter();
        binding.recyclerView.setAdapter(adapter);


        sharedViewModelModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);

        // We pass getViewLifecycleOwner instead of this, because we want the lifecycle of the fragment view not the fragment instance
        sharedViewModelModel.getExercise().observe(getViewLifecycleOwner(), new Observer<Exercise>() {
            @Override
            public void onChanged(Exercise exercise) {

            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}