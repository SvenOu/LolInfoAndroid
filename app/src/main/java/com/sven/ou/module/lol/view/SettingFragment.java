package com.sven.ou.module.lol.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sven.ou.R;
import com.sven.ou.common.base.BaseFragment;

public class SettingFragment extends BaseFragment {

    @SuppressLint("ValidFragment")
    public SettingFragment(String fragmentId) {
        super(fragmentId);
    }

    public SettingFragment() {}

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fra_setting, container, false);
        return rootView;
    }
}
