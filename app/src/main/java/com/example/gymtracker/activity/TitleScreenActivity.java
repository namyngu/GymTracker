package com.example.gymtracker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gymtracker.databinding.ActivityTitleScreenBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TitleScreenActivity extends AppCompatActivity {
    private ActivityTitleScreenBinding binding;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTitleScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // get firebase auth instance and get current user
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        // if user exists login to Home screen
        if (user != null) {
            goToMainActivity();
            finish();
        }

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginActivity();
                finish();
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegisterActivity();
                finish();
            }
        });

    }

    public void goToLoginActivity() {
        Intent intent = new Intent (getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void goToRegisterActivity() {
        Intent intent = new Intent (getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    public void goToMainActivity() {
        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}