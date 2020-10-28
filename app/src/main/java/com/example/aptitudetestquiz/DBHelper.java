package com.example.aptitudetestquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.aptitudetestquiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuestionDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public DBHelper(Context context) {
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
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER," +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_TYPE + " TEXT " +
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
                "36 sec", "45 sec", "48 sec", "49 sec", 3,"normal","quants");
        addQuestion(q1);

        Question q2 = new Question("At present, the ratio between the ages of Arun and Deepak is 4 : 3." +
                " After 6 years, Arun's age will be 26 years. What is the age of Deepak at present ?\n",
                "12 years", "15 years", "19 and half","21 years", 2,"normal","quants");
        addQuestion(q2);

        Question q3 = new Question("In a box, there are 8 red, 7 blue and 6 green balls." +
                " One ball is picked up randomly. What is the probability that it is neither red nor green?\n",
                "1/3", "3/4", "7/19", "8/21",1,"normal","quants");
        addQuestion(q3);

        Question q4 = new Question("A trader mixes 26 kg of rice at Rs. 20 per kg with 30 kg of rice of other variety at" +
                " Rs. 36 per kg and sells the mixture at Rs. 30 per kg. His profit percent is:\n",
                "No profit, no loss", "5%", "8%","10%", 2,"normal","quants");
        addQuestion(q4);

        Question q5 = new Question("The difference of two numbers is 1365. On dividing the larger number by the smaller, " +
                "we get 6 as quotient and the 15 as remainder. What is the smaller number ?\n",
                "240", "270", "295","360", 2,"normal","quants");
        addQuestion(q5);

        Question q6 = new Question("The angle of elevation of a ladder leaning against a wall is 60° " +
                "and the foot of the ladder is 4.6 m away from the wall. The length of the ladder is:\n",
                "2.3m", "4.6m", "7.8m","9.2m", 4,"normal","quants");
        addQuestion(q6);

        Question q7 = new Question("A alone can do a piece of work in 6 days and B alone in 8 days." +
                " A and B undertook to do it for Rs. 3200." +
                " With the help of C, they completed the work in 3 days. How much is to be paid to C?\n",
                "375", "400", "600","800", 2,"normal","quants");
        addQuestion(q7);

        Question q8 = new Question("Out of 7 consonants and 4 vowels, how many words of 3 consonants and 2 vowels can be formed?",
                "210", "1050", "25200","21400", 3,"normal","quants");
        addQuestion(q8);

        Question q9 = new Question("A boat running upstream takes 8 hours 48 minutes to cover a certain distance, while it takes 4 hours to cover the" +
                " same distance running downstream.What is the ratio between the speed of the boat" +
                " and speed of the water current respectively?",
                "2 : 1", "3 : 2", "8 : 3","Cannot be determined", 3,"normal","quants");
        addQuestion(q9);

        Question q10 = new Question("In a mixture 60 litres, the ratio of milk and water 2 : 1." +
                " If this ratio is to be 1 : 2, then the quantity of water to be further added is:\n",
                "20 litres", "30 litres", "40 litres","60 litres", 4,"normal","quants");
        addQuestion(q10);

        Question q11 = new Question("Look at this series: 14, 28, 20, 40, 32, 64, ... What number should come next?\n",
                "52", "56", "96", "128", 2,"normal","logic");
        addQuestion(q11);
        Question q12 = new Question("Look at this series: 8, 6, 9, 23, 87 , ... What number should come next?\n",
                "128", "226", "324","429", 4,"normal","logic");
        addQuestion(q12);
        Question q13 = new Question("An Informal Gathering occurs when a group of people get together in a casual, relaxed manner." +
                " Which situation below is the best example of an Informal Gathering?\n",
                "The book club meets on the first Thursday evening of every month.",
                "After finding out about his promotion, Jeremy and a few coworkers decide to go out for a quick drink after work.",
                "Mary sends out 25 invitations for the bridal shower she is giving for her sister.",
                "Whenever she eats at the Mexican restaurant, Clara seems to run into Peter.",
                2,"normal","logic");
        addQuestion(q13);
        Question q14 = new Question(")Reentry occurs when a person leaves his or her social system for a period of time and then returns." +
                " Which situation below best describes Reentry ?\n",
                "When he is offered a better paying position, Jacob leaves the restaurant he manages to manage a new restaurant on the other side of town.",
                "Catherine is spending her junior year of college studying abroad in France.",
                "Malcolm is readjusting to civilian life after two years of overseas military service.",
                "After several miserable months, Sharon decides that she can no longer share an apartment with her roommate Hilary.",
                3,"normal","logic");
        addQuestion(q14);

        Question q15 = new Question("Erin is twelve years old. For three years, she has been asking her parents for a dog. " +
                "Her parents have given her permission to have a bird  and not a dog. " +
                "Erin has not yet decided what kind of bird she would like to have.",
                "Erin's parents like birds better than they like dogs.",
                "Erin does not like birds.",
                "Erin and her parents live in an apartment.",
                "Erin and her parents would like to move.",
                3,"normal","logic");
        addQuestion(q15);

        Question q16 = new Question("Georgia is older than her cousin Marsha." +
                " Marsha's brother Bart is older than Georgia. When Marsha and Bart are visiting with Georgia, all three like to play a game of Monopoly. " +
                "Marsha wins more often than Georgia does.\n",
                "When he plays Monopoly with Marsha and Georgia, Bart often loses.",
                "Of the three, Georgia is the oldest.",
                "Georgia hates to lose at Monopoly.",
                "Of the three, Marsha is the youngest.",
                4,"normal","logic");
        addQuestion(q16);

        Question q17 = new Question("Statement: In order to bring punctuality in our office, we must provide conveyance allowance to our employees." +
                "- In charge of a company tells Personnel Manager." +
                "Assumptions:" +
                "Conveyance allowance will not help in bringing punctuality." +
                "Discipline and reward should always go hand in hand.",
                "Only assumption I is implicit",
                "Only assumption II is implicit",
                "Neither I nor II is implicit",
                "Both I and II are implicit",
                2,"normal","logic");
        addQuestion(q17);

        Question q18 = new Question("Statement: \"If you trouble me, I will slap you.\" - A mother warns her child.\n" +

                "Assumptions:" +
                "With the warning, the child may stop troubling her." +
                "All children are basically naughty.",
                "Only assumption I is implicit",
                "Only assumption II is implicit",
                "Either I or II is implicit",
                "Both I and II are implicit",
                1,"normal","logic");
        addQuestion(q18);

        Question q19 = new Question("Statements:\n" +
                "\n" +
                "All the schools in the area had to be kept closed for most part of the week.\n" +
                "Many parents have withdrawn their children from the local schools.",
                "Statement I is the cause and statement II is its effect",
                "Statement II is the cause and statement I is its effect",
                "Both the statements I and II are independent causes",
                "Both the statements I and II are effects of independent causes",
                4,"normal","logic");
        addQuestion(q19);

        Question q20 = new Question("Statements:\n" +
                "There is unprecedented increase in the number of young unemployed in comparison to the previous year.\n" +
                "A large number of candidates submitted applications against an advertisement for the post of manager issued by a bank.",
                "Statement I is the cause and statement II is its effect",
                "Statement II is the cause and statement I is its effect",
                "Both the statements I and II are independent causes",
                "Both the statements I and II are effects of independent causes",
                1,"normal","logic");
        addQuestion(q20);
        Question q21 = new Question("choose the word which is the exact OPPOSITE of the given words.\n" +
                "\t\n" +
                "EXODUS",
                "Influx", "Home-coming", "Return", "Restoration", 1,"normal","verbal");
        addQuestion(q21);

        Question q22 = new Question("Read each sentence to find out whether there is any grammatical error in it.",
                "I could not put up in a hotel\t",
                "because the boarding and lodging charges",
                "were exorbitant.",
                "no error", 1,"normal","verbal");
        addQuestion(q22);

        Question q23 = new Question("Read each sentence to find out whether there is any grammatical error in it.",
                "A lot of travel delay is caused",
                "due to the inefficiency and lack of good management",
                "on behalf of the railways.",
                "no errors",
                3,"normal","verbal");
        addQuestion(q23);

        Question q24 = new Question("For given sentences\n" +
                "P :\tEinstein was\n" +
                "Q :\talthough a great scientist\n" +
                "R :\tweak in arithmetic\n" +
                "S :\tright from his school days\n" +
                "The Proper sequence should be:",
                "SRPQ", "QPRS", "QPSR","RQPS", 2,"normal","verbal");
        addQuestion(q24);

        Question q25 = new Question("Extreme old age when a man behaves like a fool",
                "Imbecility",
                "Senility",
                "Dotage",
                "Superannuation",
                3,"normal","verbal");
        addQuestion(q25);

        Question q26 = new Question("The study of ancient societies",
                "Anthropology",
                "Archaeology",
                "History",
                "Ethnology",
                2,"normal","verbal");
        addQuestion(q26);

        Question q27 = new Question("To cause troops, etc. to spread out in readiness for battle",
                "Disperse",
                "Deploy",
                "Collocate",
                "Align",
                2,"normal","verbal");
        addQuestion(q27);

        Question q28 = new Question("Given pair of words have a certain relationship to each other followed by four pairs of related words, Select the pair which has the same relationship.\n" +
                "THRUST:SPEAR",
                "mangle:iron",
                "scabbard:sword",
                "bow:arrow",
                "fence:epee",
                4,"normal","verbal");
        addQuestion(q28);

        Question q29 = new Question("Choose the correct meaning of proverb/idiom\n" +
                "To catch a tartar\n",
                "To trap wanted criminal with great difficulty",
                "To catch a dangerous person",
                "To meet with disaster",
                "To deal with a person who is more than one's match",
                2,"normal","verbal");
        addQuestion(q29);

        Question q30 = new Question("Choose the correct meaning of proverb/idiom\n" +
                "To drive home\n",
                "To find one's roots",
                "To return to place of rest",
                "Back to original position",
                "To emphasise",
                4,"normal","verbal");
        addQuestion(q30);
        Question q31 = new Question("Alok has three daughters. His friend Shyam wants to know the ages of his daughters. Alok gives him first hint." +
                " 1) The product of their ages is 72.2)The sum of their ages is equal" +
                "  to my house number.3)The oldest of the girls likes strawberry ice-cream." ,

                "2,4,9",
                "2,3,13",
                "3,3,8",
                "2,6,6",
                3,"normal","puzzle");
        addQuestion(q31);

        Question q32 = new Question("An employee works for an employer for 7 days. The employer has a gold rod of 7 units. How does the employer pays to the employee so that the employee gets 1 unit at the end of everyday." +
                " The employer can make at most 2 cuts in rod.\n",
                "3 rods of size 1, 2 and 4.",
                "3 rods of size 2, 2 and 3.",
                "3 rods of size 1, 3 and 3.",
                "3 rods of size 1, 1 and 5.",
                1,"normal","puzzle");
        addQuestion(q32);

        Question q33 = new Question(" A man is allocated a task. He doubles the task done everyday. If the man completely does the task in 18 days, " +
                "how many days did it take for the man to complete 25% of the task?",
                "20", "18", "14", "16",4,"normal","puzzle");
        addQuestion(q33);

        Question q34 = new Question("There are 3 ants sitting on three corners of a triangle. All ants randomly pick a direction and start moving along edge of the triangle. What is the probability that any two ants collide?\n" +
                " \n" +
                "Hint: Every ant has two choices ",
                "6/8",
                "2/8",
                "3/8",
                "5/8",
                1,"normal","puzzle");
        addQuestion(q34);

        Question q35 = new Question("There are 1000 wine bottles. One of the bottles contains poisoned wine." +
                " A rat dies after one hour of drinking the poisoned wine. How many minimum rats are needed to figure out which bottle contains poison in hour.\n",
                "8",
                "9",
                "10",
                "11", 3,"normal","puzzle");
        addQuestion(q35);


        Question q36 = new Question("In a country, all families want a boy. They keep having babies till a boy is born. " +
                "What is the expected ratio of boys and girls in the country?",
                "50:50",
                "25:75",
                "75:25",
                "20:80",
                1,"difficult","puzzle");
        addQuestion(q36);

        Question q37 = new Question("You have 15 Rs with you. You go to a shop and shopkeeper tells you price as 1 Rs per chocolate." +
                " He also tells you that you can get a chocolate in return of 3 wrappers. How many maximum chocolates you can eat?\n",
                "20",
                "19",
                "22",
                "17",
                3,"difficult","puzzle");
        addQuestion(q37);

        Question q38 = new Question("There is an 8 by 8 chessboard in which two diagonally opposite corners have been cut off." +
                "You are given 31 dominos, and a single domino can cover exactly two squares. Can you use the 31 dominos to cover the entire board?\n",
                "Yes",
                "No",
                "Can't say",
                "Data insufficient",
                2,"difficult","puzzle");
        addQuestion(q38);

        Question q39 = new Question(" A newspaper made of 16 large sheets of paper folded in half. The newspaper has 64 pages altogether." +
                " The first sheet contains pages 1, 2, 63, 64.If we pick up a sheet containing page number 45. What are the other pages that this sheet contains?",
                "18, 19, 45, 46.",
                "18, 19, 44, 45.",
                "19, 20, 44, 45.",
                "19, 20, 45, 46.",
                4,"difficult","puzzle");
        addQuestion(q39);

        Question q40 = new Question("10  A car has 4 tyres and 1 spare tyre. Each tyre can travel a maximum distance of 20000 miles before wearing off. " +
                "What is the maximum distance the car can travel before you are forced to buy a new tyre?\n",
                "25000",
                "26000",
                "25500",
                "24000",
                1,"difficult","puzzle");
        addQuestion(q40);


        Question q41 = new Question("Two trains running in opposite directions cross a man standing on the platform in 27 seconds and 17 seconds respectively " +
                "and they cross each other in 23 seconds. The ratio of their speeds is:\n",
                "1:3", "3:2", "3:4", "None of the above", 2,"difficult","quants");
        addQuestion(q41);

        Question q42 = new Question("A person borrows Rs. 5000 for 2 years at 4% p.a. simple interest. He immediately lends it to another person at" +
                " 6% p.a for 2 years. Find his gain in the transaction per year.\n",
                "Rs. 112.50", "Rs. 125", "Rs. 225", "Rs. 167.50", 1,"difficult","quants");
        addQuestion(q42);

        Question q43 = new Question("Two students appeared at an examination. One of them secured 9 marks more than the other and his marks was 56%" +
                " of the sum of their marks. The marks obtained by them are:\n",
                "39, 30", "41, 32", "42,33", "43,34", 3,"difficult","quants");
        addQuestion(q43);

        Question q44 = new Question("If A = x% of y and B = y% of x, then which of the following is true?\n" +
                "\n",
                "A is smaller than B.",
                "A is greater than B.",
                "Relationship between A and B cannot be determined.",
                "None of these",
                4,"difficult","quants");
        addQuestion(q44);

        Question q45 = new Question("On 8th Dec, 2007 Saturday falls. What day of the week was it on 8th Dec, 2006?\n",
                "Sunday",
                "Thursday",
                "Friday",
                "Tuesday",
                3,"difficult","quants");
        addQuestion(q45);

        Question q46 = new Question("The price of 10 chairs is equal to that of 4 tables. The price of 15 chairs and " +
                "2 tables together is Rs. 4000. The total price of 12 chairs and 3 tables is:\n",
                "Rs. 3500", "Rs. 3750", "Rs. 3840", "Rs. 3900", 4,"difficult","quants");
        addQuestion(q46);

        Question q47 = new Question("In a regular week, there are 5 working days and for each day, the working hours are 8. A man gets Rs. 2.40 per hour for regular work and Rs. 3.20 per hours for overtime. " +
                "If he earns Rs. 432 in 4 weeks, then how many hours does he work for ?\n",
                "160", "175", "180", "195", 2,"difficult","quants");
        addQuestion(q47);

        Question q48 = new Question("A, B, C subscribe Rs. 50,000 for a business. A subscribes Rs. 4000 more than B" +
                " and B Rs. 5000 more than C. Out of a total profit of Rs. 35,000, A receives:\n",
                "Rs. 8400", "Rs. 11,900", "Rs. 13,600", "Rs. 14,700", 4,"difficult","quants");
        addQuestion(q48);

        Question q49 = new Question("If log 27 = 1.431, then the value of log 9 is:\n",
                "0.934", "0.945", "0.954", "0.958", 3,"difficult","quants");
        addQuestion(q49);

        Question q50 = new Question(")Find out the wrong number in the given sequence of numbers.\t\n" +
                "8, 13, 21, 32, 47, 63, 83\n",
                "47", "63", "32", "83", 1,"difficult","quants");
        addQuestion(q50);
        Question q51 = new Question("Statements: No women teacher can play. Some women teachers are athletes.\n" +
                "Conclusions:\n" +
                "Male athletes can play.\n" +
                "Some athletes can play.",
                "Only conclusion I follows",
                "Only conclusion II follows",
                "Either I or II follows",
                "Neither I nor II follows",
                4,"difficult","logic");
        addQuestion(q51);

        Question q52 = new Question("Statements: Some doctors are fools. Some fools are rich.\n" +
                "Conclusions:\n" +
                "Some doctors are rich\n" +
                "Some rich are doctors.",
                "Only conclusion I follows",
                "Only conclusion II follows",
                "Either I or II follows",
                "Neither I nor II follows",
                4,"difficult","logic");
        addQuestion(q52);

        Question q53 = new Question("ELFA, GLHA, ILJA, _____, MLNA",
                "OLPA",
                "KLMA",
                "LLMA",
                "KLLA",
                4,"difficult","logic");
        addQuestion(q53);

        Question q54 = new Question("QAR, RAS, SAT, TAU, _____",
                "UAV",
                "UAT",
                "TAS",
                "TAT",
                1,"difficult","logic");
        addQuestion(q54);
        Question q55 = new Question("Here are some words translated from an artificial language.\n" +
                "gorblflur means fan belt\n" +
                "pixngorbl means ceiling fan\n" +
                "arthtusl means tile roof\n" +
                "Which word could mean \"ceiling tile\"?",
                "gorbltusl",
                "flurgorbl",
                "arthflur",
                "pixnarth",
                4,"difficult","logic");
        addQuestion(q55);

        Question q56 = new Question("Here are some words translated from an artificial language.\n" +
                "migenlasan means cupboard\n" +
                "lasanpoen means boardwalk\n" +
                "cuopdansa means pullman\n" +
                "Which word could mean \"walkway\"?",
                "poenmigen",
                "cuopeisel",
                "lasandansa",
                "poenforc", 4,"difficult","logic");
        addQuestion(q56);

        Question q57 = new Question("Tanya is older than Eric.\n" +
                "Cliff is older than Tanya.\n" +
                "Eric is older than Cliff.\n" +
                "If the first two statements are true, the third statement is",
                "true",
                "false",
                "uncertain",
                "-",
                2,"difficult","logic");
        addQuestion(q57);

        Question q58 = new Question("The Kingston Mall has more stores than the Galleria.\n" +
                "The Four Corners Mall has fewer stores than the Galleria.\n" +
                "The Kingston Mall has more stores than the Four Corners Mall.\n" +
                "If the first two statements are true, the third statement is\n",
                "true",
                "false",
                "uncertain",
                "-",
                1,"difficult","logic");
        addQuestion(q58);

        Question q59 = new Question("Look at this series: 14, 28, 20, 40, 32, 64, ... What number should come next?\n",
                "52", "56", "96", "128", 2,"difficult","logic");
        addQuestion(q59);
        Question q60 = new Question("Look at this series: F2, __, D8, C16, B32, ... What number should fill the blank?",
                "A16",
                "G4",
                "E4",
                "E3",
                3,"difficult","logic");
        addQuestion(q60);
        Question q61 = new Question("From the given alternatives, choose the one which best expresses the given sentence in Indirect/Direct speech\n" +
                "\t\n" +
                "I told him that he was not working hard.",
                "I said to him, \"You are not working hard.\"",
                "I told to him, \"You are not working hard.\"",
                "I said, \"You are not working hard.\"",
                "I said to him, \"He is not working hard.\"",
                1,"difficult","verbal");
        addQuestion(q61);

        Question q62 = new Question("From the given alternatives, choose the one which best expresses the given sentence in Indirect/Direct speech\t\n" +
                "He said to his father, \"Please increase my pocket-money.\"",
                "He told his father, \"Please increase the pocket-money\"",
                "He pleaded his father to please increase my pocket money.",
                "He requested his father to increase his pocket-money.",
                "He asked his father to increase his pocket-money.\n",
                3,"difficult","verbal");
        addQuestion(q62);

        Question q63 = new Question("Which of phrases given below each sentence should replace the phrase printed in bold type to make the grammatically correct?\n" +
                "\t\n" +
                "The small child does whatever his father was done.",
                "has done",
                "did",
                "does",
                "had done",
                3,"difficult","verbal");
        addQuestion(q63);

        Question q64 = new Question("In the following the questions choose the word which best expresses the meaning of the given word.\n" +
                "CORPULENT",
                "Lean",
                "Gaunt",
                "Emaciated",
                "Obese",
                4,"difficult","verbal");
        addQuestion(q64);

        Question q65 = new Question("In the following the questions choose the word which best expresses the meaning of the given word.\n" +
                "EMBEZZLE",
                "Misappropriate",
                "Balance",
                "Remunerate",
                "Clear",
                1,"difficult","verbal");
        addQuestion(q65);

        Question q66 = new Question("In the following questions choose the word which is the exact OPPOSITE of the given words.\n" +
                " \t\n" +
                "INDISCREET\n",
                "Reliable",
                "Honest",
                "Prudent",
                "Stupid",
                3,"difficult","verbal");
        addQuestion(q66);

        Question q67 = new Question("In the following questions choose the word which is the exact OPPOSITE of the given words. \t\n" +
                "\t\n" +
                "TANGIBLE\n",
                "Ethereal",
                "Concrete",
                "Actual",
                "Solid",
                1,"difficult","verbal");
        addQuestion(q67);

        Question q68 = new Question("Change the voice.\n" +
                "\n" +
                "You should open the wine about three hours before you use it.",
                "Wine should be opened about three hours before use.",
                "Wine should be opened by you three hours before use.",
                "Wine should be opened about three hours before you use it.",
                "Wine should be opened about three hours before it is used.\n" ,
                4,"difficult","verbal");
        addQuestion(q68);

        Question q69 = new Question("Change the voice.\n" +
                "After driving professor Kumar to the museum she dropped him at his hotel.\n",
                "After being driven to the museum, Professor Kumar was dropped at his hotel.",
                "Professor Kumar was being driven dropped at his hotel",
                "After she had driven Professor Kumar to the museum she had dropped him at his hotel.",
                "After she was driven Professor Kumar to the museum she had dropped him at his hotel.",
                1,"difficult","verbal");
        addQuestion(q69);

        Question q70 = new Question("Change the voice.\n" +
                "\n" +
                "Who is creating this mess?\n",
                "Who has been created this mess?",
                "By whom has this mess been created?",
                "By whom this mess is being created?",
                "By whom is this mess being created?",
                4,"difficult","verbal");
        addQuestion(q70);
    }

    //method to add questions to the database
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuizContract.QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty_level());
        cv.put(QuizContract.QuestionsTable.COLUMN_TYPE, question.getType());

        db.insert(QuizContract.QuestionsTable.TABLE_NAME,null,cv);
    }

    //Listing all the questions according to the quesry
    public List<Question> getQuestions(String difficulty, String type) {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c= db.rawQuery("SELECT * FROM quiz_questions WHERE difficulty_level=? AND type=?",new String[]{difficulty,type});
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty_level(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_DIFFICULTY)));
                question.setType(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_TYPE)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    //Method required to implement AndroidDatabaseManager
    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }

}
