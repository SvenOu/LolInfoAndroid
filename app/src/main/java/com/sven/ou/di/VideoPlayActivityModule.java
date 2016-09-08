package com.sven.ou.di;

import android.content.Context;

import com.sven.ou.module.lol.activities.VideoPlayActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(
    injects = {VideoPlayActivity.class},//// TODO: 2016/8/26 add fragment
    addsTo = ApplicationModule.class,
    library = true
)
public class VideoPlayActivityModule {
    private final VideoPlayActivity activity;

    public VideoPlayActivityModule(VideoPlayActivity activity) {
        this.activity = activity;
    }

    @Named("activityContext")
    @Provides
    Context provideActivityContext() {
        return activity;
    }
}
