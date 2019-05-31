package com.example.wbdemo.business.main;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by zhoujunyu on 2019/5/30.
 */
public class TabEntity implements CustomTabEntity {

    private String mTitle;

    public TabEntity(String title) {
        mTitle = title;
    }

    @Override
    public String getTabTitle() {
        return mTitle;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
