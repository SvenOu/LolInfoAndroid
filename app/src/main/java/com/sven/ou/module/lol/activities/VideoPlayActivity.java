package com.sven.ou.module.lol.activities;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.sven.ou.R;
import com.sven.ou.common.base.BaseActivity;
import com.sven.ou.common.component.VideoEnabledWebChromeClient;
import com.sven.ou.common.component.VideoEnabledWebView;
import com.sven.ou.di.VideoPlayActivityModule;
import com.sven.ou.module.lol.entity.Video;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class VideoPlayActivity extends BaseActivity  {

    public static final String TAG = VideoPlayActivity.class.getSimpleName();

    public static final String KEY_VIDEO_DATA = "video_data";

    @BindView(R.id.nonVideoLayout) RelativeLayout nonVideoLayout;
    @BindView(R.id.videoLayout)  RelativeLayout videoLayout;
    @BindView(R.id.webView) VideoEnabledWebView webView;

    private Video video;
    private VideoEnabledWebChromeClient webChromeClient;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_video_play);
        init();
    }

    public static void StartVideoPlayActivity(Activity fromActivity, Video video){
        Intent intent = new Intent(fromActivity, VideoPlayActivity.class);
        intent.putExtra(KEY_VIDEO_DATA, video);
        fromActivity.startActivity(intent);
    }

    private void init() {
        View loadingView = getLayoutInflater().inflate(R.layout.loading_video_view, null);
        webChromeClient = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, loadingView, webView) {
            // Subscribe to standard events, such as onProgressChanged()...
            @Override
            public void onProgressChanged(WebView view, int progress) {
                // Your code...
            }
        };
        webChromeClient.setOnToggledFullscreen(new VideoEnabledWebChromeClient.ToggledFullscreenCallback() {
            @Override
            public void toggledFullscreen(boolean fullscreen) {
                // Your code to handle the full-screen change, for example showing and hiding the title bar. Example:
                if (fullscreen) {
                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14) {
                        //noinspection all
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                    }
                }
                else {
                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14) {
                        //noinspection all
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    }
                }
            }
        });
        webView.setWebChromeClient(webChromeClient);
        // Call private class InsideWebViewClient
        webView.setWebViewClient(new InsideWebViewClient());

        if(null != getIntent() && null != getIntent().getSerializableExtra(KEY_VIDEO_DATA)){
            video = (Video) getIntent().getSerializableExtra(KEY_VIDEO_DATA);
            String videoUrl = video.getContentVideoUrl();
            webView.loadUrl(videoUrl);
        }
    }

    private class InsideWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new VideoPlayActivityModule(this));
    }
}
