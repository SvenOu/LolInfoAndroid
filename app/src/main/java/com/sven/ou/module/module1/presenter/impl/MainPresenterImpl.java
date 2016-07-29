package com.sven.ou.module.module1.presenter.impl;

import android.support.annotation.NonNull;

import com.sven.ou.module.module1.model.FlavoursTest;
import com.sven.ou.module.module1.model.RecusionTest;
import com.sven.ou.module.module1.presenter.MainPresenter;
import com.sven.ou.navigation.Navigator;
import com.sven.ou.module.module1.view.MainView;

/**
 * Created by sven-ou on 2016/6/14.
 */
public class MainPresenterImpl implements MainPresenter {

    private RecusionTest recusionTest;
    private MainView mainView;
    private Navigator navigator;
    private FlavoursTest flavoursTest;

    public MainPresenterImpl(RecusionTest recusionTest, FlavoursTest flavoursTest, Navigator navigator) {
        this.flavoursTest = flavoursTest;
        this.recusionTest = recusionTest;
        this.navigator = navigator;
    }


    public void setView(@NonNull MainView mainView) {
        this.mainView = mainView;
    }

    public void ini(){
        recusionTest.testAll();
        flavoursTest.printAllVariate();
        flavoursTest.testObservable();
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
