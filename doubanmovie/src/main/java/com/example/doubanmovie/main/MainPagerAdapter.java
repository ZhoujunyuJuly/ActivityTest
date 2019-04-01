package com.example.doubanmovie.main;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by zhoujunyu on 2019/4/1.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 2;
    private MovieFragment movieFragment = null;
    private TextFragment textFragment = null;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        movieFragment = new MovieFragment();
        textFragment = new TextFragment();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
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
        Fragment fragment = null;
        switch (i){
            case 0:
                fragment = movieFragment;
                break;
            case 1:
                fragment = textFragment;
                break;
        }
        return fragment;
    }
}
