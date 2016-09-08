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

package com.sven.ou.module.lol.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.paginate.Paginate;
import com.sven.ou.R;
import com.sven.ou.common.base.BaseFragment;
import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.lol.activities.VideoPlayActivity;
import com.sven.ou.module.lol.adapters.NewestVideoViewAdapter;
import com.sven.ou.module.lol.entity.Video;
import com.sven.ou.module.lol.oberver.LolObserver;
import com.sven.ou.module.lol.presenter.NewestVideoPresenter;
import com.sven.ou.navigation.ActivityScreenNavigator;
import com.sven.ou.navigation.Navigator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class NewestVideoFragment extends BaseFragment implements Paginate.Callbacks {

    private static final String TAG = NewestVideoFragment.class.getSimpleName();
    private Paginate paginate;
    private NewestVideoViewAdapter newestVideoViewAdapter;
    private static final int ROW_VIEW_COUNT = 2;

    @BindView(R.id.newestVideoRecyclerview) RecyclerView newestVideoRecyclerview;
    @Inject Context applicationContext;
    @Inject NewestVideoPresenter newestVideoPresenter;
    @Inject Navigator navigator;

    public NewestVideoFragment() {
        super(ActivityScreenNavigator.KEY_NEWEAST_VIDEO);
    }

    @Nullable
    @Override
    public View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(
                R.layout.fra_newest_video, container, false);
    }

    /**
     * Called when next page of data needs to be loaded.
     */
    @Override
    public synchronized void onLoadMore() {
        newestVideoPresenter.loadNextNewestVideoPage(new LolObserver<DaiWanLolResult<List<Video>>>(applicationContext) {
            @Override
            public void onNext(DaiWanLolResult<List<Video>> listDaiWanLolResult) {
                List<Video> authorList = listDaiWanLolResult.getData();
                newestVideoViewAdapter.add(authorList);
            }
        });
    }

    /**
     * Called to check if loading of the next page is currently in progress. While loading is in progress
     * {@link Paginate.Callbacks#onLoadMore} won't be called.
     *
     * @return true if loading is currently in progress, false otherwise.
     */
    @Override
    public synchronized boolean isLoading() {
        return newestVideoPresenter.isLoadingPageData();
    }

    /**
     * Called to check if there is more data (more pages) to load. If there is no more pages to load, {@link
     * Paginate.Callbacks#onLoadMore} won't be called and loading row, if used, won't be added.
     *
     * @return true if all pages has been loaded, false otherwise.
     */
    @Override
    public boolean hasLoadedAllItems() {
        return newestVideoPresenter.hasLoadedAllIPages();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        // If RecyclerView was recently bound, unbind
        if (paginate != null) {
            paginate.unbind();
        }
        newestVideoRecyclerview.setLayoutManager(new GridLayoutManager(applicationContext, ROW_VIEW_COUNT));
        newestVideoViewAdapter = new NewestVideoViewAdapter(getActivity(), new ArrayList(0), new NewestVideoViewAdapter.CallBack() {
            @Override
            public void onItemClick(NewestVideoViewAdapter.ViewHolder viewHolder) {
                Video video = viewHolder.video;
                navigator.goToVideoPlayActivity(getActivity(), video);
            }
        });
        newestVideoRecyclerview.setAdapter(newestVideoViewAdapter);

        paginate = Paginate.with(newestVideoRecyclerview, this).build();
    }
}
