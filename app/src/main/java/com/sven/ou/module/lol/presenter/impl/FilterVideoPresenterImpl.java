package com.sven.ou.module.lol.presenter.impl;

import android.content.Context;

import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.module.lol.activities.FilterVideoActivity;
import com.sven.ou.module.lol.entity.Video;
import com.sven.ou.module.lol.presenter.FilterVideoPresenter;
import com.sven.ou.navigation.Navigator;
import com.sven.ou.network.Network;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class FilterVideoPresenterImpl implements FilterVideoPresenter{
    private static final String TAG = FilterVideoPresenterImpl.class.getSimpleName();
    //page start form 1.
    private int currentPage = 0;
    private boolean isLoading = false;
    private boolean hasLoadedAllIPages = false;
    private Context applicationContext;
    private Navigator navigator;
    private FilterVideoActivity.DataType dataType;

    public FilterVideoPresenterImpl(Context applicationContext, Navigator navigator) {
        this.applicationContext = applicationContext;
        this.navigator = navigator;
    }


    @Override
    public void setCurrentDataType(FilterVideoActivity.DataType dataType) {
        this.dataType = dataType;
    }

    @Override
    public void loadNextVideoPage(Observer observer){
        loadVideoPage(observer, currentPage + 1);
    }

    @Override
    public void loadVideoPage(Observer observer, final int page) {
        currentPage = page;
        isLoading = true;

        Func1<DaiWanLolResult<List<Video>>, DaiWanLolResult<List<Video>>> fun1 = new Func1<DaiWanLolResult<List<Video>>,
                DaiWanLolResult<List<Video>>>() {

            @Override
            public DaiWanLolResult<List<Video>> call(DaiWanLolResult<List<Video>> listDaiWanLolResult) {
                isLoading = false;
                if (null == listDaiWanLolResult.getData() || listDaiWanLolResult.getData().size() <= 0) {
                    hasLoadedAllIPages = true;
                } else {
                    hasLoadedAllIPages = false;
                }
                return listDaiWanLolResult;
            }
        };

        if(dataType.getType().equals(FilterVideoActivity.FILTER_TYPE_HERO)){
            Network.getDaiWanLolVideoApi().getHeroVideos(Integer.parseInt(dataType.getHero().getKey()), page)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                    .map(fun1)
                    .subscribe(observer);
        }else{
            //FilterVideoActivity.FILTER_TYPE_AUTHOR
            Network.getDaiWanLolVideoApi().getAuthorVideos(dataType.getAuthor().getId(),page)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(fun1)
                    .subscribe(observer);
        }

    }

    @Override
    public boolean isLoadingPageData() {
        return isLoading;
    }

    @Override
    public boolean hasLoadedAllIPages() {
        return hasLoadedAllIPages;
    }
}
