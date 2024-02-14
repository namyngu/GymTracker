package com.example.gymtracker.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gymtracker.databinding.ActivityRegisterBinding;
import com.example.gymtracker.entity.User;
import com.example.gymtracker.viewmodel.RegisterViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        RegisterViewModel viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar.setVisibility(View.VISIBLE);

                String email, password, ageStr, weightstr;
                int age, weight;
                email = String.valueOf(binding.email.getText());
                password = String.valueOf(binding.password.getText());
                ageStr = binding.tiAge.getText().toString();
                weightstr = binding.tiWeight.getText().toString();

                // Check if email and password is empty
                if (email.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_LONG).show();
                    return;
                }

                if (ageStr.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Enter age", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    age = Integer.parseInt(ageStr);
                }

                if (weightstr.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Enter weight", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    weight = Integer.parseInt(weightstr);
                }

                // Create user
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Details: ", "createUserWithEmail:success");

                                    // Add user to Room database
                                    FirebaseUser currentUser = mAuth.getCurrentUser();

                                    //Create user object
                                    User user = new User(currentUser.getUid(),
                                            binding.tiDisplayName.getText().toString().trim(),
                                            age,
                                            weight,
                                            "operator");
                                    // Add user to database
                                    viewModel.insert(user);

                                    binding.progressBar.setVisibility(View.GONE);
                                    Toast.makeText(RegisterActivity.this, "Register Success",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "ERROR: Registration failed",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

        // Return to title screen
        binding.tvBackToTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TitleScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}