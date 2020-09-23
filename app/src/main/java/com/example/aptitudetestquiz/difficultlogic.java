package com.example.aptitudetestquiz;
import android.content.Intent;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class difficultlogic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.click);
        setContentView(R.layout.activity_difficultlogic);


        Button buttonStartQuiz = findViewById(R.id.start);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                startQuiz();
            }
        });
    }

    private void startQuiz() {

        Intent intent = new Intent(difficultlogic.this, difficultlogic1.class);
        startActivity(intent);
    }
    public void clickback(View view)
    {
        Intent intent1=new Intent(difficultlogic.this,level2.class);
        startActivity(intent1);
    }
}