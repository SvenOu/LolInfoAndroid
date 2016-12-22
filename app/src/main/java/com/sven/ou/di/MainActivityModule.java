
package com.sven.ou.di;

import android.content.Context;

import com.sven.ou.module.test.activities.MainActivity;
import com.sven.ou.module.test.model.LolApiTest;
import com.sven.ou.module.test.model.RecusionTest;
import com.sven.ou.module.test.presenter.MainPresenter;
import com.sven.ou.module.test.presenter.impl.MainPresenterImpl;
import com.sven.ou.module.test.view.impl.MainFragment;
import com.sven.ou.navigation.Navigator;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(
    injects = {MainActivity.class, MainFragment.class},
    addsTo = ApplicationModule.class,
    library = true
)
public class MainActivityModule {
    private final MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Named("activityContext")
    @Provides
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    MainPresenter provideMainPresenter(RecusionTest recusionTest, LolApiTest lolApiTest, Navigator navigator) {
        return new MainPresenterImpl(recusionTest, lolApiTest, navigator);
    }
}
