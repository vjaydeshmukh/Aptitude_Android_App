package com.example.aptitudetestquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView datetime;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    MediaPlayer mysong;
    boolean doubleBackToExistPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.click);
        mysong=MediaPlayer.create(getApplicationContext(),R.raw.study_music);
        mysong.setVolume(10,10);
        mysong.start();
        mysong.setLooping(true);
        Button login_button = findViewById(R.id.login);
        Button signup_button = findViewById(R.id.signup);
        datetime = (TextView) findViewById(R.id.datetime);

        Button database = findViewById(R.id.Database);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("EEE, d MMM, yyyy        hh:mm aaa");
        date = dateFormat.format(new Date());
        datetime.setText(date);


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent1 = new Intent(MainActivity.this,LoginActivity.class );
                startActivity(intent1);
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent2 = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent2);
            }
        });

        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent intent = new Intent(MainActivity.this , AndroidDatabaseManager.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(doubleBackToExistPressedOnce) {
            finishAffinity();
            super.onBackPressed();
            return;
        }

        this.doubleBackToExistPressedOnce = true;
        Toast.makeText(this,"Please click BACK again to exist" , Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExistPressedOnce = false;
            }
        },2000);
    }

}
