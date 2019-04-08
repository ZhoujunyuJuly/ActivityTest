package com.example.doubanmovie.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by zhoujunyu on 2019/4/1.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private static String[] info = {"正在热映", "即将上映"};

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return info.length;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }


    @Override
    public Fragment getItem(int i) {
        if (i == 1) {
            return TextFragment.textFragment();
        } else {
            return MovieFragment.mainFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return info[position];

    }
}
