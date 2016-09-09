package com.sven.ou.module.lol.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lsjwzh.widget.recyclerviewpager.LoopRecyclerViewPager;
import com.sven.ou.R;
import com.sven.ou.common.base.BaseFragment;
import com.sven.ou.common.component.DotsView;
import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.lol.adapters.WeekFreeHerosAdapter;
import com.sven.ou.module.lol.entity.thisweek.Hero;
import com.sven.ou.module.lol.oberver.LolObserver;
import com.sven.ou.module.lol.presenter.AuthorInfoPresenter;
import com.sven.ou.navigation.Navigator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @Inject AuthorInfoPresenter authorInfoPresenter;
    @Inject SearchVideoDialog searchVideoDialog;
    @Inject Context applicationContext;
    @Inject Navigator navigator;
    @BindView(R.id.weekFreeHeroList) LoopRecyclerViewPager rvp_weekFreeHeros;
    @BindView(R.id.tabViewpager) ViewPager tabViewpager;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.mainToolbar) Toolbar toolbar;
    @BindView(R.id.toolbarLeftIcon) ImageView toolbarLeftIcon;
    @BindView(R.id.toolbarRightIcon) ImageView toolbarRightIcon;
    @BindView(R.id.toolbarTitle) TextView toolbarTitle;
    @BindView(R.id.mainFab) FloatingActionButton mainFab;
    @BindView(R.id.freeHeroDotsView) DotsView freeHeroDotsView;

    private WeekFreeHerosAdapter weekFreeHerosAdapter;
    private TabFragmentPagerAdapter tabFragmentPagerAdapter;

    @OnClick(R.id.toolbarLeftIcon)
    public void toolbarLeftIconClick(View view) {
        DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.activity_root);
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

    @SuppressLint("ValidFragment")
    public HomeFragment(String fragmentId) {
        super(fragmentId);
    }

    public HomeFragment() {}

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(
                R.layout.fra_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {

        if(null == tabFragmentPagerAdapter){
            setupViewPager();
            tabLayout.setupWithViewPager(tabViewpager);
        }

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
                weekFreeHerosAdapter = new WeekFreeHerosAdapter(applicationContext, heros, new WeekFreeHerosAdapter.Callback() {
                    @Override
                    public void onItemClick(WeekFreeHerosAdapter.ViewHolder viewHolder) {
                        navigator.goToFilterActivity(getActivity(), viewHolder.hero);
                    }

                    @Override
                    public void onItemChange(Hero hero, int totalCount, int location) {
                        freeHeroDotsView.updaetpage(totalCount, location);
                    }

                });
                rvp_weekFreeHeros.setAdapter(weekFreeHerosAdapter);
            }
        });
    }

    private void setupViewPager() {
        tabFragmentPagerAdapter = new TabFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        tabFragmentPagerAdapter.addFragment(new NewestVideoFragment(), getString(R.string.newest_video));
        tabFragmentPagerAdapter.addFragment(new AuthorInfoFragment(), getString(R.string.author_info));
        tabViewpager.setAdapter(tabFragmentPagerAdapter);
    }

    private static class TabFragmentPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public TabFragmentPagerAdapter(FragmentManager fm) {
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
    public void onDestroy() {
        searchVideoDialog.dismiss();
        super.onDestroy();
    }
}
