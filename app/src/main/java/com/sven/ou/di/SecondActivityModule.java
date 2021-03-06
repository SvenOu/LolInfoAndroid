
package com.sven.ou.di;

import android.content.Context;

import com.sven.ou.module.test.activities.SecondActivity;
import com.sven.ou.module.test.presenter.SecondPresenter;
import com.sven.ou.module.test.presenter.impl.SecondPresenterImpl;
import com.sven.ou.module.test.view.impl.SecondFragment;
import com.sven.ou.navigation.Navigator;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(
    injects = {SecondActivity.class, SecondFragment.class},
    addsTo = ApplicationModule.class,
    library = true
)
public class SecondActivityModule {
    // TODO put your application-specific providers here!

    private final SecondActivity activity;

    public SecondActivityModule(SecondActivity activity) {
        this.activity = activity;
    }

    @Named("activityContext")
    @Provides
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    SecondPresenter provideSecondPresenter(Context context, Navigator navigator){
        return new SecondPresenterImpl(context, navigator);
    }
}
