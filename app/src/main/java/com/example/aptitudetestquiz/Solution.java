package com.example.aptitudetestquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Solution extends AppCompatActivity {


    TextView questionno;
    TextView question;
    TextView answer;
    ArrayList<Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);
        Intent intent = getIntent();
        //questionList = (ArrayList<Question>) intent.getParcelableArrayListExtra("questiondata");
        question.setText(questionList.get(1).getQuestion());





    }
}