package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectandroid.models.User;
import com.example.projectandroid.repository.UserRepository;

public class RegisterActivity extends AppCompatActivity {

    EditText nameInput, emailInput, passwordInput;
    Button registerButton, goToLoginButton;
    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameInput = findViewById(R.id.usernameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        goToLoginButton = findViewById(R.id.goToRegisterButton);
        registerButton = findViewById(R.id.registerButton);

        userRepository = new UserRepository(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValidation();
            }
        });

        goToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to LoginActivity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setValidation() {
        // name invalid
        if (nameInput.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please write a name", Toast.LENGTH_SHORT).show();
        }
        else{
            // email invalid
            if (emailInput.getText().toString().isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Please write an email", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput.getText().toString()).matches()) {
                Toast.makeText(RegisterActivity.this, "Please write a valid email", Toast.LENGTH_SHORT).show();
            } else if(userRepository.getUserByEmail(emailInput.getText().toString()) != null) {
                Toast.makeText(RegisterActivity.this, "There is already an account with this email", Toast.LENGTH_SHORT).show();
            }
            else{
                // password invalid
                if (passwordInput.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please write a password", Toast.LENGTH_SHORT).show();
                } else if (passwordInput.getText().length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Password needs to have at least 6 characters", Toast.LENGTH_SHORT).show();
                } else  {
                    // adding user
                    if(userRepository.getUserByEmail(emailInput.getText().toString()) != null){
                        Toast.makeText(RegisterActivity.this, "There is already an account with this email", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        User user = new User(emailInput.getText().toString(), passwordInput.getText().toString(), nameInput.getText().toString());
                        userRepository.insertUser(user);
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }
        }

    }

}


