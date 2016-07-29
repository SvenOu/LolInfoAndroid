package com.sven.ou.module.module1.presenter;

import android.support.annotation.NonNull;

import com.sven.ou.module.module1.view.MainView;

/**
 * Created by sven-ou on 2016/7/18.
 */
public interface MainPresenter extends BasePresenter {
    void gotoSecondView();

    void gotoThirdView();

    void setView(@NonNull MainView mainView);

    void ini();

}
