package com.example.wbdemo.sqlite;

import android.database.Cursor;

import com.example.wbdemo.info.maindata.Emotion;
import com.example.wbdemo.info.maindata.StatusesBean;

import java.util.List;

/**
 * Created by zhoujunyu on 2020-03-18.
 */
public interface ISQLiteOperate {

    void execSQL(String sql,Object[] objects);
    void execSQLWB(String sql, List<StatusesBean> statusesBeanList);
    void execSQLEM(String sql, List<Emotion> emotionList);
    void close();
    void delete(String table);
    Cursor rawQuery(String sql);

}
