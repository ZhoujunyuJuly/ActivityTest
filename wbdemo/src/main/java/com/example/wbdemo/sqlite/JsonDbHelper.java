package com.example.wbdemo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhoujunyu on 2019/7/22.
 */
public class JsonDbHelper extends SQLiteOpenHelper {

    public static final String CREAT_STATUSESBEAN = "create table if not exists StatusesBean("
            + "id integer primary key autoincrement,"//自增长的ID
            + "name text,"       //微博昵称
            + "portrait text,"   //微博头像
            + "time text,"       //发布时间
            + "content text,"    //内容
            + "image text,"      //照片
            + "attitude integer,"//点赞数
            + "comment integer," //评论数
            + "repost integer,"  //转发数
            + "share integer)";  //分享数

    private Context mContext;

    public JsonDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_STATUSESBEAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
