package com.sven.ou.module.lol.presenter;

import com.sven.ou.module.lol.activities.FilterVideoActivity;

import rx.Observer;

public interface FilterVideoPresenter {
    void setCurrentDataType(FilterVideoActivity.DataType dataType);
    void loadNextVideoPage(Observer observer);
    void loadVideoPage(Observer observer, int page);
    boolean isLoadingPageData();
    boolean hasLoadedAllIPages();
}
