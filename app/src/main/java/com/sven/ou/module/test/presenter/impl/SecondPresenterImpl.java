package com.sven.ou.module.test.presenter.impl;

import android.content.Context;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.sven.ou.common.base.BaseActivity;
import com.sven.ou.module.test.presenter.BasePresenter;
import com.sven.ou.module.test.presenter.SecondPresenter;
import com.sven.ou.navigation.Navigator;
import com.sven.ou.module.test.view.SecondView;

import java.io.IOException;

/**
 * Created by sven-ou on 2016/6/14.
 */
public class SecondPresenterImpl implements BasePresenter, SurfaceHolder.Callback, SecondPresenter {

    private SecondView secondView;
    private Navigator navigator;
    private Context applicationContext;

    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;
    private Uri targetUri;
    boolean pausing = false;;

    public SecondPresenterImpl(Context context, Navigator navigator) {
        this.navigator = navigator;
        this.applicationContext = context;
    }

    @Override
    public void setView(@NonNull SecondView secondView) {
        this.secondView = secondView;
    }

    @Override
    public void ini(SurfaceView surfaceView){
        targetUri = Uri.parse("android.resource://"+ secondView.context().getPackageName()+"/raw/video_demo");
        ((BaseActivity)secondView.context()).getWindow().setFormat(PixelFormat.UNKNOWN);
        mediaPlayer = new MediaPlayer();
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
//        surfaceHolder.setFixedSize(176, 144);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
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

        if(mediaPlayer.isPlaying()){
            mediaPlayer.reset();
        }

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDisplay(surfaceHolder);

        try {
            mediaPlayer.setDataSource(applicationContext, targetUri);
            mediaPlayer.prepare();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();
    }

    @Override
    public void pauseVideo(){
        if(pausing){
            pausing = false;
            mediaPlayer.start();
        }
        else{
            pausing = true;
            mediaPlayer.pause();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void destroy() {

    }
}
