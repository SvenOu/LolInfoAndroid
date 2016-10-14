package com.sven.ou.module.lol.presenter;

import rx.Observer;

public interface SearchUserPresenter {

    void loadUserArea(Observer observer);

    void getUserArea(String keyword, Observer observer);

    void getUserBaseInfo(String qquin,  int vaidObserver, Observer observer);
}
