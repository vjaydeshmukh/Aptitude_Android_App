package com.
        example.aptitudetestquiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aptitudetestquiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class DBHelperdiff3 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz7.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public DBHelperdiff3(Context context) {
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

        Question q11 = new Question("From the given alternatives, choose the one which best expresses the given sentence in Indirect/Direct speech\n" +
                "\t\n" +
                "I told him that he was not working hard.",
                "I said to him, \"You are not working hard.\"",
                "I told to him, \"You are not working hard.\"",
                "I said, \"You are not working hard.\"",
                "I said to him, \"He is not working hard.\"",
                1);
        addQuestion(q11);

        Question q12 = new Question("From the given alternatives, choose the one which best expresses the given sentence in Indirect/Direct speech\t\n" +
                "He said to his father, \"Please increase my pocket-money.\"",
                "He told his father, \"Please increase the pocket-money\"",
                "He pleaded his father to please increase my pocket money.",
                "He requested his father to increase his pocket-money.",
                "He asked his father to increase his pocket-money.\n",
                3);
        addQuestion(q12);

        Question q13 = new Question("Which of phrases given below each sentence should replace the phrase printed in bold type to make the grammatically correct?\n" +
                "\t\n" +
                "The small child does whatever his father was done.",
                "has done",
                "did",
                "does",
                "had done",
                3);
        addQuestion(q13);

        Question q14 = new Question("In the following the questions choose the word which best expresses the meaning of the given word.\n" +
                "CORPULENT",
                "Lean",
                "Gaunt",
                "Emaciated",
                "Obese",
                4);
        addQuestion(q14);

        Question q15 = new Question("In the following the questions choose the word which best expresses the meaning of the given word.\n" +
                "EMBEZZLE",
                "Misappropriate",
                "Balance",
                "Remunerate",
                "Clear",
                1);
        addQuestion(q15);

        Question q16 = new Question("In the following questions choose the word which is the exact OPPOSITE of the given words.\n" +
                " \t\n" +
                "INDISCREET\n",
                "Reliable",
                "Honest",
                "Prudent",
                "Stupid",
                3);
        addQuestion(q16);

        Question q17 = new Question("In the following questions choose the word which is the exact OPPOSITE of the given words. \t\n" +
                "\t\n" +
                "TANGIBLE\n",
                "Ethereal",
                "Concrete",
                "Actual",
                "Solid",
                1);
        addQuestion(q17);

        Question q18 = new Question("Change the voice.\n" +
                "\n" +
                "You should open the wine about three hours before you use it.",
                "Wine should be opened about three hours before use.",
                "Wine should be opened by you three hours before use.",
                "Wine should be opened about three hours before you use it.",
                "Wine should be opened about three hours before it is used.\n" ,
                4);
        addQuestion(q18);

        Question q19 = new Question("Change the voice.\n" +
                "After driving professor Kumar to the museum she dropped him at his hotel.\n",
                "After being driven to the museum, Professor Kumar was dropped at his hotel.",
                "Professor Kumar was being driven dropped at his hotel",
                "After she had driven Professor Kumar to the museum she had dropped him at his hotel.",
                "After she was driven Professor Kumar to the museum she had dropped him at his hotel.",
                1);
        addQuestion(q19);

        Question q20 = new Question("Change the voice.\n" +
                "\n" +
                "Who is creating this mess?\n",
                "Who has been created this mess?",
                "By whom has this mess been created?",
                "By whom this mess is being created?",
                "By whom is this mess being created?",
                4);
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
