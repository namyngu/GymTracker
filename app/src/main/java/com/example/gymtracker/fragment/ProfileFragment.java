package com.example.gymtracker.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymtracker.Login;
import com.example.gymtracker.R;
import com.example.gymtracker.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Viewbinding for fragments
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void goToLoginActivity(){
        Intent intent = new Intent(getContext(), Login.class);
        startActivity(intent);
    }

}