package com.sven.ou.module.lol.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.sven.ou.R;
import com.sven.ou.common.base.BaseActivity;
import com.sven.ou.di.MainViewActivityModule;
import com.sven.ou.module.test.activities.MainActivity;
import com.sven.ou.navigation.ActivityScreenNavigator;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import butterknife.BindView;


public class MainViewActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getName();
    @Inject Context applicationContext;
    @Inject ActivityScreenNavigator activityScreenNavigator;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.activity_root) DrawerLayout mDrawerLayout;

    private boolean backExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.act_main);
        activityScreenNavigator.swicthScreenByKey(ActivityScreenNavigator.KEY_HOME);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();
        switch (menuItem.getItemId()) {
            case R.id.nav_news:
                activityScreenNavigator.swicthScreenByKey(ActivityScreenNavigator.KEY_HOME);
                break;
            case R.id.nav_friends:
                activityScreenNavigator.swicthScreenByKey(ActivityScreenNavigator.KEY_FRIEND);
                break;
            case R.id.nav_discover:
                activityScreenNavigator.swicthScreenByKey(ActivityScreenNavigator.KEY_DISCOVER);
                break;
            case R.id.nav_my:
                // TODO: 2016/10/10
//                activityScreenNavigator.swicthScreenByKey(ActivityScreenNavigator.KEY_MY);
                activityScreenNavigator.swicthScreenByKey(ActivityScreenNavigator.KEY_SEARCH_USER);
                break;
            case R.id.nav_setting:
                activityScreenNavigator.swicthScreenByKey(ActivityScreenNavigator.KEY_SETTING);
                break;
            case R.id.nav_about_us:
                activityScreenNavigator.swicthScreenByKey(ActivityScreenNavigator.KEY_ABOUT_US);
                break;
        }
        return true;
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