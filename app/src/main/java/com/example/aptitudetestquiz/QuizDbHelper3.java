package com.example.aptitudetestquiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aptitudetestquiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper3 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz3.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper3(Context context) {
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

    private void fillQuestionsTable()
    {
        Question q1 = new Question("choose the word which is the exact OPPOSITE of the given words.\n" +
                "\t\n" +
                "EXODUS",
                "Influx", "Home-coming", "Return", "Restoration", 1);
        addQuestion(q1);

        Question q2 = new Question("Read each sentence to find out whether there is any grammatical error in it.",
                "I could not put up in a hotel\t",
                "because the boarding and lodging charges",
                "were exorbitant.",
                "no error", 1);
        addQuestion(q2);

        Question q3 = new Question("Read each sentence to find out whether there is any grammatical error in it.",
                "A lot of travel delay is caused",
                "due to the inefficiency and lack of good management",
                "on behalf of the railways.",
                "no errors",
                3);
        addQuestion(q3);

        Question q4 = new Question("For given sentences\n" +
                "P :\tEinstein was\n" +
                "Q :\talthough a great scientist\n" +
                "R :\tweak in arithmetic\n" +
                "S :\tright from his school days\n" +
                "The Proper sequence should be:",
                "SRPQ", "QPRS", "QPSR","RQPS", 2);
        addQuestion(q4);

        Question q5 = new Question("Extreme old age when a man behaves like a fool",
                "Imbecility",
                "Senility",
                "Dotage",
                "Superannuation",
                3);
        addQuestion(q5);

        Question q6 = new Question("The study of ancient societies",
                "Anthropology",
                "Archaeology",
                "History",
                "Ethnology",
                2);
        addQuestion(q6);

        Question q7 = new Question("To cause troops, etc. to spread out in readiness for battle",
                "Disperse",
                "Deploy",
                "Collocate",
                "Align",
                2);
        addQuestion(q7);

        Question q8 = new Question("Given pair of words have a certain relationship to each other followed by four pairs of related words, Select the pair which has the same relationship.\n" +
                "THRUST:SPEAR",
                "mangle:iron",
                "scabbard:sword",
                "bow:arrow",
                "fence:epee",
                4);
        addQuestion(q8);

        Question q9 = new Question("Choose the correct meaning of proverb/idiom\n" +
                "To catch a tartar\n",
                "To trap wanted criminal with great difficulty",
                "To catch a dangerous person",
                "To meet with disaster",
                "To deal with a person who is more than one's match",
                2);
        addQuestion(q9);

        Question q10 = new Question("Choose the correct meaning of proverb/idiom\n" +
                "To drive home\n",
                "To find one's roots",
                "To return to place of rest",
                "Back to original position",
                "To emphasise",
                4);
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
