package com.example.aptitudetestquiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aptitudetestquiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper4 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz4.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper4(Context context) {
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
        // db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE "+QuestionsTable.TABLE_NAME+" ADD COLUMN COLUMN_SOLUTION TEXT");
        }
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Alok has three daughters. His friend Shyam wants to know the ages of his daughters. Alok gives him first hint." +
                " 1) The product of their ages is 72.2)The sum of their ages is equal" +
                "  to my house number.3)The oldest of the girls likes strawberry ice-cream." ,

                "2,4,9",
                "2,3,13",
                "3,3,8",
                "2,6,6",
                3);
        addQuestion(q1);

        Question q2 = new Question("An employee works for an employer for 7 days. The employer has a gold rod of 7 units. How does the employer pays to the employee so that the employee gets 1 unit at the end of everyday." +
                " The employer can make at most 2 cuts in rod.\n",
                "3 rods of size 1, 2 and 4.",
                "3 rods of size 2, 2 and 3.",
                "3 rods of size 1, 3 and 3.",
                "3 rods of size 1, 1 and 5.",
                1);
        addQuestion(q2);

        Question q3 = new Question(" A man is allocated a task. He doubles the task done everyday. If the man completely does the task in 18 days, " +
                "how many days did it take for the man to complete 25% of the task?",
                "20", "18", "14", "16",4);
        addQuestion(q3);

        Question q4 = new Question("There are 3 ants sitting on three corners of a triangle. All ants randomly pick a direction and start moving along edge of the triangle. What is the probability that any two ants collide?\n" +
                " \n" +
                "Hint: Every ant has two choices ",
                "6/8",
                "2/8",
                "3/8",
                "5/8",
                1);
        addQuestion(q4);

        Question q5 = new Question("There are 1000 wine bottles. One of the bottles contains poisoned wine." +
                " A rat dies after one hour of drinking the poisoned wine. How many minimum rats are needed to figure out which bottle contains poison in hour.\n",
                "8",
                "9",
                "10",
                "11", 3);
        addQuestion(q5);


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
