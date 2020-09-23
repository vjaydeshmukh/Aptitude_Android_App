package com.example.aptitudetestquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {


    ImageButton button_quants;
    ImageButton button_logic;
    ImageButton button_puzzle;
    ImageButton button_verbal;
    MediaPlayer mysong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.click);
        mysong=MediaPlayer.create(getApplicationContext(),R.raw.harry_potter);
        mysong.setVolume(10,20); 
        mysong.start();
        mysong.setLooping(true);
        button_quants=findViewById(R.id.button_quants);
        button_logic=findViewById(R.id.button_logic);
        button_puzzle=findViewById(R.id.button_puzzle);
        button_verbal=findViewById(R.id.button_verbal);

        button_quants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent1=new Intent(MainActivity.this,level1.class);
                startActivity(intent1);

            }
        });
        button_logic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent2=new Intent(MainActivity.this,level2.class);
                startActivity(intent2);

            }
        });
        button_verbal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent3=new Intent(MainActivity.this,level3.class);
                startActivity(intent3);

            }
        });
        button_puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent4=new Intent(MainActivity.this,level4.class);
                startActivity(intent4);

            }
        });
    }
}
