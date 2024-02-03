package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gymtracker.databinding.ActivityMainBinding;
import com.example.gymtracker.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // get firebase auth instance and get current user
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        // if user doesn't exist return to login screen otherwise display user email.
        if (user == null) {
            goToLoginActivity();
            finish();
        }
        else {
            binding.tvTitle.setText(user.getDisplayName());
        }


        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                goToLoginActivity();
                finish();
            }
        });
    }


    public void goToLoginActivity(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}