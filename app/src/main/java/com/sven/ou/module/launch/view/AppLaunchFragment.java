/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sven.ou.module.launch.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.sven.ou.R;
import com.sven.ou.common.base.BaseFragment;
import com.sven.ou.common.config.Config;
import com.sven.ou.module.launch.db.SearchHistory;
import com.sven.ou.module.lol.activities.MainViewActivity;
import com.sven.ou.navigation.ActivityScreenNavigator;

import javax.inject.Inject;

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

        initActiveAndroid();
        initImageLoader();
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

        if(Config.isDevelopMode())
            builder.writeDebugLogs(); // Remove for release app

        ImageLoaderConfiguration config = builder.build();
        ImageLoader.getInstance().init(config);
    }

    private void initActiveAndroid() {
        //当重新安装app的时候才会自动执行migrations和创建model对应的表
        final Configuration dbConfiguration = new Configuration.Builder(appContext).
                setDatabaseName(Config.getDataBasePrefix() + DB_NAME).
                setFormatType(Configuration.Builder.SQL_SCRIPT_XML_FORMAT).
                setDatabaseVersion(DB_VERSION).
                setModelClasses(SearchHistory.class).
                create();

        Single.create(new Single.OnSubscribe<Boolean>() {
            @Override
            public void call(SingleSubscriber<? super Boolean> singleSubscriber) {
                ActiveAndroid.initialize(dbConfiguration);
                // TODO: 2016/9/5  need remove
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                singleSubscriber.onSuccess(true);
            }
        })
         .subscribeOn(Schedulers.newThread())
         .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(Boolean result) {
                gotoMainScreen();
            }
        });

    }

    private void gotoMainScreen() {
        getActivity().startActivity(new Intent(getContext(), MainViewActivity.class));
        getActivity().finish();
    }
}
