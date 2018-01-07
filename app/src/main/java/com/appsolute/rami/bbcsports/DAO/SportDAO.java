package com.appsolute.rami.bbcsports.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.appsolute.rami.bbcsports.Entities.Sport;
import com.appsolute.rami.bbcsports.Helpers.SportHelper;
import com.appsolute.rami.bbcsports.Utils.DbContract;

import java.util.ArrayList;

/**
 * Created by Rami on 1/4/2018.
 */

public class SportDAO {


        SportHelper mDbHelper;

        public SportDAO(Context context) {
            mDbHelper = new SportHelper(context);
        }

        public long insertSport(Sport sport){
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DbContract.SportEntries.COLUMN_TITLE, sport.getTitle());
            cv.put(DbContract.SportEntries.COLUMN_DESCRIPTION, sport.getDescription());
            cv.put(DbContract.SportEntries.COLUMN_DATE, sport.getPublishedAt());
            cv.put(DbContract.SportEntries.COLUMN_SOURCE, sport.getSource());
            cv.put(DbContract.SportEntries.COLUMN_IMG, sport.getUrlToImage());
            return db.insert(DbContract.SportEntries.TABLE_NAME, null, cv);
        }

        public ArrayList<Sport> getAllSport(){
            SQLiteDatabase db = mDbHelper.getReadableDatabase();
            ArrayList<Sport> sports = new ArrayList<>();
            Cursor cursor = db.query(DbContract.SportEntries.TABLE_NAME, null, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Sport sport = new Sport();
                sport.setTitle(cursor.getString(1));
                sport.setDescription(cursor.getString(2));

                sport.setUrlToImage(cursor.getString(3));
                sport.setSource(cursor.getString(4));
                sport.setPublishedAt(cursor.getString(5));

                sports.add(sport);
                cursor.moveToNext();
            }
            cursor.close();
            return sports;
        }

        public void deleteAll(){
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            db.delete(DbContract.SportEntries.TABLE_NAME, null, null);
        }


    }

