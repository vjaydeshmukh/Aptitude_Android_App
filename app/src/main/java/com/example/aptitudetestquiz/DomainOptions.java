package com.example.aptitudetestquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

public class DomainOptions extends AppCompatActivity {
    ImageButton button_quants;
    ImageButton button_logic;
    ImageButton button_puzzle;
    ImageButton button_verbal;

    private View button_database;
    boolean doubleBackToExistPressedOnce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain_options);
        final MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.click);

        button_quants=findViewById(R.id.button_quants);
        button_logic=findViewById(R.id.button_logic);
        button_puzzle=findViewById(R.id.button_puzzle);
        button_verbal=findViewById(R.id.button_verbal);


        button_quants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent1=new Intent(DomainOptions.this,levels.class);
                String str = "quants";
                intent1.putExtra("Domain_name",str);
                startActivity(intent1);

            }
        });
        button_logic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent2=new Intent(DomainOptions.this,levels.class);
                String str = "logic";
                intent2.putExtra("Domain_name",str);
                startActivity(intent2);

            }
        });
        button_verbal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent3=new Intent(DomainOptions.this,levels.class);
                String str = "verbal";
                intent3.putExtra("Domain_name",str);
                startActivity(intent3);

            }
        });
        button_puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent4=new Intent(DomainOptions.this,levels.class);
                String str = "puzzle";
                intent4.putExtra("Domain_name",str);
                startActivity(intent4);

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
