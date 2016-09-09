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

package com.sven.ou.module.lol.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.paginate.Paginate;
import com.sven.ou.R;
import com.sven.ou.common.base.BaseActivity;
import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.di.FilterVideoModule;
import com.sven.ou.module.lol.adapters.NewestVideoViewAdapter;
import com.sven.ou.module.lol.adapters.WeekFreeHerosAdapter;
import com.sven.ou.module.lol.entity.Author;
import com.sven.ou.module.lol.entity.Video;
import com.sven.ou.module.lol.entity.thisweek.Hero;
import com.sven.ou.module.lol.oberver.LolObserver;
import com.sven.ou.module.lol.presenter.FilterVideoPresenter;
import com.sven.ou.navigation.Navigator;
import com.sven.ou.network.Network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FilterVideoActivity extends BaseActivity implements Paginate.Callbacks {

    private static final String TAG = FilterVideoActivity.class.getSimpleName();
    private static final String KEY_DATA_TYPE = "data_type";

    public static final String FILTER_TYPE_AUTHOR = "author";
    public static final String FILTER_TYPE_HERO = "hero";
    private Paginate paginate;
    private NewestVideoViewAdapter newestVideoViewAdapter;
    private static final int ROW_VIEW_COUNT = 2;

    @BindView(R.id.filterVideoRecyclerview) RecyclerView filterVideoRecyclerview;
    @BindView(R.id.dataImage) ImageView dataImage;
    @BindView(R.id.topToolbar) Toolbar topToolbar;
    @BindView(R.id.collapsingToolbar) CollapsingToolbarLayout collapsingToolbar;
    @Inject Context applicationContext;
    @Inject FilterVideoPresenter filterVideoPresenter;
    @Inject Navigator navigator;
    @Inject Context appContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_filter_video);
        init();
    }

    public static void startFilterVideoActivity(Activity fromActivity, String type, Hero hero, Author author){
        Intent intent = new Intent(fromActivity, FilterVideoActivity.class);
        DataType dataType = new DataType(type, author, hero);
        intent.putExtra(KEY_DATA_TYPE, dataType);
        fromActivity.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
             finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void init() {
        // If RecyclerView was recently bound, unbind
        if (paginate != null) {
            paginate.unbind();
        }
        setSupportActionBar(topToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(null != getIntent() && null != getIntent().getSerializableExtra(KEY_DATA_TYPE)){
            DataType dataType = (DataType) getIntent().getSerializableExtra(KEY_DATA_TYPE);
            filterVideoPresenter.setCurrentDataType(dataType);

            if(FILTER_TYPE_HERO.equals(dataType.getType())){
                Hero hero = dataType.getHero();
                collapsingToolbar.setTitle(hero.getTitle());
                Network.getDaiWanLolDataApi().getChampionIconById(Integer.parseInt(hero.getKey())).
                        subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(new WeekFreeHerosAdapter.LolHeroObserver<DaiWanLolResult<List<Map>>>(appContext, dataImage) {
                            @Override
                            public void onSuccess(DaiWanLolResult<List<Map>> listDaiWanLolResult, ImageView imageView) {
                                if(null == listDaiWanLolResult.getData() ||
                                        listDaiWanLolResult.getData().size() <= 0){
                                    Logger.e(TAG, "listDaiWanLolResult is not valid.");
                                }
                                String imageUrl = (String) listDaiWanLolResult.getData().get(0).get("return");
                                ImageLoader.getInstance().displayImage(imageUrl, dataImage);
                            }
                        });
            }else{
                // FILTER_TYPE_AUTHOR
                Author author = dataType.getAuthor();
                collapsingToolbar.setTitle(author.getName());
                ImageLoader.getInstance().displayImage(author.getImg(), dataImage);
            }
        }
        
        filterVideoRecyclerview.setLayoutManager(new GridLayoutManager(applicationContext, ROW_VIEW_COUNT));
        newestVideoViewAdapter = new NewestVideoViewAdapter(this, new ArrayList(0), new NewestVideoViewAdapter.CallBack() {
            @Override
            public void onItemClick(NewestVideoViewAdapter.ViewHolder viewHolder) {
                Video video = viewHolder.video;
                navigator.goToVideoPlayActivity(FilterVideoActivity.this, video);
            }
        });
        filterVideoRecyclerview.setAdapter(newestVideoViewAdapter);
        paginate = Paginate.with(filterVideoRecyclerview, this).build();
    }


    @Override
    public void onLoadMore() {
        filterVideoPresenter.loadNextVideoPage(new LolObserver<DaiWanLolResult<List<Video>>>(applicationContext) {
            @Override
            public void onNext(DaiWanLolResult<List<Video>> listDaiWanLolResult) {
                List<Video> authorList = listDaiWanLolResult.getData();
                newestVideoViewAdapter.add(authorList);
            }
        });
    }

    @Override
    public synchronized boolean isLoading() {
        return filterVideoPresenter.isLoadingPageData();
    }

    @Override
    public boolean hasLoadedAllItems() {
        return filterVideoPresenter.hasLoadedAllIPages();
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new FilterVideoModule(this));
    }


    public static class DataType implements Serializable{
        private String type;
        private Hero hero;
        private Author author;

        public DataType(String type, Author author, Hero hero) {
            this.author = author;
            this.hero = hero;
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Hero getHero() {
            return hero;
        }

        public void setHero(Hero hero) {
            this.hero = hero;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }
    }
}
