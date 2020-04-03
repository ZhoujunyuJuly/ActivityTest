package com.example.wbdemo.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.wbdemo.info.maindata.Emotion;
import com.example.wbdemo.info.maindata.StatusesBean;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhoujunyu on 2020-03-18.
 */
public class SQLiteManager implements ISQLiteOperate {


    public static final String SQL_ERROR_TAG = "sql";
    private AtomicInteger mOpenCounter = new AtomicInteger();

    private SQLiteManager() {
    }

    private static JsonDbHelper mDBHelper;
    private static SQLiteManager mSqLiteManager;
    private SQLiteDatabase mDb;
    private Cursor mCursor;

    public static SQLiteManager getInstance(Context context){
        mDBHelper = JsonDbHelper.getInstance(context);
        if( mSqLiteManager == null ) {
            synchronized (SQLiteManager.class){
                if( mSqLiteManager == null ){
                    mSqLiteManager = new SQLiteManager();
                }
            }
        }
        return mSqLiteManager;
    }


    private SQLiteDatabase getSQLiteDataBase(){
        if( mOpenCounter.incrementAndGet() == 1 || mDb == null){
            mDb = mDBHelper.getWritableDatabase();
        }
        return mDb;
    }

    private void closeSQLiteDataBase(){
        if( mOpenCounter.decrementAndGet() == 0){
            mDb.close();
        }
    }

    @Override
    public void execSQL(String sql, Object[] objects) {
        mDb = getSQLiteDataBase();
        try{
            mDb.execSQL(sql,objects);
        }catch (Exception e){
            Log.e(SQL_ERROR_TAG, "execSQL: " + e.getMessage() + sql );
        }finally {
            closeSQLiteDataBase();
        }
    }

    @Override
    public void execSQLWB(String sql, List<StatusesBean> statusesBeanList) {
        mDb = getSQLiteDataBase();
        try {
            mDb.beginTransaction();
            for(StatusesBean item : statusesBeanList){
                mDb.execSQL(sql, new Object[]{null, item.getUser().getName(), item.getUser().getAvatar_hd(),
                        item.getCreated_at(), item.getText(), item.getPic_urls(), item.getAttitudes_count(),
                        item.getComments_count(), item.getReposts_count(), item.getShares_count()});
            }
            mDb.setTransactionSuccessful();
        }catch (Exception e){
            Log.e(SQL_ERROR_TAG, "execSQL: " + e.getMessage() + sql);
        }finally {
            mDb.endTransaction();
            closeSQLiteDataBase();
        }
    }

    @Override
    public void execSQLEM(String sql, List<Emotion> emotionList) {
        mDb = getSQLiteDataBase();
        try {
            mDb.beginTransaction();
            for (Emotion item : emotionList ){
                mDb.execSQL(sql,new Object[]{null,item.getValue(),item.getUrl()});
            }
            mDb.setTransactionSuccessful();
        }catch (Exception e){
            Log.e(SQL_ERROR_TAG, "execSQL: " + e.getMessage() + sql);
        }finally {
            mDb.endTransaction();
            closeSQLiteDataBase();
        }
    }

    @Override
    public void close() {
        if( mCursor != null){
            mCursor.close();
        }
        closeSQLiteDataBase();
    }

    @Override
    public void delete(String table) {
        mDb = getSQLiteDataBase();
        mDb.delete(table,null,null);
    }

    @Override
    public Cursor rawQuery(String sql) {
        mDb = getSQLiteDataBase();
        mCursor = mDb.rawQuery(sql,null);
        return mCursor;
    }
}
