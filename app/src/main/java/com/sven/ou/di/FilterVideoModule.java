
package com.sven.ou.di;

import android.content.Context;

import com.sven.ou.module.lol.activities.FilterVideoActivity;
import com.sven.ou.module.lol.presenter.FilterVideoPresenter;
import com.sven.ou.module.lol.presenter.impl.FilterVideoPresenterImpl;
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
    injects = {FilterVideoActivity.class},
    addsTo = ApplicationModule.class,
    library = true
)
public class FilterVideoModule {
    private final FilterVideoActivity activity;

    public FilterVideoModule(FilterVideoActivity activity) {
        this.activity = activity;
    }

    @Named("activityContext")
    @Provides
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    FilterVideoPresenter provideMainPresenter(Context context, Navigator navigator) {
        return new FilterVideoPresenterImpl(context, navigator);
    }
}
