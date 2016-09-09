package com.sven.ou.module.lol.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sven.ou.R;
import com.sven.ou.common.base.BaseFragment;

public class FriendFragment extends BaseFragment {

    @SuppressLint("ValidFragment")
    public FriendFragment(String fragmentId) {
        super(fragmentId);
    }

    public FriendFragment() {}

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fra_friend, container, false);
        return rootView;
    }
}
