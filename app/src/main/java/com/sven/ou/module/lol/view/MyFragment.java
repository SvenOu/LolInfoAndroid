package com.sven.ou.module.lol.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sven.ou.R;
import com.sven.ou.common.base.BaseFragment;

public class MyFragment extends BaseFragment {

    public MyFragment(String fragmentId) {
        super(fragmentId);
    }

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fra_my, container, false);
        return rootView;
    }
}
