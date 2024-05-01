package com.example.newspaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.newspaper.Fragments.Trending;
import com.example.newspaper.Fragments.foryou;
import com.example.newspaper.Fragments.homepage1;

public class Fragment extends AppCompatActivity {
    ImageView grid2, profile2, home2;
    TextView todayspick, foryou, trending;
    ViewPager viewPager;
    homepage1 home;
    foryou for1;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);

        grid2 = findViewById(R.id.grid12);
        profile2 = findViewById(R.id.profile12);
        home2 = findViewById(R.id.home12);
        todayspick = findViewById(R.id.todayspick1);
        foryou = findViewById(R.id.foryou1);
        trending = findViewById(R.id.trending1);
        viewPager = findViewById(R.id.frame);
        sharedPreferences = getSharedPreferences("forLog", 0);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        home = new homepage1();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.frame, home);
//        transaction.commit();

        viewPager.setAdapter(new PagerAdapterClass(getSupportFragmentManager()));
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onPageSelected(int position) {
                todayspick.setTextColor(Color.BLACK);
                foryou.setTextColor(Color.BLACK);
                trending.setTextColor(Color.BLACK);
                if (position == 0) {
                    foryou.setTextColor(Color.RED);
                } else if (position == 1) {
                    todayspick.setTextColor(Color.RED);
                } else {
                    trending.setTextColor(Color.RED);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//
//        grid2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                g = new grid1();
//                FragmentManager manager = getSupportFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//                transaction.replace(R.id.frame, g);
//                transaction.commit();
//            }
//        });
//
//        profile2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Fragment.this, Profile.class));
//            }
//        });
//
//        home2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                home = new homepage1();
//                FragmentManager manager = getSupportFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//                transaction.replace(R.id.frame, home);
//                transaction.commit();
//            }
//        });


    }

    @SuppressLint("ResourceAsColor")
    public void setTabClickListener1(View view) {
        todayspick.setTextColor(Color.BLACK);
        foryou.setTextColor(Color.BLACK);
        trending.setTextColor(Color.BLACK);
        if (view.getId() == R.id.foryou1) {
            foryou.setTextColor(Color.RED);
            viewPager.setCurrentItem(0);
        } else if (view.getId() == R.id.todayspick1) {
            todayspick.setTextColor(Color.RED);

            viewPager.setCurrentItem(1);
        } else if (view.getId() == R.id.trending1) {
            trending.setTextColor(Color.RED);
            viewPager.setCurrentItem(2);
        }
    }

    public void setTabClickListener(View view) {
        if (view.getId() == R.id.grid12) {
            startActivity(new Intent(Fragment.this, grid.class));
        } else if (view.getId() == R.id.profile12) {
            startActivity(new Intent(Fragment.this, Profile.class));
        } else if (view.getId() == R.id.home12) {
            viewPager.setCurrentItem(1);
        }
    }


//    public void todays() {
//        home = new homepage1();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.frame, home);
//        transaction.commit();
//    }
//
//    public void foryou1() {
//        for1 = new foryou();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.frame, for1);
//        transaction.commit();
//    }

    public void showPopupMenu(View view) {
        PopupMenu pop = new PopupMenu(this, view);
        getMenuInflater().inflate(R.menu.homepagemenu, pop.getMenu());

        pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.item3:
                        sharedPreferences.edit().putBoolean("rememberMe", false).apply();
                        startActivity(new Intent(Fragment.this, MainActivity.class));
                        break;

                    case R.id.item2:
                        startActivity(new Intent(Fragment.this, Retrieve_Database.class));
                        break;
//                    case R.id.item:
//                        startActivity(new Intent(Fragment.this, NavigationDrawer.class));
//                        break;

                }
                return false;
            }
        });
        pop.show();
    }

    public class PagerAdapterClass extends FragmentPagerAdapter {

        public PagerAdapterClass(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public androidx.fragment.app.Fragment getItem(int position) {
            if (position == 1) {
                return new homepage1();
            }
            if (position == 0) {
                return new foryou();
            }
            if (position == 2) {
                return new Trending();
            } else {
                return new Trending();
            }

        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
