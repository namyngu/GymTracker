package com.example.gymtracker.interfaces;

import android.view.View;

import com.example.gymtracker.entity.Exercise;

public interface ItemClickListener {
    void onItemClicked(Exercise exercise, View view);
}
