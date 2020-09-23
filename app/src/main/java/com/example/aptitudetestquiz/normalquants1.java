package com.example.aptitudetestquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class normalquants1 extends AppCompatActivity {

    public static final String EXTRA_SCORE="extrascore";
    private static final long  COUNT_DOWN=600000;

    private TextView textViewQuestion;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button submit_next;
    private long backpressed;

    private boolean answered;
    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion= new Question();

    private int totalscore;

    private  ColorStateList textColorDefaultcd;
    private CountDownTimer countDownTimer;
    private long timeleft;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normalquants1);
        final MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.click);
        textViewQuestion=findViewById(R.id.question);
        textViewQuestionCount=findViewById(R.id.questionno);
        textViewCountDown=findViewById(R.id.countdown);
        rbGroup=findViewById(R.id.radiogroup);
        rb1=findViewById(R.id.radio_button1);
        rb2=findViewById(R.id.radio_button2);
        rb3=findViewById(R.id.radio_button3);
        rb4=findViewById(R.id.radio_button4);

        submit_next=findViewById(R.id.submit_next);

        textColorDefaultcd=textViewCountDown.getTextColors();

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList=dbHelper.getAllQuestions();
        questionCountTotal=questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();
        submit_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                if(!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        checkAnswer();
                    } else
                        {
                            Toast.makeText(normalquants1.this, "Please answer the question", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    showNextQuestion();
                }

            }
        });
        timeleft=COUNT_DOWN;
        startCountDown();

    }

    private void showNextQuestion()
    {
        rbGroup.clearCheck();
        if(questionCounter<questionCountTotal)
        {
            currentQuestion=questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            

            questionCounter++;
            textViewQuestionCount.setText("Question: "+questionCounter + "/" +questionCountTotal);
            answered=false;
            submit_next.setText("CONFIRM");




        }
        else
        {
            finishQuiz();
        }
    }

    private void startCountDown()
    {
        countDownTimer=new CountDownTimer(timeleft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleft=millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                updateCountDownText();
                checkAnswer();

            }
        }.start();
    }
       private  void updateCountDownText()
       {
           int min=(int )(timeleft/1000)/60;
           int sec=(int )(timeleft/1000)%60;

           String time=String.format(Locale.getDefault(),"%02d:%02d",min,sec);
           if(timeleft<=2000)
           {
               finishQuiz();
           }
           textViewCountDown.setText(time);
           if(timeleft<60000)
           {
               textViewCountDown.setTextColor(Color.RED);
           }
           else
           {
               textViewCountDown.setTextColor(Color.WHITE);
           }


       }
    private void checkAnswer() {
        answered = true;

        //countDownTimer.cancel();
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {
            totalscore++;
        }

        if (questionCounter < questionCountTotal) {
            submit_next.setText("SUBMIT");
        }
        else
        {
            submit_next.setText("FINISH");
        }
    }
    private void finishQuiz()
    {

        finish();
        Intent intent2 = new Intent(getApplicationContext(), score.class);
        intent2.putExtra(EXTRA_SCORE,Integer.toString(totalscore));
        startActivity(intent2);

    }
    public void onBackPressed()
    {
        if(backpressed +2000 >System.currentTimeMillis())
        {
            finishQuiz();
        }
        else
        {
            Toast.makeText(this, "Press  back again to finish", Toast.LENGTH_SHORT).show();
        }
        backpressed=System.currentTimeMillis();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}
