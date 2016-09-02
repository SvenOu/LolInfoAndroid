package com.sven.ou.module.lol.activities;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lsjwzh.widget.recyclerviewpager.LoopRecyclerViewPager;
import com.sven.ou.R;
import com.sven.ou.common.base.BaseActivity;
import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.di.MainViewActivityModule;
import com.sven.ou.module.lol.adapters.WeekFreeHerosAdapter;
import com.sven.ou.module.lol.entity.thisweek.Hero;
import com.sven.ou.module.lol.oberver.LolObserver;
import com.sven.ou.module.lol.presenter.AuthorInfoPresenter;
import com.sven.ou.module.lol.view.impl.AuthorInfoFragment;
import com.sven.ou.module.lol.view.impl.AuthorVideoFragment;
import com.sven.ou.module.lol.view.impl.HeroVideoFragment;
import com.sven.ou.module.lol.view.impl.NewestVideoFragment;
import com.sven.ou.module.lol.view.impl.VideoSearchFragment;
import com.sven.ou.module.test.activities.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainViewActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getName();
    @Inject Context applicationContext;
    @Inject AuthorInfoPresenter authorInfoPresenter;
    @BindView(R.id.weekFreeHeroList) LoopRecyclerViewPager rvp_weekFreeHeros;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.viewpager) ViewPager viewPager;

    private WeekFreeHerosAdapter weekFreeHerosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_black);

        ab.setDisplayHomeAsUpEnabled(true);


        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        if (viewPager != null) {
            setupViewPager(viewPager);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        authorInfoPresenter.getWeekFreeHero(new LolObserver<DaiWanLolResult<List<Map<String, Hero>>>>(applicationContext) {
            @Override
            public void onNext(DaiWanLolResult<List<Map<String, Hero>>> daiWanLolResult) {
                List<Map<String, Hero>> data = daiWanLolResult.getData();
                if(null == data || data.size() <= 0){
                    Logger.e(TAG, "data is valid .");
                    return;
                }
                //把map转换成list
                List<Hero> heros = new ArrayList<Hero>();
                for(String key: data.get(0).keySet()){
                    Hero hero = data.get(0).get(key);
                    hero.setKeyHero(key);
                    heros.add(hero);
                }
                rvp_weekFreeHeros.setLayoutManager(new LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false ));
                weekFreeHerosAdapter = new WeekFreeHerosAdapter(applicationContext, heros);
                rvp_weekFreeHeros.setAdapter(weekFreeHerosAdapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (AppCompatDelegate.getDefaultNightMode()) {
//            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
//                menu.findItem(R.id.menu_night_mode_system).setChecked(true);
//                break;
//            case AppCompatDelegate.MODE_NIGHT_AUTO:
//                menu.findItem(R.id.menu_night_mode_auto).setChecked(true);
//                break;
//            case AppCompatDelegate.MODE_NIGHT_YES:
//                menu.findItem(R.id.menu_night_mode_night).setChecked(true);
//                break;
//            case AppCompatDelegate.MODE_NIGHT_NO:
//                menu.findItem(R.id.menu_night_mode_day).setChecked(true);
//                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
//            case R.id.menu_night_mode_system:
//                setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
//                break;
//            case R.id.menu_night_mode_day:
//                setNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                break;
//            case R.id.menu_night_mode_night:
//                setNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                break;
//            case R.id.menu_night_mode_auto:
//                setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
        AppCompatDelegate.setDefaultNightMode(nightMode);

        if (Build.VERSION.SDK_INT >= 11) {
            recreate();
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new AuthorInfoFragment(), getString(R.string.author_info));
        adapter.addFragment(new AuthorVideoFragment(), getString(R.string.author_video));
        adapter.addFragment(new HeroVideoFragment(), getString(R.string.hero_video));
        adapter.addFragment(new NewestVideoFragment(), getString(R.string.newest_video));
        adapter.addFragment(new VideoSearchFragment(), getString(R.string.video_search));
        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MainViewActivityModule(this));
    }
}