package com.example.projectandroid;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectandroid.models.News;
import com.example.projectandroid.models.NewsImage;
import com.example.projectandroid.models.User;
import com.example.projectandroid.repository.NewsRepository;
import com.example.projectandroid.repository.NewsImageRepository;
import com.example.projectandroid.repository.UserRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;

public class NewNewsActivity extends AppCompatActivity {

    EditText titleInput, contentInput;
    Button addNewsButton;
    UserRepository userRepository;
    NewsRepository newsRepository;
    NewsImageRepository newsImageRepository;
    User user;
    ImageView imageView;
    private static final int PICK_IMAGE_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_news);

        titleInput = findViewById(R.id.titleInput);
        contentInput = findViewById(R.id.contentInput);
        addNewsButton = findViewById(R.id.addNewsButton);
        imageView = findViewById(R.id.imageNews);

        userRepository = new UserRepository(this);
        newsRepository = new NewsRepository(this);
        newsImageRepository = new NewsImageRepository(this);

        SessionManagement sessionManagement = new SessionManagement(NewNewsActivity.this);
        String emailUser = sessionManagement.getSession();

        user = userRepository.getUserByEmail(emailUser);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);

                }            }
        });

        addNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValidation();
            }
        });

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

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

    public void setValidation() {
        // title invalid
        if (titleInput.getText().toString().isEmpty()) {
            Toast.makeText(NewNewsActivity.this, "Please write a title", Toast.LENGTH_SHORT).show();
        }
        else{
            // content invalid
            if (contentInput.getText().toString().isEmpty()) {
                Toast.makeText(NewNewsActivity.this, "Please write a content", Toast.LENGTH_SHORT).show();
            } else if (contentInput.getText().length() < 20) {
                Toast.makeText(NewNewsActivity.this, "Content needs to have at least 20 characters", Toast.LENGTH_SHORT).show();
            } else {
                News news = new News(user.getId(), titleInput.getText().toString(),contentInput.getText().toString(), new Date());
                int newsId = newsRepository.insertNews(news);
                sendNotificationsWithDeepLinks(newsId);
                System.out.println("merge");
                if(hasImage(imageView, "not_empty_image")){
                    Bitmap image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    NewsImage newsImage = new NewsImage(newsId, image);
                    newsImageRepository.insertPhoto(newsImage);
                }
                Toast.makeText(NewNewsActivity.this, "News added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewNewsActivity.this, NewsFeedActivity.class);
                startActivity(intent);
            }
        }

    }

    public void sendNotificationsWithDeepLinks(int newsId){
        NotificationGenerator.openActivityNotification(this, newsId);
    }

    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(bitmap);
            imageView.setTag("not_empty_image");
        }
    }

    private boolean hasImage(@NonNull ImageView view, String tag) {

        String previousTag = (String) view.getTag();
        return previousTag.equalsIgnoreCase(tag);


    }
}
