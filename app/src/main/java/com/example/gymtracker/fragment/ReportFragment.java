package com.example.gymtracker.fragment;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.gymtracker.R;
import com.example.gymtracker.databinding.FragmentReportBinding;
import com.example.gymtracker.entity.DailyStep;
import com.example.gymtracker.entity.Weight;
import com.example.gymtracker.viewmodel.ReportViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ReportFragment extends Fragment {

    FragmentReportBinding binding;
    ArrayList barArrayList;

    List<DailyStep> dailySteps = new ArrayList<>();
    List<Weight> weights = new ArrayList<>();
    ReportViewModel viewModel;
    private FirebaseAuth auth;
    private FirebaseUser user;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReportBinding.inflate(inflater, container, false);
        View view  = binding.getRoot();

        initParams();
        getData();
        initBarChart();



        return view;
    }

    private void getData() {
        barArrayList = new ArrayList();
        barArrayList.add(new BarEntry(2f,10));
        barArrayList.add(new BarEntry(3f,20));
        barArrayList.add(new BarEntry(4f,30));
        barArrayList.add(new BarEntry(5f,40));
        barArrayList.add(new BarEntry(6f,50));
    }

    private void initBarChart() {
        BarDataSet barDataSet = new BarDataSet(barArrayList, "Daily Steps");
        BarData barData = new BarData(barDataSet);
        binding.barChart.setData(barData);
        //Color bar data set
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //text color
        barDataSet.setValueTextColor(Color.BLACK);
        //setting text size
        barDataSet.setValueTextSize(16f);
        binding.barChart.getDescription().setEnabled(true);
    }

    private void initParams() {
        viewModel = new ViewModelProvider(requireActivity()).get(ReportViewModel.class);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        CompletableFuture<List<DailyStep>> stepsFuture = viewModel.getAllSteps(user.getUid());
        stepsFuture.thenAccept(result -> {
           dailySteps = result;
        });

        CompletableFuture<List<Weight>> weightsFuture = viewModel.getAllWeights(user.getUid());
        weightsFuture.thenAccept(result -> {
            weights = result;
        });
    }
}