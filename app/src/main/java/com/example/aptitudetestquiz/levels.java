package com.example.aptitudetestquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class levels extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.click);
        Button button_normal = findViewById(R.id.button1);
        Button button_difficult = findViewById(R.id.button2);

        Intent intent = getIntent();
        final String str = intent.getStringExtra("Domain_name");

        Toast.makeText(this, "WELCOME TO QUIZ TEST", Toast.LENGTH_SHORT).show();

        button_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent1 = new Intent(levels.this, instructionpage.class);
                String level_type = "normal";
                intent1.putExtra("Domain_name", str);
                intent1.putExtra("level_type", level_type);
                startActivity(intent1);

            }
        });


        button_difficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent2 = new Intent(levels.this, instructionpage.class);
                String level_type = "difficult";
                intent2.putExtra("Domain_name", str);
                intent2.putExtra("level_type", level_type);
                startActivity(intent2);

            }
        });
    }
}
