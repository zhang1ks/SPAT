package com.example.zhang1ks.testbottombar;

/**
 * Created by yuge on 11/10/2016.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ViewUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * Created by dm on 16-3-29.
 * The First Page of Bottom
 */
public class BottomFg1 extends Fragment {
    private TabLayout tabs;
    private DisplayMetrics dm;

    private TabFg1 tabFg1;
    private TabFg2 tabFg2;
    private TabFg3 tabFg3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg1_bottom, container, false);

        dm = getResources().getDisplayMetrics();

        ViewPager pager = (ViewPager)view. findViewById(R.id.pager);
        pager.setOffscreenPageLimit(0);//Set it to 0, the default was 2.
        tabs = (TabLayout) view.findViewById(R.id.tabs);
        pager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        tabs.setupWithViewPager(pager);
        return view;
    }



    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private final String[] titles = new String[]{"Community", "Trending", "Latest",};

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (tabFg1 == null) {
                        tabFg1 = new TabFg1();
                    }
                    return tabFg1;
                case 1:
                    if (tabFg2 == null) {
                        tabFg2 = new TabFg2();
                    }
                    return tabFg2;
                case 2:
                    if (tabFg3 == null) {
                        tabFg3 = new TabFg3();
                    }
                    return tabFg3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        private void setOverflowShowingAlways() {
            try {
                ViewConfiguration config = ViewConfiguration.get(getParentFragment().getActivity());
                Field menuKeyField = ViewConfiguration.class
                        .getDeclaredField("sHasPermanentMenuKey");
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}