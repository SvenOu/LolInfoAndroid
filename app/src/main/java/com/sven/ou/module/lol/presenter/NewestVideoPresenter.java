package com.sven.ou.module.lol.presenter;

import rx.Observer;

public interface NewestVideoPresenter {
    void loadNextNewestVideoPage(Observer observer);
    void loadNewestVideoPage(Observer observer, int page);
    boolean isLoadingPageData();
    boolean hasLoadedAllIPages();
}
