package com.example.aptitudetestquiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aptitudetestquiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Two trains are moving in opposite directions @ 60 km/hr and 90 km/hr." +
                " Their lengths are 1.10 km and 0.9 km respectively." +
                " The time taken by the slower train to cross the faster train in seconds is:\n",
                "36 sec", "45 sec", "48 sec", "49 sec", 3);
        addQuestion(q1);

        Question q2 = new Question("At present, the ratio between the ages of Arun and Deepak is 4 : 3." +
                " After 6 years, Arun's age will be 26 years. What is the age of Deepak at present ?\n",
                "12 years", "15 years", "19 and half","21 years", 2);
        addQuestion(q2);

        Question q3 = new Question("In a box, there are 8 red, 7 blue and 6 green balls." +
                " One ball is picked up randomly. What is the probability that it is neither red nor green?\n",
                "1/3", "3/4", "7/19", "8/21",1);
        addQuestion(q3);

        Question q4 = new Question("A trader mixes 26 kg of rice at Rs. 20 per kg with 30 kg of rice of other variety at" +
                " Rs. 36 per kg and sells the mixture at Rs. 30 per kg. His profit percent is:\n",
                "No profit, no loss", "5%", "8%","10%", 2);
        addQuestion(q4);

        Question q5 = new Question("The difference of two numbers is 1365. On dividing the larger number by the smaller, " +
                "we get 6 as quotient and the 15 as remainder. What is the smaller number ?\n",
                "240", "270", "295","360", 2);
        addQuestion(q5);

        Question q6 = new Question("The angle of elevation of a ladder leaning against a wall is 60° " +
                "and the foot of the ladder is 4.6 m away from the wall. The length of the ladder is:\n",
                "2.3m", "4.6m", "7.8m","9.2m", 4);
        addQuestion(q6);

        Question q7 = new Question("A alone can do a piece of work in 6 days and B alone in 8 days." +
                " A and B undertook to do it for Rs. 3200." +
                " With the help of C, they completed the work in 3 days. How much is to be paid to C?\n",
                "375", "400", "600","800", 2);
        addQuestion(q7);

        Question q8 = new Question("Out of 7 consonants and 4 vowels, how many words of 3 consonants and 2 vowels can be formed?",
                "210", "1050", "25200","21400", 3);
        addQuestion(q8);

        Question q9 = new Question("A boat running upstream takes 8 hours 48 minutes to cover a certain distance, while it takes 4 hours to cover the" +
                " same distance running downstream.What is the ratio between the speed of the boat" +
                " and speed of the water current respectively?",
                "2 : 1", "3 : 2", "8 : 3","Cannot be determined", 3);
        addQuestion(q9);

        Question q10 = new Question("In a mixture 60 litres, the ratio of milk and water 2 : 1." +
                " If this ratio is to be 1 : 2, then the quantity of water to be further added is:\n",
                "20 litres", "30 litres", "40 litres","60 litres", 4);
        addQuestion(q10);


    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
