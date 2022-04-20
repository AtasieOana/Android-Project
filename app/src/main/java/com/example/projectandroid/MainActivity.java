package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //LogIn button
        Button button;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LogIn button
        button = findViewById(R.id.buttonLogIn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openLoginPage();
            }
        });


        //NewsList button
        Button button2;

        button2 = findViewById(R.id.NewsList);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openNewsList();
            }
        });

        // add initial news in database
        AsyncTaskNews task = new AsyncTaskNews(this);
        task.execute();
        System.out.println("main activity");

    }

    //button LogIn
    public void openLoginPage() {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    //button NewsList
    public void openNewsList() {
        Intent intent = new Intent(this, NewsList.class);
        startActivity(intent);
    }

}