package com.sven.ou.module.test.view.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sven.ou.R;
import com.sven.ou.common.base.BaseFragment;
import com.sven.ou.module.test.presenter.MainPresenter;
import com.sven.ou.navigation.Navigator;
import com.sven.ou.module.test.view.MainView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BaseFragment implements MainView {
    private static final String TAG = MainFragment.class.getName();

    @Inject Context context;
    @Inject LocationManager locationManager;
    @Inject Navigator navigator;
    @Inject MainPresenter mMainPresenter;

    @BindView(R.id.button1) Button button1;
    @BindView(R.id.button2) Button button2;
    @BindView(R.id.button3) Button button3;
    @BindView(R.id.button4) Button button4;
    @BindView(R.id.button5) Button button5;

    @SuppressLint("ValidFragment")
    public MainFragment(String fragmentId) {
        super(fragmentId);
    }

    public MainFragment() {}

    @Override
    public View onCreateFragmentView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_test_main, container, false);
        return view;
    }

    @OnClick(R.id.button1)
    public void button1Click(View view) {
        mMainPresenter.gotoSecondView();
    }
    @OnClick(R.id.button2)
    public void button2Click(View view) {
        mMainPresenter.gotoThirdView();
    }

    @OnClick(R.id.button3)
    public void button3Click(View view) {
        mMainPresenter.gotoThirdView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMainPresenter.setView(this);
        mMainPresenter.ini();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMainPresenter.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMainPresenter.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMainPresenter.destroy();
    }

    @Override
    public Context context() {
        return this.getActivity();
    }

}
