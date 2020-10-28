package com.example.aptitudetestquiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.service.autofill.UserData;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.jar.Attributes;


public class SignupActivity extends AppCompatActivity {

    EditText email,password,name;
    Button register_button,login_button;
    String NameHolder,PasswordHolder,EmailHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase UserDataBaseobj;
    String UserDataBaseQueryHolder;
    UserDBHelper dbhelper;
    Cursor cursor;
    String  F_Result = "NOT_FOUND";
    Boolean ValidEmailAddress = false;
    boolean doubleBackToExistPressedOnce;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.click);

        login_button = (Button) findViewById(R.id.login);
        register_button = (Button) findViewById(R.id.signup);
        name = (EditText) findViewById(R.id.editName);
        email = (EditText) findViewById(R.id.editEmailAddress);
        password = (EditText) findViewById(R.id.editPassword);

        dbhelper = new UserDBHelper(this);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                //Creating database if doesn't exist
                UserDatabaseBuild();
                //Creating table if doesn't exist
                UserTableBuild();
                //Checking editText empty or not
                CheckEditTextStatus();

                CheckValidEmailAddress();
                //Checking email exists or not
                CheckEmailAlreadyExistsorNot();
                //Empty EditText after registering
                EmptyEditText();
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    public void UserDatabaseBuild(){
         UserDataBaseobj = openOrCreateDatabase(UserDBHelper.DATABASE_NAME,Context.MODE_PRIVATE,null);
    }

    public void UserTableBuild(){
        UserDataBaseobj.execSQL("CREATE TABLE IF NOT EXISTS "+UserContract.UserTable.TABLE_NAME+
                "(" +UserContract.UserTable.COLUMN_ID+ "INTEGER PRIMARY KEY, "
                + UserContract.UserTable.COLUMN_1_NAME+ "VARCHAR, "
                +UserContract.UserTable.COLUMN_2_EMAIL+ "VARCHAR, "
                +UserContract.UserTable.COLUMN_3_PASSWORD+ "VARCHAR )");
    }

    public void InsertDataIntoUserDatabase(){
        if (EditTextEmptyHolder == true){
           UserDataBaseQueryHolder = "INSERT INTO "+UserContract.UserTable.TABLE_NAME+" (name,email,password) VALUES('"+NameHolder+"', '"+EmailHolder+"', '"+PasswordHolder+"');" ;

           UserDataBaseobj.execSQL(UserDataBaseQueryHolder);

           UserDataBaseobj.close();

           Toast.makeText(SignupActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(SignupActivity.this, "Please Fill All The Required Fields", Toast.LENGTH_SHORT).show();
        }
    }

    public void EmptyEditText(){
        name.getText().clear();
        email.getText().clear();
        password.getText().clear();
    }

    public void CheckEditTextStatus(){
        NameHolder = name.getText().toString();
        EmailHolder = email.getText().toString();
        PasswordHolder = password.getText().toString();

        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) ||TextUtils.isEmpty(PasswordHolder))
        {
            EditTextEmptyHolder = false;
            Toast.makeText(SignupActivity.this, "Please Fill All The Required Fields", Toast.LENGTH_SHORT).show();

        }
        else{
            EditTextEmptyHolder = true;
        }
    }

    public void CheckValidEmailAddress() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (EditTextEmptyHolder == true){
            if (email.getText().toString().trim().matches(emailPattern)) {
                Toast.makeText(SignupActivity.this, "Valid Email Address", Toast.LENGTH_SHORT).show();
                ValidEmailAddress = true;
            } else {
                Toast.makeText(SignupActivity.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                ValidEmailAddress = false;
            }

    }
    }
    public void CheckEmailAlreadyExistsorNot(){
        UserDataBaseobj = dbhelper.getWritableDatabase();
        if(ValidEmailAddress == true) {
            cursor = UserDataBaseobj.query(UserContract.UserTable.TABLE_NAME, null, " " + UserContract.UserTable.COLUMN_2_EMAIL + "=?", new String[]{EmailHolder}, null, null, null);

            while (cursor.moveToNext()) {
                if (cursor.isFirst()) {
                    cursor.moveToFirst();

                    F_Result = "Email Found";
                    cursor.close();
                }
            }
            checkFinalResult();
        }


        }


    public void checkFinalResult(){

        if(F_Result.equalsIgnoreCase("Email Found")){
            Toast.makeText(SignupActivity.this,"Email Already Exists", Toast.LENGTH_SHORT).show();
        }
        else{
            InsertDataIntoUserDatabase();
        }

        F_Result = "NOT_FOUND";

    }

    @Override
    public void onBackPressed() {
        if(doubleBackToExistPressedOnce) {
            finishAffinity();
            super.onBackPressed();
            return;
        }

        this.doubleBackToExistPressedOnce = true;
        Toast.makeText(this,"Please click BACK again to exist" , Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExistPressedOnce = false;
            }
        },2000);
    }
}