package com.example.databasetest;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by zhoujunyu on 2019/5/16.
 */
public class MySQLHelper extends SQLiteOpenHelper {

    private Context mContext;
    public static final String CREATE_BOOK = "create table BOOK("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "page integer,"
            + "name text)";

    public MySQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext,"table is created!",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
