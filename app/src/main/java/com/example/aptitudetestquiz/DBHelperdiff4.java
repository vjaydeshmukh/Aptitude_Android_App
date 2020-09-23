package com.example.aptitudetestquiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aptitudetestquiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class DBHelperdiff4 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz8.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public DBHelperdiff4(Context context) {
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


        Question q6 = new Question("In a country, all families want a boy. They keep having babies till a boy is born. " +
                "What is the expected ratio of boys and girls in the country?",
                "50:50",
                "25:75",
                "75:25",
                "20:80",
                1);
        addQuestion(q6);

        Question q7 = new Question("You have 15 Rs with you. You go to a shop and shopkeeper tells you price as 1 Rs per chocolate." +
                " He also tells you that you can get a chocolate in return of 3 wrappers. How many maximum chocolates you can eat?\n",
                "20",
                "19",
                "22",
                "17",
                3);
        addQuestion(q7);

        Question q8 = new Question("There is an 8 by 8 chessboard in which two diagonally opposite corners have been cut off." +
                "You are given 31 dominos, and a single domino can cover exactly two squares. Can you use the 31 dominos to cover the entire board?\n",
                "Yes",
                "No",
                "Can't say",
                "Data insufficient",
                2);
        addQuestion(q8);

        Question q9 = new Question(" A newspaper made of 16 large sheets of paper folded in half. The newspaper has 64 pages altogether." +
                " The first sheet contains pages 1, 2, 63, 64.If we pick up a sheet containing page number 45. What are the other pages that this sheet contains?",
                "18, 19, 45, 46.",
                "18, 19, 44, 45.",
                "19, 20, 44, 45.",
                "19, 20, 45, 46.",
                4);
        addQuestion(q9);

        Question q10 = new Question("10  A car has 4 tyres and 1 spare tyre. Each tyre can travel a maximum distance of 20000 miles before wearing off. " +
                "What is the maximum distance the car can travel before you are forced to buy a new tyre?\n",
                "25000",
                "26000",
                "25500",
                "24000",
                1);
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
