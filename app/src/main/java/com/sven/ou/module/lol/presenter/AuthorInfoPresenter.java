package com.sven.ou.module.lol.presenter;

import rx.Observer;

public interface AuthorInfoPresenter {
    void loadNextAuthorInfoPage(Observer observer);
    void loadAuthorInfoPage(Observer observer, int page);
    boolean isLoadingPageData();
    boolean hasLoadedAllIPages();

    void getWeekFreeHero(Observer observer);
}
