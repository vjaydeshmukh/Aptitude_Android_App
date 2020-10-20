package com.example.aptitudetestquiz;
import android.content.Intent;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class instructionpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.click);
        setContentView(R.layout.activity_instructionpage);


        Button buttonStartQuiz = findViewById(R.id.start);

        Intent intent = getIntent();

        final String Domain_name = intent.getStringExtra("Domain_name");
        final String level_type = intent.getStringExtra("level_type");

        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(instructionpage.this,questionspage.class);
                intent.putExtra("Domain_name", Domain_name);
                intent.putExtra("level_type", level_type);
                startActivity(intent);

            }
        });

    }

}