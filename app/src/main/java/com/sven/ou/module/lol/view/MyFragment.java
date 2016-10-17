package com.sven.ou.module.lol.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sven.ou.R;
import com.sven.ou.common.appdata.MemoryData;
import com.sven.ou.common.base.BaseFragment;
import com.sven.ou.navigation.ActivityScreenNavigator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MyFragment extends BaseFragment {
    private static final String TAG = MyFragment.class.getSimpleName();

    @BindView(R.id.userArea) TextView userArea;
    @Inject ActivityScreenNavigator activityScreenNavigator;
    @Inject MemoryData memoryData;

    @SuppressLint("ValidFragment")
    public MyFragment(String fragmentId) {
        super(fragmentId);
    }

    public MyFragment() {}

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fra_my, container, false);
        return rootView;
    }

    @OnClick(R.id.findUserBtn)
    public void findUser() {
        activityScreenNavigator.swicthScreenByKey(ActivityScreenNavigator.KEY_SEARCH_USER);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        if(null != memoryData.getCurSelectUserArea()){
            userArea.setText(String.valueOf(memoryData.getCurSelectUserArea().getArea_id()));
        }
    }
}
