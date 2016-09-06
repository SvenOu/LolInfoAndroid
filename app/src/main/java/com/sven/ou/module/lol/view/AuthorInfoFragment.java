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
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paginate.Paginate;
import com.sven.ou.R;
import com.sven.ou.common.base.BaseFragment;
import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.lol.adapters.AuthorRecycleViewAdapter;
import com.sven.ou.module.lol.entity.Author;
import com.sven.ou.module.lol.oberver.LolObserver;
import com.sven.ou.module.lol.presenter.AuthorInfoPresenter;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import butterknife.BindView;

public class AuthorInfoFragment extends BaseFragment implements Paginate.Callbacks {

    private static final String TAG = AuthorInfoFragment.class.getSimpleName();
    private Paginate paginate;
    private AuthorRecycleViewAdapter authorRecycleViewAdapter;
    private View rootView;
    @BindView(R.id.recyclerview) RecyclerView authorInfoRecyclerview;
    @Inject Context applicationContext;
    @Inject AuthorInfoPresenter authorInfoPresenter;

    @Nullable
    @Override
    public View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(null == rootView){
            rootView = inflater.inflate(
                    R.layout.fra_author_info, container, false);
        }
        return rootView;
    }

    /**
     * Called when next page of data needs to be loaded.
     */
    @Override
    public synchronized void onLoadMore() {
        authorInfoPresenter.loadNextAuthorInfoPage(new LolObserver<DaiWanLolResult<List<Author>>>(applicationContext) {
            @Override
            public void onNext(DaiWanLolResult<List<Author>> listDaiWanLolResult) {
                List<Author> authorList = listDaiWanLolResult.getData();
                authorRecycleViewAdapter.add(authorList);
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
        return authorInfoPresenter.isLoadingPageData();
    }

    /**
     * Called to check if there is more data (more pages) to load. If there is no more pages to load, {@link
     * Paginate.Callbacks#onLoadMore} won't be called and loading row, if used, won't be added.
     *
     * @return true if all pages has been loaded, false otherwise.
     */
    @Override
    public boolean hasLoadedAllItems() {
        return authorInfoPresenter.hasLoadedAllIPages();
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
        authorInfoRecyclerview.setLayoutManager(new LinearLayoutManager(authorInfoRecyclerview.getContext()));
        authorRecycleViewAdapter = new AuthorRecycleViewAdapter(getActivity(), new ArrayList(0));
        authorInfoRecyclerview.setAdapter(authorRecycleViewAdapter);

        paginate = Paginate.with(authorInfoRecyclerview, this).build();
    }
}
