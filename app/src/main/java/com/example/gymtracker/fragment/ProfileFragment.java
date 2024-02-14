package com.example.gymtracker.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymtracker.activity.LoginActivity;
import com.example.gymtracker.databinding.FragmentProfileBinding;
import com.example.gymtracker.entity.User;
import com.example.gymtracker.viewmodel.ProfileViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


public class ProfileFragment extends Fragment {
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    FragmentProfileBinding binding;
    ProfileViewModel viewModel;
    User user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Viewbinding for fragments
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);

        // get firebase auth instance and get current user
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        CompletableFuture<User> userCompletableFuture = viewModel.findByIdFuture(firebaseUser.getUid());

        try {
            user = userCompletableFuture.get();
            binding.textViewDisplayname.setText(user.getDisplayName());
        }
        catch (Exception e){
            Log.e("ERROR: ", "Could not find user");
        }

        return binding.getRoot();
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void goToLoginActivity(){
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

}