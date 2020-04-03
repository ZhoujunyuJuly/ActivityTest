package com.example.wbdemo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.wbdemo.info.URLInfo.DBName;

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

    public static final String CREAT_EMOTION = "create table if not exists Emotion("
            + "id integer primary key autoincrement,"//自增长的ID
            + "value text,"       //表情名称 "[呵呵]"
            + "url text)"  ;      //表情地址

    private JsonDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private volatile static JsonDbHelper mJsonDbHelper;
    public static JsonDbHelper getInstance(Context context){
        if( mJsonDbHelper == null ){
            synchronized (JsonDbHelper.class){
                if( mJsonDbHelper == null ){
                    mJsonDbHelper = new JsonDbHelper(context,DBName,null,1);
                }
            }
        }
        return mJsonDbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_STATUSESBEAN);
        db.execSQL(CREAT_EMOTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
