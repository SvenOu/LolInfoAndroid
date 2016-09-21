package com.sven.ou.module.lol.presenter;

import com.sven.ou.module.lol.db.SearchHistory;

import java.util.List;

import rx.Observer;

public interface SearchVideoDialogPresenter {
    boolean saveSearchHistory(SearchHistory searchHistory);
    List<SearchHistory> getSearchHistory(String keyWord, int maxResult);

    void loadNextNewestVideoPage(Observer observer, String keyWord);
    void loadNewestVideoPage(Observer observer, String keyWord, int page);
    boolean isLoadingPageData();
    boolean hasLoadedAllIPages();
}
