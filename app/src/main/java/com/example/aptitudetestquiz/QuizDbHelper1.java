package com.example.aptitudetestquiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aptitudetestquiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper1 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz1.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper1(Context context) {
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
            db.execSQL("ALTER TABLE "+QuestionsTable.TABLE_NAME+" ADD  COLUMN COLUMN_SOLUTION TEXT");
        }
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Look at this series: 14, 28, 20, 40, 32, 64, ... What number should come next?\n",
                "52", "56", "96", "128", 2);
        addQuestion(q1);
        Question q2 = new Question("Look at this series: 8, 6, 9, 23, 87 , ... What number should come next?\n",
                "128", "226", "324","429", 4);
        addQuestion(q2);
        Question q3 = new Question("An Informal Gathering occurs when a group of people get together in a casual, relaxed manner." +
                " Which situation below is the best example of an Informal Gathering?\n",
                "The book club meets on the first Thursday evening of every month.",
                "After finding out about his promotion, Jeremy and a few coworkers decide to go out for a quick drink after work.",
                "Mary sends out 25 invitations for the bridal shower she is giving for her sister.",
                "Whenever she eats at the Mexican restaurant, Clara seems to run into Peter.",
                2);
        addQuestion(q3);
        Question q4 = new Question(")Reentry occurs when a person leaves his or her social system for a period of time and then returns." +
                " Which situation below best describes Reentry ?\n",
                "When he is offered a better paying position, Jacob leaves the restaurant he manages to manage a new restaurant on the other side of town.",
                "Catherine is spending her junior year of college studying abroad in France.",
                "Malcolm is readjusting to civilian life after two years of overseas military service.",
                "After several miserable months, Sharon decides that she can no longer share an apartment with her roommate Hilary.",
                3);
        addQuestion(q4);

        Question q5 = new Question("Erin is twelve years old. For three years, she has been asking her parents for a dog. " +
                "Her parents have given her permission to have a bird  and not a dog. " +
                "Erin has not yet decided what kind of bird she would like to have.",
                "Erin's parents like birds better than they like dogs.",
                "Erin does not like birds.",
                "Erin and her parents live in an apartment.",
                "Erin and her parents would like to move.",
                3);
        addQuestion(q5);

        Question q6 = new Question("Georgia is older than her cousin Marsha." +
                " Marsha's brother Bart is older than Georgia. When Marsha and Bart are visiting with Georgia, all three like to play a game of Monopoly. " +
                "Marsha wins more often than Georgia does.\n",
                "When he plays Monopoly with Marsha and Georgia, Bart often loses.",
                "Of the three, Georgia is the oldest.",
                "Georgia hates to lose at Monopoly.",
                "Of the three, Marsha is the youngest.",
                4);
        addQuestion(q6);

        Question q7 = new Question("Statement: In order to bring punctuality in our office, we must provide conveyance allowance to our employees." +
                "- In charge of a company tells Personnel Manager." +
                "Assumptions:" +
                "Conveyance allowance will not help in bringing punctuality." +
                "Discipline and reward should always go hand in hand.",
                "Only assumption I is implicit",
                "Only assumption II is implicit",
                "Neither I nor II is implicit",
                "Both I and II are implicit",
                2);
        addQuestion(q7);

        Question q8 = new Question("Statement: \"If you trouble me, I will slap you.\" - A mother warns her child.\n" +

                "Assumptions:" +
                "With the warning, the child may stop troubling her." +
                "All children are basically naughty.",
                "Only assumption I is implicit",
                "Only assumption II is implicit",
                "Either I or II is implicit",
                "Both I and II are implicit",
                1);
        addQuestion(q8);

        Question q9 = new Question("Statements:\n" +
                "\n" +
                "All the schools in the area had to be kept closed for most part of the week.\n" +
                "Many parents have withdrawn their children from the local schools.",
                "Statement I is the cause and statement II is its effect",
                "Statement II is the cause and statement I is its effect",
                "Both the statements I and II are independent causes",
                "Both the statements I and II are effects of independent causes",
                4);
        addQuestion(q9);

        Question q10 = new Question("Statements:\n" +
                "There is unprecedented increase in the number of young unemployed in comparison to the previous year.\n" +
                "A large number of candidates submitted applications against an advertisement for the post of manager issued by a bank.",
                "Statement I is the cause and statement II is its effect",
                "Statement II is the cause and statement I is its effect",
                "Both the statements I and II are independent causes",
                "Both the statements I and II are effects of independent causes",
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
