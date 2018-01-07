package com.appsolute.rami.bbcsports.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.appsolute.rami.bbcsports.Utils.DbContract;

/**
 * Created by Rami on 1/5/2018.
 */

public class SportHelper extends SQLiteOpenHelper {

        public static final int DB_VERSION = 1;
        public static final String DB_NAME = "sport.db";
        public static final String COMMA_SEP = ",";
        public static final String TEXT_TYPE = " TEXT";

        public static final String CREATE_CIGARETTE_ENTRY_SQL =
                "CREATE TABLE "+ DbContract.SportEntries.TABLE_NAME+" ("
                        +DbContract.SportEntries._ID+" INTEGER PRIMARY KEY"+COMMA_SEP
                        +DbContract.SportEntries.COLUMN_TITLE+TEXT_TYPE+COMMA_SEP
                        +DbContract.SportEntries.COLUMN_DESCRIPTION+TEXT_TYPE+COMMA_SEP
                        +DbContract.SportEntries.COLUMN_IMG+TEXT_TYPE+COMMA_SEP
                        +DbContract.SportEntries.COLUMN_SOURCE+TEXT_TYPE+COMMA_SEP
                        +DbContract.SportEntries.COLUMN_DATE+TEXT_TYPE
                        +"  )";

        public static final String DELETE_CIGARETTE_ENTRY_SQL =
                "DROP TABLE IF EXISTS "+DbContract.SportEntries.TABLE_NAME;


        public SportHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_CIGARETTE_ENTRY_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DELETE_CIGARETTE_ENTRY_SQL);
            onCreate(sqLiteDatabase);
        }

    }

