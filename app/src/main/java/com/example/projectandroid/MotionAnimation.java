package com.example.projectandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.projectandroid.NewsFeedActivity;
import com.example.projectandroid.R;
import com.example.projectandroid.newsApi.AsyncTaskNews;
import com.example.projectandroid.repository.NewsRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MotionAnimation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.motion_layout);


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(3).setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.feed:
                        startActivity(new Intent(getApplicationContext(),NewsFeedActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.logout:
                        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
                        sessionManagement.removeSession();
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.add:
                        startActivity(new Intent(getApplicationContext(),NewNewsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

}
