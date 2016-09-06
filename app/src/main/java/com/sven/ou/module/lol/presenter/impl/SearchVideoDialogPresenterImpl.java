package com.sven.ou.module.lol.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.module.launch.db.SearchHistory;
import com.sven.ou.module.lol.entity.Video;
import com.sven.ou.module.lol.presenter.SearchVideoDialogPresenter;
import com.sven.ou.network.Network;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SearchVideoDialogPresenterImpl implements SearchVideoDialogPresenter{

    private Context appContext;

    //page start form 1.
    private int currentPage = 0;
    private boolean isLoading = false;
    private boolean hasLoadedAllIPages = false;

    public SearchVideoDialogPresenterImpl(Context appContext) {
        this.appContext = appContext;
    }

    @Override
    public boolean saveSearchHistory(SearchHistory searchHistory) {
        SearchHistory dataBaseHistory =
                new Select().from(SearchHistory.class).
                        where("content = ?", searchHistory.getContent()).executeSingle();
        if(null != dataBaseHistory){
            searchHistory.setHistoryId(dataBaseHistory.getHistoryId());
        }
        return (null != searchHistory.save());
    }

    @Override
    public List<SearchHistory> getSearchHistory(String keyWord, int maxResult) {
        List<SearchHistory> histories = new Select()
                .from(SearchHistory.class)
                .where("content LIKE ?", '%' + keyWord + '%')
                .orderBy("create_date DESC")
                .limit(maxResult)
                .execute();
        return histories;
    }

    @Override
    public void loadNextNewestVideoPage(Observer observer, String keyWord){
        loadNewestVideoPage(observer,keyWord, currentPage + 1);
    }

    @Override
    public void loadNewestVideoPage(Observer observer, String keyWord, final int page) {
        if(TextUtils.isEmpty(keyWord)){
            hasLoadedAllIPages = true;
            isLoading = false;
            return;
        }
        currentPage = page;
        isLoading = true;
        Network.getDaiWanLolVideoApi().findVideos(keyWord, page)
                .subscribeOn(Schedulers.newThread()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .map(new Func1<DaiWanLolResult<List<Video>>,
                        DaiWanLolResult<List<Video>>>() {
                    @Override
                    public DaiWanLolResult<List<Video>> call(DaiWanLolResult<List<Video>> listDaiWanLolResult) {
                        isLoading = false;
                        if(null == listDaiWanLolResult.getData() || listDaiWanLolResult.getData().size() <= 0){
                            hasLoadedAllIPages = true;
                        }else{
                            hasLoadedAllIPages = false;
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
