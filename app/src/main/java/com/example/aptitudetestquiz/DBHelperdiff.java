package com.example.aptitudetestquiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aptitudetestquiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class DBHelperdiff extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz6.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public DBHelperdiff(Context context) {
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


        Question q11 = new Question("Two trains running in opposite directions cross a man standing on the platform in 27 seconds and 17 seconds respectively " +
                "and they cross each other in 23 seconds. The ratio of their speeds is:\n",
                "1:3", "3:2", "3:4", "None of the above", 2);
        addQuestion(q11);

        Question q12 = new Question("A person borrows Rs. 5000 for 2 years at 4% p.a. simple interest. He immediately lends it to another person at" +
                " 6% p.a for 2 years. Find his gain in the transaction per year.\n",
                "Rs. 112.50", "Rs. 125", "Rs. 225", "Rs. 167.50", 1);
        addQuestion(q12);

        Question q13 = new Question("Two students appeared at an examination. One of them secured 9 marks more than the other and his marks was 56%" +
                " of the sum of their marks. The marks obtained by them are:\n",
                "39, 30", "41, 32", "42,33", "43,34", 3);
        addQuestion(q13);

        Question q14 = new Question("If A = x% of y and B = y% of x, then which of the following is true?\n" +
                "\n",
                "A is smaller than B.",
                "A is greater than B.",
                "Relationship between A and B cannot be determined.",
                "None of these",
                4);
        addQuestion(q14);

        Question q15 = new Question("On 8th Dec, 2007 Saturday falls. What day of the week was it on 8th Dec, 2006?\n",
                "Sunday",
                "Thursday",
                "Friday",
                "Tuesday",
                3);
        addQuestion(q15);

        Question q16 = new Question("The price of 10 chairs is equal to that of 4 tables. The price of 15 chairs and " +
                "2 tables together is Rs. 4000. The total price of 12 chairs and 3 tables is:\n",
                "Rs. 3500", "Rs. 3750", "Rs. 3840", "Rs. 3900", 4);
        addQuestion(q16);

        Question q17 = new Question("In a regular week, there are 5 working days and for each day, the working hours are 8. A man gets Rs. 2.40 per hour for regular work and Rs. 3.20 per hours for overtime. " +
                "If he earns Rs. 432 in 4 weeks, then how many hours does he work for ?\n",
                "160", "175", "180", "195", 2);
        addQuestion(q17);

        Question q18 = new Question("A, B, C subscribe Rs. 50,000 for a business. A subscribes Rs. 4000 more than B" +
                " and B Rs. 5000 more than C. Out of a total profit of Rs. 35,000, A receives:\n",
                "Rs. 8400", "Rs. 11,900", "Rs. 13,600", "Rs. 14,700", 4);
        addQuestion(q18);

        Question q19 = new Question("If log 27 = 1.431, then the value of log 9 is:\n",
                "0.934", "0.945", "0.954", "0.958", 3);
        addQuestion(q19);

        Question q20 = new Question(")Find out the wrong number in the given sequence of numbers.\t\n" +
                "8, 13, 21, 32, 47, 63, 83\n",
                "47", "63", "32", "83", 1);
        addQuestion(q20);
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
