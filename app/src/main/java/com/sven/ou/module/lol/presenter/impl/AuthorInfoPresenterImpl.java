package com.sven.ou.module.lol.presenter.impl;

import android.content.Context;

import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.lol.entity.Author;
import com.sven.ou.module.lol.entity.thisweek.Hero;
import com.sven.ou.module.lol.oberver.LolObserver;
import com.sven.ou.module.lol.presenter.AuthorInfoPresenter;
import com.sven.ou.network.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class AuthorInfoPresenterImpl implements AuthorInfoPresenter{
    private static final String TAG = AuthorInfoPresenterImpl.class.getSimpleName();
    //page start form 1.
    private int currentPage = 0;
    private boolean isLoading = false;
    private boolean hasLoadedAllIPages = false;
    private Context applicationContext;

    public AuthorInfoPresenterImpl(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void loadNextAuthorInfoPage(Observer observer){
        loadAuthorInfoPage(observer, currentPage + 1);
    }

    @Override
    public void loadAuthorInfoPage(Observer observer, final int page) {
        currentPage = page;
        isLoading = true;
        Network.getDaiWanLolVideoApi().getAuthors()
                .subscribeOn(Schedulers.newThread()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .map(new Func1<DaiWanLolResult<List<Author>>,
                        DaiWanLolResult<List<Author>>>() {
                    @Override
                    public DaiWanLolResult<List<Author>> call(DaiWanLolResult<List<Author>> listDaiWanLolResult) {
                        isLoading = false;
                        if(null == listDaiWanLolResult.getData()){
                            listDaiWanLolResult.setData(new ArrayList<Author>(0));
                        }
                        //这里没分页，只有一页所以直接设成true
                        hasLoadedAllIPages = true;
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

    @Override
    public void getWeekFreeHero(Observer observer) {
        Network.getDaiWanLolDataApi().getFree().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(observer);
    }
}
