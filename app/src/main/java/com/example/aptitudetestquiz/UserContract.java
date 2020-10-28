package com.example.aptitudetestquiz;

import android.provider.BaseColumns;

public final class UserContract {

    private UserContract() {
    }

    public static class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "user_table";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_1_NAME = "name";
        public static final String COLUMN_2_EMAIL = "email";
        public static final String COLUMN_3_PASSWORD = "password";

    }
}
