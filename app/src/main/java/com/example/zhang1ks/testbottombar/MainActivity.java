package com.example.zhang1ks.testbottombar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView lvLeftMenu;
    private String[] lvs = {"List Item 01", "List Item 02", "List Item 03", "List Item 04"};
    private ArrayAdapter arrayAdapter;
    private ImageView ivRunningMan;
    private AnimationDrawable mAnimationDrawable;


    // Fragment
    private ViewPager bViewPager;
    private BottomFragmentPagerAdapter bAdapter;
    private List<Fragment> bFragments;
    private BottomFg1 fg1;
    private BottomFg2 fg2;
    private BottomFg4 fg4;
    private BottomFg5 fg5;
    private LinearLayout firstLayout;
    private LinearLayout secondLayout;
    private LinearLayout thirdLayout;
    private LinearLayout fourthLayout;
    private LinearLayout fifthLayout;
    private ImageView firstImage;
    private ImageView secondImage;
    private ImageView thirdImage;
    private ImageView fourthImage;
    private ImageView fifthImage;

    //Fg1_bottom TabLayout
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initView();//Initial Bottombar
        initPlus();//Initial Plus Action


    }

    //Initial the Plus Button
    private void initPlus() {
        thirdLayout = (LinearLayout) findViewById(R.id.third_layout);
        thirdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(i);
            }
        });
    }

    //Initial other four buttons except for Plus Button
    private void initView() {

        //Login Activity Missing, You are able to add a clickable icon here to go to the Login Activity which is gonna be created later.

//        titleLeftImv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            }
//        });

// Init Bottom Bar
        bViewPager = (ViewPager) findViewById(R.id.b_main_viewpager);

        firstImage = (ImageView) findViewById(R.id.first_image);
        secondImage = (ImageView) findViewById(R.id.second_image);
        // The third image is the Plus Button, which is used to open the new Posting Page. So the defination of it is deleted here and it is defined in initPlus() above.
//        thirdImage = (ImageView) findViewById(R.id.third_image);

        fourthImage = (ImageView) findViewById(R.id.fourth_image);
        fifthImage = (ImageView) findViewById(R.id.fifth_image);

        // If a textfiled below the image is necessary, you can add it here.
//        firstText = (TextView) findViewById(R.id.first_text);
//        secondText = (TextView) findViewById(R.id.second_text);
//        thirdText = (TextView) findViewById(R.id.third_text);
//        fourthText = (TextView) findViewById(R.id.fourth_text);

        //Init layouts including images.
        firstLayout = (LinearLayout) findViewById(R.id.first_layout);
        secondLayout = (LinearLayout) findViewById(R.id.second_layout);
//        thirdLayout = (LinearLayout) findViewById(R.id.third_layout);
        fourthLayout = (LinearLayout) findViewById(R.id.fourth_layout);
        fifthLayout = (LinearLayout) findViewById(R.id.fifth_layout);

        firstLayout.setOnClickListener(MainActivity.this);
        secondLayout.setOnClickListener(MainActivity.this);
//        thirdLayout.setOnClickListener(MainActivity.this);
        fourthLayout.setOnClickListener(MainActivity.this);
        fifthLayout.setOnClickListener(MainActivity.this);

        //Every Bottom Image will lead to a new Page(Fragment).
        bFragments = new ArrayList<Fragment>();
        Fragment b_01 = new BottomFg1();
        Fragment b_02 = new BottomFg2();
        Fragment b_04 = new BottomFg4();
        Fragment b_05 = new BottomFg5();
        bFragments.add(b_01);
        bFragments.add(b_02);
//        bFragments.add(b_03);
        bFragments.add(b_04);
        bFragments.add(b_05);

        bAdapter = new BottomFragmentPagerAdapter(getSupportFragmentManager(), bFragments);
        bViewPager.setAdapter(bAdapter);

        //Set Slide Listener
        bViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            //Change icon status by replacing the Image Resource while sliding
            @Override
            public void onPageSelected(int position) {
                int currentItem = bViewPager.getCurrentItem();
                initTabImage();
                switch (currentItem) {
                    case 0:
                        firstImage.setImageResource(R.drawable.homeb);
                        break;
                    case 1:
                        secondImage.setImageResource(R.drawable.profileb);
                        break;
//                    case 2:
//                        thirdImage.setImageResource(R.drawable.addcontent);
//                        break;
                    case 2:
                        fourthImage.setImageResource(R.drawable.channelsb);
                        break;
                    case 3:
                        fifthImage.setImageResource(R.drawable.searchb);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //Initial Tab Icons(while both sliding and clicking)
    private void initTabImage() {
        firstImage.setImageResource(R.drawable.home);
        secondImage.setImageResource(R.drawable.profile);
//        thirdImage.setImageResource(R.drawable.addcontent);
        fourthImage.setImageResource(R.drawable.channels);
        fifthImage.setImageResource(R.drawable.search);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.first_layout:
                // initTabImage();
                //mImageTabMain.setImageResource(R.drawable.tab_main_icon_green);
                setSelect(0);
                break;
            case R.id.second_layout:
                setSelect(1);
                break;
//            case R.id.third_layout:
//                setSelect(2);
//                break;
            case R.id.fourth_layout:
                setSelect(2);
                break;
            case R.id.fifth_layout:
                setSelect(3);
                break;
        }
    }

    //Change Icons clicked
    private void setSelect(int i) {

        initTabImage();
        switch (i) {
            case 0:
                firstImage.setImageResource(R.drawable.homeb);
                break;
            case 1:
                secondImage.setImageResource(R.drawable.profileb);
                break;
//            case 2:
//                thirdImage.setImageResource(R.drawable.addcontent);
//                break;
            case 2:
                fourthImage.setImageResource(R.drawable.channelsb);
                break;
            case 3:
                fifthImage.setImageResource(R.drawable.searchb);
                break;
            default:
                break;
        }
        bViewPager.setCurrentItem(i);
    }

}
//    //TabLayout
//    private void initTabViews() {
//
//        //
//        mViewPager = (ViewPager) findViewById(R.id.viewpager);
//        myFragmentPagerAdapter = new MyFragmentPagerAdapter();
//        mViewPager.setAdapter(myFragmentPagerAdapter);
//
//        //
//        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        mTabLayout.setupWithViewPager(mViewPager);
//
//        //
//        one = mTabLayout.getTabAt(0);
//        two = mTabLayout.getTabAt(1);
//        three = mTabLayout.getTabAt(2);
//    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.first_layout:
//                setChioceItem(0);
//                break;
//            case R.id.second_layout:
//                setChioceItem(1);
//                break;
//            case R.id.third_layout:
//                setChioceItem(2);
//                break;
//            case R.id.fourth_layout:
//                setChioceItem(3);
//                break;
//            case R.id.fifth_layout:
//                setChioceItem(4);
//                break;
//            default:
//                break;
//        }
//    }
//
//    /**
//     * set choice
//     *
//     * @param index choice：0, 1, 2, 3,4
//     */
//    private void setChioceItem(int index) {
//        clearChioce(); // Clear All Fragment
//        switch (index) {
//            case 0:
//                firstImage.setImageDrawable(getResources().getDrawable(R.drawable.homeb));
//                break;
//            case 1:
//                secondImage.setImageDrawable(getResources().getDrawable(R.drawable.profileb));
//                break;
//            case 2:
//                break;
//            case 3:
//                fourthImage.setImageDrawable(getResources().getDrawable(R.drawable.channelsb));
//                break;
//            case 4:
//                fifthImage.setImageDrawable(getResources().getDrawable(R.drawable.searchb));
//                break;
//        }
//        bViewPager.setCurrentItem(index);
//    }
//
//    /**
//     * when one choice selected, reset other choices
//     */
//    private void clearChioce() {
//        firstImage.setImageDrawable(getResources().getDrawable(R.drawable.home));
//        secondImage.setImageDrawable(getResources().getDrawable(R.drawable.profile));
//        fourthImage.setImageDrawable(getResources().getDrawable(R.drawable.channels));
//        fifthImage.setImageDrawable(getResources().getDrawable(R.drawable.search));
//    }
//}
