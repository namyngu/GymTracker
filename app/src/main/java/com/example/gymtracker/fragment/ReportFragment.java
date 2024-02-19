package com.example.gymtracker.fragment;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ReportFragment extends Fragment {

    FragmentReportBinding binding;
    ArrayList barArrayList;
    private ArrayList<String> xLabels = new ArrayList<>();

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
        getDataLast7Days();
        initBarChart();



        return view;
    }

    private void getDataLast7Days() {
        barArrayList = new ArrayList();
        int count = 0;


        // Get the last 7 daily steps (equivalent to 7 days)
        for (DailyStep step : dailySteps) {
            if (dailySteps.size() - count <= 7) {
                barArrayList.add(new BarEntry(count - 1, step.getSteps()));     // first index at 0 for x-axis.
                // get labels for x-axis (Day-Month)
                String[] str = step.getDate().split("-");
                String dayMonth = str[0] + "-" + str[1];
                xLabels.add(dayMonth);
            }
            count++;
        }
    }

    private void initBarChart() {
        BarDataSet barDataSet = new BarDataSet(barArrayList, "Daily Steps");
        BarData barData = new BarData(barDataSet);
        binding.barChart.setData(barData);
        XAxis xAxis = binding.barChart.getXAxis();     // get XAxis object
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xLabels));      // Label x-axis from the xLabels array
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);

        binding.barChart.setFitBars(true);  //x-axis fit all bars.
        //Color bar data set
        barDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        //text color
        barDataSet.setValueTextColor(Color.BLACK);
        //setting text size
        barDataSet.setValueTextSize(16f);
        binding.barChart.getDescription().setEnabled(true);

        // Hide background lines
        xAxis.setDrawGridLines(false);
        binding.barChart.getAxisLeft().setDrawGridLines(false);
        binding.barChart.getAxisRight().setDrawGridLines(false);
        // Hideright Y axis
        binding.barChart.getAxisRight().setEnabled(false);

        binding.barChart.invalidate(); //refresh
    }

    private void initParams() {
        viewModel = new ViewModelProvider(requireActivity()).get(ReportViewModel.class);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        CompletableFuture<List<DailyStep>> stepsFuture = viewModel.getAllSteps(user.getUid());
        try {
            dailySteps = stepsFuture.get();
        }
        catch (Exception e) {
            Log.e("ReportFragment","Failed to get daily steps");
        }


        CompletableFuture<List<Weight>> weightsFuture = viewModel.getAllWeights(user.getUid());
        try{
            weights = weightsFuture.get();
        }
        catch (Exception e) {
            Log.e("ReportFragment","Failed to get weights");
        }
    }
}