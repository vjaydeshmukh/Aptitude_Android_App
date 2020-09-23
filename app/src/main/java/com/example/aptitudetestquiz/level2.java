package com.example.aptitudetestquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class level2 extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);
        final MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.click);
        Button button_normal=findViewById(R.id.button1);
        Button button_difficult=findViewById(R.id.button2);


        Toast.makeText(this, "WELCOME TO QUIZ TEST", Toast.LENGTH_SHORT).show();
        button_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                start_normal_logic();
            }
        });
        button_difficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                start_difficult_logic();
            }
        });

        Button back1=findViewById(R.id.back1);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent  intent_back1=new Intent(level2.this,MainActivity.class);
                startActivity(intent_back1);
            }
        });


    }
    private void start_normal_logic()
    {

        Intent intent=new Intent(level2.this,normallogic.class);
        startActivity(intent);
    }
    private void start_difficult_logic()
    {
        Intent intent1=new Intent(level2.this,difficultlogic.class);
        startActivity(intent1);
    }
}
