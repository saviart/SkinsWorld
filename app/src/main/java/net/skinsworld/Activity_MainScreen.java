package net.skinsworld;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import net.skinsworld.adapter.Adapter_MainScreen;
import net.skinsworld.fragment_mainscreen.Fragment_Earn;
import net.skinsworld.fragment_mainscreen.Fragment_Profile;
import net.skinsworld.fragment_mainscreen.Fragment_Community;
import net.skinsworld.fragment_mainscreen.Fragment_Skin;
import net.skinsworld.library.DatabaseHandler;
import net.skinsworld.library.GlobalVariables;


public class Activity_MainScreen extends AppCompatActivity {
    public ViewPager mViewPager;
    public static Activity_MainScreen main;
    private ActionBar toolbar;
    private BottomNavigationView navigation;
    public static Activity AM;


    public void selectIndex(int newIndex) {
        mViewPager.setCurrentItem(newIndex);
    }

    @Override
    public void onBackPressed() {
        int currentPosition = mViewPager.getCurrentItem();
        if (currentPosition != 0) {
            mViewPager.setCurrentItem(currentPosition-1);
        } else {

            Toast.makeText(this.getApplicationContext(),"Bấm back 1 lần nữa để thoát ứng dụng",Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AM = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setSoftInputMode(WindowManager.
                LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_mainscreen);
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        toolbar = getSupportActionBar();
        initUI();
        setUI();
        main = this;
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //navigation.setItemIconTintList(null);
        eventViewpager();
        //mặc định set vị trí là profile
        navigation.setSelectedItemId(R.id.navigation_profile);

        if(GlobalVariables.user.getActive().equals("0")){
            Intent intent = new Intent(Activity_MainScreen.this,Activity_UserBlocked.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }


//
    }







    private void eventViewpager() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_skins);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_profile);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_earn);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_community);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {


            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_skins:
//
                    selectIndex(0);


                    return true;
                case R.id.navigation_profile:

                    selectIndex(1);


                    return true;
                case R.id.navigation_earn:

                    selectIndex(2);


                    return true;
                case R.id.navigation_community:

                    selectIndex(3);


                    return true;
            }
            return false;
        }
    };


    private void initUI() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager_main);

    }

    private void setUI() {
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view = new View(getBaseContext());
                container.addView(view);
                return view;
            }
        });


        Adapter_MainScreen adapter = new Adapter_MainScreen(getSupportFragmentManager());
        adapter.AddFragment(new Fragment_Skin(), "Skins");
        adapter.AddFragment(new Fragment_Profile(), "Profile");
        adapter.AddFragment(new Fragment_Earn(), "Earn");
        adapter.AddFragment(new Fragment_Community(), "Setting");
        mViewPager = (ViewPager) findViewById(R.id.viewpager_main);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(1);//selected position


    }

    public ViewPager getViewPager() {
        return mViewPager;
    }
}