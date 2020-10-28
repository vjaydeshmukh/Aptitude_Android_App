package com.example.aptitudetestquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

public class score extends AppCompatActivity {

    Button anothertest;
    Button solutions;
    TextView print_correctque;
    TextView print_skippedque;
    TextView print_wrongque;
    TextView time_required;
    boolean doubleBackToExistPressedOnce;

    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    ArrayList PieEntryLabels;
    int correct;
    int skipped;
    int wrong;
    private ArrayList<Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        final MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.click);

        anothertest=(Button)findViewById(R.id.anothertest);
        solutions=(Button) findViewById(R.id.solutions);

        print_correctque=(TextView) findViewById(R.id.correct);
        print_skippedque = (TextView) findViewById(R.id.skipped);
        print_wrongque = (TextView) findViewById(R.id.wrong);
        time_required = (TextView) findViewById(R.id.time_required);

        Intent intent =getIntent();
        print_correctque.setText(intent.getStringExtra("correctque"));
        print_skippedque.setText(intent.getStringExtra("skippedque"));
        print_wrongque.setText(intent.getStringExtra("wrongque"));
        time_required.setText(intent.getStringExtra("time_required"));

        //questionList = intent.getParcelableArrayListExtra("questiondata");

        correct= Integer.parseInt(intent.getStringExtra("correctque"));
        wrong= Integer.parseInt(intent.getStringExtra("wrongque"));
        skipped= Integer.parseInt(intent.getStringExtra("skippedque"));

        pieChart = findViewById(R.id.pieChart);
        getEntries();
        pieDataSet = new PieDataSet(pieEntries, " ");

        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(20f);
        pieDataSet.setSliceSpace(5f);
        pieDataSet.setColors(Color.GREEN,Color.BLUE,Color.RED);
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.setCenterText("SCORE ANALYSIS");
        pieChart.setCenterTextSize(20f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.BLACK);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleRadius(60f);
        pieChart.setCenterTextColor(Color.WHITE);
        pieChart.getLegend().setEnabled(false);


        anothertest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent intent1 = new Intent(score.this, DomainOptions.class);
                startActivity(intent1);
            }
        });

        solutions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent intent2 = new Intent(score.this,Solution.class);
                /*Bundle bundle = new Bundle();
               /* bundle.putParcelable("questionsdata", (Parcelable) questionList);
                intent2.putExtras(bundle);*/
                //intent2.putParcelableArrayListExtra("questiondata",questionList);
                startActivity(intent2);
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
    private void getEntries() {
        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(correct, "Correct"));
        pieEntries.add(new PieEntry(skipped, "Skipped"));
        pieEntries.add(new PieEntry(wrong, "Wrong"));

}}
