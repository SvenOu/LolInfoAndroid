package com.sven.ou.module.test.presenter;

import android.support.annotation.NonNull;
import android.view.SurfaceView;

import com.sven.ou.module.test.view.SecondView;

/**
 * Created by sven-ou on 2016/7/18.
 */
public interface SecondPresenter extends BasePresenter {
    void setView(@NonNull SecondView secondView);

    void ini(SurfaceView surfaceView);

    void playVideo();

    void pauseVideo();

}
