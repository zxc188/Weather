package com.example.administrator.weather.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/9/16.
 */

public class MyDBHolder extends SQLiteOpenHelper {
    private final static String DB_NAME = "wordsdb";
    private final static int DB_VERSION = 1;

    private final static String CREATE_TABEL = "create table " + DBdao.TABLE_NAME + "(" +
            DBdao._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            DBdao.COLUMN_ID + " text)";
    private final String DROP_TABEL = "drop tabel if exists"+DBdao.TABLE_NAME;


    public MyDBHolder(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABEL);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABEL);
        onCreate(db);
    }
}
