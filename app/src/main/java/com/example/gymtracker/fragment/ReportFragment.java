package com.example.gymtracker.fragment;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.gymtracker.R;
import com.example.gymtracker.databinding.FragmentReportBinding;


public class ReportFragment extends Fragment {

    FragmentReportBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReportBinding.inflate(inflater, container, false);
        View view  = binding.getRoot();



        // Inflate the layout for this fragment
        return view;
    }
}