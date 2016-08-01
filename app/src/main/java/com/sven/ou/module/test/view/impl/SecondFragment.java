package com.sven.ou.module.test.view.impl;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sven.ou.R;
import com.sven.ou.common.base.BaseFragment;
import com.sven.ou.module.test.presenter.SecondPresenter;
import com.sven.ou.module.test.view.SecondView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SecondFragment extends BaseFragment implements SecondView{
    private static final String TAG = SecondFragment.class.getName();

    @Inject Context context;
    @Inject LocationManager locationManager;
    @Inject
    SecondPresenter smScondPresenter;

    @BindView(R.id.playvideoplayer) Button btnPlayVideo;
    @BindView(R.id.pausevideoplayer) Button btnPauseVideo;
    @BindView(R.id.surfaceview) SurfaceView surfaceView;

    private Unbinder unbinder;


    @OnClick(R.id.playvideoplayer)
    public void playVideo(View view) {
        smScondPresenter.playVideo();
    }

    @OnClick(R.id.pausevideoplayer)
    public void pauseVideo(View view) {
        smScondPresenter.pauseVideo();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_second, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        smScondPresenter.setView(this);
        if(null == savedInstanceState){
            smScondPresenter.ini(surfaceView);
        }
    }

    @Override
    public Context context() {
        return this.getActivity();
    }


    @Override
    public void onPause() {
        super.onPause();
        smScondPresenter.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        smScondPresenter.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        smScondPresenter.destroy();
        unbinder.unbind();
    }

}
