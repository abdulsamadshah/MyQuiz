package com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.MainActivity;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth auth;
    ProgressDialog dialog;
    String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Logging in...");
        //notification send

        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        } else {
//            Toast.makeText(this, "some error", Toast.LENGTH_SHORT).show();
        }

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = binding.email.getText().toString();
                pass = binding.password.getText().toString();
                dialog.show();


                if (email.equals("")) {
                    binding.email.setError("Name is Empty");
                } else if (pass.equals("")) {
                    binding.password.setError("Email is Empty");
                } else {
                    usersignin();
                }


            }
        });

        binding.signups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, Signup.class));
            }
        });


    }


    private void usersignin() {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                dialog.dismiss();
                if (task.isSuccessful()) {
                    startActivity(new Intent(login.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(login.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}