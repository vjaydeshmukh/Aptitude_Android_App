package com.example.aptitudetestquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.aptitudetestquiz.questionspage.EXTRA_SCORE;


public class score extends AppCompatActivity {

    Button anothertest;
   Button solutions;
   TextView printscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        final MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.click);

        anothertest=(Button)findViewById(R.id.anothertest);
        printscore=(TextView)findViewById(R.id.printscore);
        anothertest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(score.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Intent intent1 = getIntent();
        String str = intent1.getStringExtra(EXTRA_SCORE);
        printscore.setText(str+" / 10 ");

    }
}
