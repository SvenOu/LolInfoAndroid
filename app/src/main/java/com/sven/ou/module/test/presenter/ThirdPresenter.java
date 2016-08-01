package com.sven.ou.module.test.presenter;

import android.support.annotation.NonNull;
import android.widget.VideoView;

import com.sven.ou.module.test.view.ThirdView;

/**
 * Created by sven-ou on 2016/7/18.
 */
public interface ThirdPresenter extends BasePresenter {
    void setView(@NonNull ThirdView thirdView);

    void ini(VideoView videoView);

    void playVideo();

    void pauseVideo();
}
