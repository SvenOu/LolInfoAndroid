
package com.sven.ou.di;

import android.content.Context;

import com.sven.ou.module.test.activities.ThirdActivity;
import com.sven.ou.module.test.presenter.ThirdPresenter;
import com.sven.ou.module.test.presenter.impl.ThirdPresenterImpl;
import com.sven.ou.module.test.view.impl.ThirdFragment;
import com.sven.ou.navigation.Navigator;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(
    injects = {ThirdActivity.class, ThirdFragment.class},
    addsTo = ApplicationModule.class,
    library = true
)
public class ThirdActivityModule {
    private final ThirdActivity activity;
    public ThirdActivityModule(ThirdActivity activity) {
        this.activity = activity;
    }
    @Named("activityContext")
    @Provides
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    ThirdPresenter provideThirdPresenter(Context context, Navigator navigator){
        return new ThirdPresenterImpl(context, navigator);
    }

}
