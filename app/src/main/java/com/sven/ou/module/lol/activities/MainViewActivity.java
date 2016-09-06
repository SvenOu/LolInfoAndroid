package com.sven.ou.module.lol.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.sven.ou.module.lol.view.AuthorInfoFragment;
import com.sven.ou.module.lol.view.AuthorVideoFragment;
import com.sven.ou.module.lol.view.HeroVideoFragment;
import com.sven.ou.module.lol.view.NewestVideoFragment;
import com.sven.ou.module.lol.view.SearchVideoDialog;
import com.sven.ou.module.lol.view.VideoSearchFragment;
import com.sven.ou.module.test.activities.MainActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;


public class MainViewActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getName();
    @Inject Context applicationContext;
    @Inject AuthorInfoPresenter authorInfoPresenter;
    @Inject SearchVideoDialog searchVideoDialog;
    @BindView(R.id.weekFreeHeroList) LoopRecyclerViewPager rvp_weekFreeHeros;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.mainToolbar) Toolbar toolbar;
    @BindView(R.id.toolbarLeftIcon) ImageView toolbarLeftIcon;
    @BindView(R.id.toolbarRightIcon) ImageView toolbarRightIcon;
    @BindView(R.id.toolbarTitle) TextView toolbarTitle;
    @BindView(R.id.mainFab) FloatingActionButton mainFab;

    private WeekFreeHerosAdapter weekFreeHerosAdapter;

    private boolean backExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @OnClick(R.id.toolbarLeftIcon)
    public void toolbarLeftIconClick(View view) {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }
    @OnClick(R.id.toolbarRightIcon)
    public void toolbarRightIconClick(View view) {
        searchVideoDialog.show();
    }

    @OnClick(R.id.mainFab)
    public void mainFabClick(View view) {
        Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void init() {
        setContentView(R.layout.act_main);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        if (viewPager != null) {
            setupViewPager(viewPager);
        }
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

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new AuthorInfoFragment(), getString(R.string.author_info));
        adapter.addFragment(new AuthorVideoFragment(), getString(R.string.author_video));
        adapter.addFragment(new HeroVideoFragment(), getString(R.string.hero_video));
        adapter.addFragment(new NewestVideoFragment(), getString(R.string.newest_video));
        adapter.addFragment(new VideoSearchFragment(), getString(R.string.video_search));
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();
        switch (menuItem.getItemId()) {
            case R.id.nav_news:
                toast((String) menuItem.getTitle());
                break;
            case R.id.nav_friends:
                toast((String) menuItem.getTitle());
                break;
            case R.id.nav_discover:
                toast((String) menuItem.getTitle());
                break;
            case R.id.nav_my:
                toast((String) menuItem.getTitle());
                break;
            case R.id.nav_setting:
                toast((String) menuItem.getTitle());
                break;
            case R.id.nav_about_us:
                toast((String) menuItem.getTitle());
                break;
        }
        return true;
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
    protected void onDestroy() {
        searchVideoDialog.dismiss();

        super.onDestroy();
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MainViewActivityModule(this));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(backExit){
                finish();
                System.exit(0);
            }else{
                backExit = true;
                toast(R.string.pressBackExit);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backExit = false;
                }
            }, 2000);
        }
        return false;
    }
}