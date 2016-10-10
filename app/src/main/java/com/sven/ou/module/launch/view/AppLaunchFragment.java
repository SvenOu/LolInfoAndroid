package com.sven.ou.module.launch.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.activeandroid.util.Log;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.sven.ou.R;
import com.sven.ou.common.base.BaseFragment;
import com.sven.ou.common.config.Config;
import com.sven.ou.module.lol.activities.MainViewActivity;
import com.sven.ou.module.lol.db.Area_;
import com.sven.ou.module.lol.db.SearchHistory;
import com.sven.ou.module.lol.entity.Area;
import com.sven.ou.module.lol.hackdaiwan.TokenInfo;
import com.sven.ou.navigation.ActivityScreenNavigator;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class AppLaunchFragment extends BaseFragment {

    private static final String TAG = AppLaunchFragment.class.getSimpleName();

    private static final String DB_NAME = "lol_dbchg.sqlite";
    private static final int DB_VERSION = 1;
    public static final int DISK_CACHE_SIZE = 50 * 1024 * 1024;
    @Inject Context appContext;
    @Inject ProgressDialog progressDialog;

    public AppLaunchFragment() {
        super(ActivityScreenNavigator.KEY_APP_LAUNCH);
    }

    @Nullable
    @Override
    public View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fra_launch_info, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        initImageLoader();
        initConfig();
    }

    private void initImageLoader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.color.transparent)
                .showImageForEmptyUri(R.mipmap.ic_empty)
                .showImageOnFail(R.mipmap.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .build();
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
                appContext).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(DISK_CACHE_SIZE)
                .defaultDisplayImageOptions(options)
                .tasksProcessingOrder(QueueProcessingType.LIFO);

        if(Config.isDevelopMode()){
            builder.writeDebugLogs();
        }

        ImageLoaderConfiguration config = builder.build();
        ImageLoader.getInstance().init(config);
    }

    private boolean initDaiWanToken() {
        TokenInfo dataToken = TokenInfo.findAvalableTokenByType(TokenInfo.TOKEN_TYPE_DATA);
        if(null == dataToken || dataToken.tokenIsExpired()){
            try {
                progressDialog.show();
                TokenInfo.loginAndSaveAllToken(getActivity(), new TokenInfo.GetTokenSCallBack() {
                    @Override
                    public void onGetAllToken(String dataToken, String videoToken, String tentacleToken, Date expiredDate) {
                        Config.PUBLICK_LOL_REQUEST_TOKEN = dataToken;
                        Config.VIDEO_REQUEST_TOKEN = videoToken;
                        Config.TENTACLE_LOL_REQUEST_TOKEN = tentacleToken;
                        gotoMainScreen();
                    }
                });
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            return false;
        }else{
            TokenInfo videoToken = TokenInfo.findAvalableTokenByType(TokenInfo.TOKEN_TYPE_VIDEO);
            TokenInfo tentacleToken = TokenInfo.findAvalableTokenByType(TokenInfo.TOKEN_TYPE_TENTACLE);

            if(null != dataToken){
                Config.PUBLICK_LOL_REQUEST_TOKEN = dataToken.getToken();
            }
            if(null != videoToken){
                Config.VIDEO_REQUEST_TOKEN = videoToken.getToken();
            }
            if(null != tentacleToken){
                Config.TENTACLE_LOL_REQUEST_TOKEN = tentacleToken.getToken();
            }
            return true;
        }
    }

    private void initConfig() {
        //当重新安装app的时候才会自动执行migrations和创建model对应的表
        final Configuration dbConfiguration = new Configuration.Builder(appContext).
                setDatabaseName(Config.getDataBasePrefix() + DB_NAME).
                setFormatType(Configuration.Builder.SQL_SCRIPT_XML_FORMAT).
                setDatabaseVersion(DB_VERSION).
                setModelClasses(
                        SearchHistory.class,
                        TokenInfo.class,
                        Area_.class
                ).
                create();

        Single.create(new Single.OnSubscribe<Boolean>() {
            @Override
            public void call(SingleSubscriber<? super Boolean> singleSubscriber) {
                ActiveAndroid.initialize(dbConfiguration);
                singleSubscriber.onSuccess(true);
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Boolean token) {
                        if (initDaiWanToken()) {
                            gotoMainScreen();
                        };
                    }
                });
    }

    @Override
    public void onStop() {
        super.onStop();
        progressDialog.hide();
    }

    private void gotoMainScreen() {
        getActivity().startActivity(new Intent(getContext(), MainViewActivity.class));
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        WebView hackDaiWanWebview = (WebView) getActivity().findViewById(R.id.hackDaiWanWebview);
        hackDaiWanWebview.destroy();
        progressDialog.dismiss();
        super.onDestroy();
    }
}
