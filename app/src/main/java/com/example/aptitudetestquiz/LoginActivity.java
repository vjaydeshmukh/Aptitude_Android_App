package com.example.aptitudetestquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button login_button,register_button;
    String EmailHolder,PasswordHolder;
    EditText email, password;
    Boolean editTextEmptyHolder;
    UserDBHelper dbhelper;
    Cursor cursor;
    String temp_password="NOT_FOUND";
    SQLiteDatabase UserDataBaseobj;
    String UserDataBaseQueryHolder;
    public static final String UserEmail=" ";
    boolean doubleBackToExistPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.click);

        login_button = (Button) findViewById(R.id.login);
        register_button = (Button) findViewById(R.id.registerhere);
        email = (EditText) findViewById(R.id.editEmailAddress);
        password = (EditText) findViewById(R.id.editPassword);

        dbhelper = new UserDBHelper(this);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                // calling method to check editText is empty or not
                checkEditTextStatus();
                //Calling login function
                LoginFunction();
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    public void LoginFunction(){
        if(editTextEmptyHolder){
            UserDataBaseobj = dbhelper.getWritableDatabase();
            //Querying the database for checking email and password entered
            cursor = UserDataBaseobj.query(UserContract.UserTable.TABLE_NAME,null," "+UserContract.UserTable.COLUMN_2_EMAIL + "=?" , new String[]{EmailHolder},null,null,null);

            while(cursor.moveToNext()){
                if(cursor.isFirst()){
                    cursor.moveToFirst();

                    temp_password = cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_3_PASSWORD));

                    cursor.close();
                }
            }
            checkFinalResult();
        }
        else{
            Toast.makeText(LoginActivity.this,"Please Enter Username and Password" ,Toast.LENGTH_SHORT).show();
        }

    }

    public void checkEditTextStatus(){
        EmailHolder = email.getText().toString();
        PasswordHolder = password.getText().toString();

        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){
            editTextEmptyHolder = false;
        }

        else{
            editTextEmptyHolder = true;
        }
    }

    public void checkFinalResult(){

        if(temp_password.equals(PasswordHolder)){
            Toast.makeText(LoginActivity.this,"Login Successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, DomainOptions.class);
            startActivity(intent);
        }

        else{
            Toast.makeText(LoginActivity.this, "Username or Password is Wrong, Please Try Again!", Toast.LENGTH_SHORT).show();

        }

        temp_password = "NOT_FOUND";
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
