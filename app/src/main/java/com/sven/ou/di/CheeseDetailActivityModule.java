
package com.sven.ou.di;

import android.content.Context;

import com.sven.ou.module.lol.activities.CheeseDetailActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(
    injects = {CheeseDetailActivity.class},//// TODO: 2016/8/26 add fragment
    addsTo = ApplicationModule.class,
    library = true
)
public class CheeseDetailActivityModule {
    private final CheeseDetailActivity activity;

    public CheeseDetailActivityModule(CheeseDetailActivity activity) {
        this.activity = activity;
    }

    @Named("activityContext")
    @Provides
    Context provideActivityContext() {
        return activity;
    }
}
