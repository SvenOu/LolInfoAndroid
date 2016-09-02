package com.sven.ou.module.lol.presenter.impl;

import android.content.Context;

import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.module.lol.entity.Author;
import com.sven.ou.module.lol.entity.Video;
import com.sven.ou.module.lol.presenter.AuthorInfoPresenter;
import com.sven.ou.module.lol.presenter.NewestVideoPresenter;
import com.sven.ou.network.Network;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class NewestVideoPresenterImpl implements NewestVideoPresenter{
    private static final String TAG = NewestVideoPresenterImpl.class.getSimpleName();
    //page start form 1.
    private int currentPage = 0;
    private boolean isLoading = false;
    private boolean hasLoadedAllIPages = false;
    private Context applicationContext;

    public NewestVideoPresenterImpl(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void loadNextNewestVideoPage(Observer observer){
        loadNewestVideoPage(observer, currentPage + 1);
    }

    @Override
    public void loadNewestVideoPage(Observer observer, final int page) {
        currentPage = page;
        isLoading = true;
        Network.getDaiWanLolVideoApi().getNewstVideos(page)
                .subscribeOn(Schedulers.newThread()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .map(new Func1<DaiWanLolResult<List<Video>>,
                        DaiWanLolResult<List<Video>>>() {
                    @Override
                    public DaiWanLolResult<List<Video>> call(DaiWanLolResult<List<Video>> listDaiWanLolResult) {
                        isLoading = false;
                        if(null == listDaiWanLolResult.getData()){
                            listDaiWanLolResult.setData(new ArrayList<Video>(0));
                            hasLoadedAllIPages = true;
                        }
                        return listDaiWanLolResult;
                    }
                })
                .subscribe(observer);
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
