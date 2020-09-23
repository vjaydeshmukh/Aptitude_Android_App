package com.example.aptitudetestquiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aptitudetestquiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class DBHelperdiff1 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz5.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public DBHelperdiff1(Context context) {
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


        Question q11 = new Question("Statements: No women teacher can play. Some women teachers are athletes.\n" +
                "Conclusions:\n" +
                "Male athletes can play.\n" +
                "Some athletes can play.",
                "Only conclusion I follows",
                "Only conclusion II follows",
                "Either I or II follows",
                "Neither I nor II follows",
                4);
        addQuestion(q11);

        Question q12 = new Question("Statements: Some doctors are fools. Some fools are rich.\n" +
                "Conclusions:\n" +
                "Some doctors are rich\n" +
                "Some rich are doctors.",
                "Only conclusion I follows",
                "Only conclusion II follows",
                "Either I or II follows",
                "Neither I nor II follows",
                4);
        addQuestion(q12);

        Question q13 = new Question("ELFA, GLHA, ILJA, _____, MLNA",
                "OLPA",
                "KLMA",
                "LLMA",
                "KLLA",
                4);
        addQuestion(q13);

        Question q14 = new Question("QAR, RAS, SAT, TAU, _____",
                "UAV",
                "UAT",
                "TAS",
                "TAT",
                1);
        addQuestion(q14);
        Question q15 = new Question("Here are some words translated from an artificial language.\n" +
                "gorblflur means fan belt\n" +
                "pixngorbl means ceiling fan\n" +
                "arthtusl means tile roof\n" +
                "Which word could mean \"ceiling tile\"?",
                "gorbltusl",
                "flurgorbl",
                "arthflur",
                "pixnarth",
                4);
        addQuestion(q15);

        Question q16 = new Question("Here are some words translated from an artificial language.\n" +
                "migenlasan means cupboard\n" +
                "lasanpoen means boardwalk\n" +
                "cuopdansa means pullman\n" +
                "Which word could mean \"walkway\"?",
                "poenmigen",
                "cuopeisel",
                "lasandansa",
                "poenforc", 4);
        addQuestion(q16);

        Question q17 = new Question("Tanya is older than Eric.\n" +
                "Cliff is older than Tanya.\n" +
                "Eric is older than Cliff.\n" +
                "If the first two statements are true, the third statement is",
                "true",
                "false",
                "uncertain",
                "-",
                2);
        addQuestion(q17);

        Question q18 = new Question("The Kingston Mall has more stores than the Galleria.\n" +
                "The Four Corners Mall has fewer stores than the Galleria.\n" +
                "The Kingston Mall has more stores than the Four Corners Mall.\n" +
                "If the first two statements are true, the third statement is\n",
                "true",
                "false",
                "uncertain",
                "-",
                1);
        addQuestion(q18);

        Question q19 = new Question("Look at this series: 14, 28, 20, 40, 32, 64, ... What number should come next?\n",
                "52", "56", "96", "128", 2);
        addQuestion(q19);
        Question q20 = new Question("Look at this series: F2, __, D8, C16, B32, ... What number should fill the blank?",
                "A16",
                "G4",
                "E4",
                "E3",
                3);
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
