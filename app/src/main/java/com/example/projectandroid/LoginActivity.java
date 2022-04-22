package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectandroid.models.User;
import com.example.projectandroid.repository.UserRepository;

public class LoginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginButton, goToRegisterButton;
    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        goToRegisterButton = findViewById(R.id.goToRegisterButton);
        loginButton = findViewById(R.id.pressLoginButton);

        userRepository = new UserRepository(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValidation();
            }
        });

        goToRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setValidation() {

        // email invalid
        if (emailInput.getText().toString().isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please write an email", Toast.LENGTH_SHORT).show();
        }
        else{
            // password invalid
            if (passwordInput.getText().toString().isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please write a password", Toast.LENGTH_SHORT).show();
            } else {
                User user = userRepository.getUserByEmail(emailInput.getText().toString());
                if(user == null) {
                    Toast.makeText(LoginActivity.this, "No account with this email address", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!user.getPassword().equals(passwordInput.getText().toString())) {
                        Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    } else {
                        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                        sessionManagement.saveSession(user);
                        Intent intent = new Intent(LoginActivity.this, NewsFeedActivity.class);
                        startActivity(intent);
                    }
                }
            }
        }

    }
}
