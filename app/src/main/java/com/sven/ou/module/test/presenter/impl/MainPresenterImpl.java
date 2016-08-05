package com.sven.ou.module.test.presenter.impl;

import android.support.annotation.NonNull;

import com.sven.ou.module.test.model.LolApiTest;
import com.sven.ou.module.test.model.RecusionTest;
import com.sven.ou.module.test.presenter.MainPresenter;
import com.sven.ou.navigation.Navigator;
import com.sven.ou.module.test.view.MainView;

/**
 * Created by sven-ou on 2016/6/14.
 */
public class MainPresenterImpl implements MainPresenter {

    private RecusionTest recusionTest;
    private MainView mainView;
    private Navigator navigator;
    private LolApiTest lolApiTest;

    public MainPresenterImpl(RecusionTest recusionTest, LolApiTest lolApiTest, Navigator navigator) {
        this.lolApiTest = lolApiTest;
        this.recusionTest = recusionTest;
        this.navigator = navigator;
    }


    public void setView(@NonNull MainView mainView) {
        this.mainView = mainView;
    }

    public void ini(){
        recusionTest.testAll();
        lolApiTest.testDaiWaiLolVideoApi();
        lolApiTest.testDaiWaiLolDataApi();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.mainView = null;
    }

    public void gotoSecondView() {
        this.navigator.geoToSecondView(mainView.context());
    }

    public void gotoThirdView() {
        this.navigator.geoToThirdView(mainView.context());
    }

}
