package com.example.administrator.weather.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/9/16.
 */

public class DBUtil {
    private Context context;
    private MyDBHolder myDBHolder;
    private String[] str ={"北京","上海","广州","银川","深圳","南京","苏州","石家庄","南宁","西安","哈尔滨","呼和浩特","天津","武汉","成都","厦门"} ;

    public DBUtil(Context context) {
        this.context = context;
        myDBHolder = new MyDBHolder(context);
    }

    public LinkedList<String> Getallcity() {
        LinkedList<String> linkedList = new LinkedList<>();
        SQLiteDatabase sqLiteDatabase = myDBHolder.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DBdao.TABLE_NAME, new String[]{DBdao.COLUMN_ID}, null, null, null, null, DBdao.COLUMN_ID + "ASC");
        while (cursor.moveToNext()) {
            linkedList.add(cursor.getString(cursor.getColumnIndex(DBdao.COLUMN_ID)));
        }
        return linkedList;
    }

    public void Add_all() {
        SQLiteDatabase sqLiteDatabase = myDBHolder.getWritableDatabase();
        for(int i=0;i<str.length;i++) {
            sqLiteDatabase.execSQL("insert into " + DBdao.TABLE_NAME+" values(null,?)", new String[]{str[i]});
        }
    }
    public void close() {
        myDBHolder.close();
    }
    public Cursor queryData(String key) {
        Cursor cursor = null;
        try {
            SQLiteDatabase sqLiteDatabase = myDBHolder.getReadableDatabase();
            String querySql = "select * from "+ DBdao.TABLE_NAME+" where "+DBdao.COLUMN_ID+" like '%" + key + "%'";
            cursor = sqLiteDatabase.rawQuery(querySql, null);
            Log.e("CSDN_LQR", "querySql = " + querySql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }
}
