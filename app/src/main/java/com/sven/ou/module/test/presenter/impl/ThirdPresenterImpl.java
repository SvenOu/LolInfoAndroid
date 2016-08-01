package com.sven.ou.module.test.presenter.impl;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.VideoView;

import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.test.presenter.BasePresenter;
import com.sven.ou.module.test.presenter.ThirdPresenter;
import com.sven.ou.navigation.Navigator;
import com.sven.ou.module.test.view.ThirdView;

import java.lang.ref.WeakReference;

/**
 * Created by sven-ou on 2016/6/14.
 */
public class ThirdPresenterImpl implements BasePresenter, ThirdPresenter {
    private static final String TAG = ThirdPresenterImpl.class.getName();
    private ThirdView thirdView;
    private Navigator navigator;
    private Context applicationContext;

    private WeakReference<VideoView> weakRefVideoView;
    private Uri targetUri;
    boolean pausing = false;;

    public ThirdPresenterImpl(Context context, Navigator navigator) {
        this.navigator = navigator;
        this.applicationContext = context;
    }

    @Override
    public void setView(@NonNull ThirdView thirdView) {
        this.thirdView = thirdView;
    }

    @Override
    public void ini(VideoView videoView){
        weakRefVideoView = new WeakReference<VideoView>(videoView);
        targetUri = Uri.parse("android.resource://"+ thirdView.context().getPackageName()+"/raw/video_demo");
        videoView.setVideoURI(targetUri);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }


    @Override
    public void playVideo(){
        pausing = false;
        VideoView videoView = weakRefVideoView.get();
        if(null == videoView){
            Logger.e(TAG, "videoView is null.");
            return;
        }
        if(videoView.isPlaying()){
            videoView.seekTo(0);
        }else{
            videoView.start();
        }
    }

    @Override
    public void pauseVideo(){
        VideoView videoView = weakRefVideoView.get();
        if(null == videoView){
            Logger.e(TAG, "videoView is null.");
            return;
        }
        if(pausing){
            pausing = false;
            videoView.start();
        }
        else{
            pausing = true;
            videoView.pause();
        }
    }

    @Override
    public void destroy() {

    }
}
