package com.sven.ou.module.lol.view;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.sven.ou.R;
import com.sven.ou.common.base.BaseFragment;
import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.lol.adapters.SpinnerAreaAdapter;
import com.sven.ou.module.lol.entity.Area;
import com.sven.ou.module.lol.oberver.LolObserver;
import com.sven.ou.module.lol.presenter.SearchUserPresenter;

import java.util.List;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;

public class SearchUserFragment extends BaseFragment {

    private static final String TAG = SearchUserFragment.class.getSimpleName();

    @Inject SearchUserPresenter searchUserPresenter;
    @Inject Context appContext;
    @BindView(R.id.toolbarSearchField) EditText toolbarSearchField;
    @BindView(R.id.toolbarLeftIcon) ImageView toolbarLeftIcon;
    @BindView(R.id.toolbarRightIcon) ImageView toolbarRightIcon;
    @BindView(R.id.ryUserList) RecyclerView ryUserList;
    @BindView(R.id.spinnerArea) Spinner spinnerArea;

    private SpinnerAreaAdapter areaAdapter;

    @SuppressLint("ValidFragment")
    public SearchUserFragment(String fragmentId) {
        super(fragmentId);
    }

    public SearchUserFragment() {}

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fra_search_user, container, false);
        return rootView;
    }

    @OnClick(R.id.toolbarLeftIcon)
    public void toolbarLeftIconClick(View view) {
        DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.activity_root);
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    @OnClick(R.id.toolbarRightIcon)
    public void toolbarRightIconClick(View view) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        searchUserPresenter.loadUserArea(new LolObserver<DaiWanLolResult<List<Area>>>(appContext) {
            @Override
            public void onNext(DaiWanLolResult<List<Area>> daiWanLolResult) {
                if(null == daiWanLolResult || null == daiWanLolResult.getData()){
                    Logger.e(TAG, "area is empty.");
                    return;
                }
                areaAdapter = new SpinnerAreaAdapter(getContext(), daiWanLolResult.getData());
                spinnerArea.setAdapter(areaAdapter);
            }
        });
    }
}
