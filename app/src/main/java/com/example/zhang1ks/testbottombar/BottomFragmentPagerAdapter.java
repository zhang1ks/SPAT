package com.example.zhang1ks.testbottombar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zhang1ks on 2016-11-15.
 */

public class BottomFragmentPagerAdapter extends FragmentPagerAdapter {
    //继承FragmentPagerAdapter类 ,并自定义的构造器
    private List<Fragment> fragments;
    public BottomFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments =fragments;
    }

    @Override
    public Fragment getItem(int position) {


        return fragments.get(position);

    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}