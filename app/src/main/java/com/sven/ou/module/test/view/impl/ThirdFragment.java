package com.sven.ou.module.test.view.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import com.sven.ou.R;
import com.sven.ou.common.base.BaseFragment;
import com.sven.ou.module.test.presenter.ThirdPresenter;
import com.sven.ou.module.test.view.ThirdView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ThirdFragment extends BaseFragment implements ThirdView{
    private static final String TAG = ThirdFragment.class.getName();

    @Inject Context context;
    @Inject LocationManager locationManager;
    @Inject ThirdPresenter mThirdPresenter;

    @BindView(R.id.playvideoplayer) Button btnPlayVideo;
    @BindView(R.id.pausevideoplayer) Button btnPauseVideo;
    @BindView(R.id.videoView) VideoView videoView;

    @SuppressLint("ValidFragment")
    public ThirdFragment(String fragmentId) {
        super(fragmentId);
    }

    public ThirdFragment() {}

    @OnClick(R.id.playvideoplayer)
    public void playVideo(View view) {
        mThirdPresenter.playVideo();
    }

    @OnClick(R.id.pausevideoplayer)
    public void pauseVideo(View view) {
        mThirdPresenter.pauseVideo();
    }


    @Override
    public View onCreateFragmentView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_test_third, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mThirdPresenter.setView(this);
        if(null == savedInstanceState){
            mThirdPresenter.ini(videoView);
        }
    }

    @Override
    public Context context() {
        return this.getActivity();
    }


    @Override
    public void onPause() {
        super.onPause();
        mThirdPresenter.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mThirdPresenter.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThirdPresenter.destroy();
    }

}
