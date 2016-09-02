/*
 * Copyright (C) 2013 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sven.ou.di;

import android.content.Context;

import com.sven.ou.module.lol.activities.MainViewActivity;
import com.sven.ou.module.lol.presenter.AuthorInfoPresenter;
import com.sven.ou.module.lol.presenter.NewestVideoPresenter;
import com.sven.ou.module.lol.presenter.impl.AuthorInfoPresenterImpl;
import com.sven.ou.module.lol.presenter.impl.NewestVideoPresenterImpl;
import com.sven.ou.module.lol.view.impl.AuthorInfoFragment;
import com.sven.ou.module.lol.view.impl.AuthorVideoFragment;
import com.sven.ou.module.lol.view.impl.HeroVideoFragment;
import com.sven.ou.module.lol.view.impl.NewestVideoFragment;
import com.sven.ou.module.lol.view.impl.VideoSearchFragment;
import com.sven.ou.module.test.model.LolApiTest;
import com.sven.ou.module.test.model.RecusionTest;
import com.sven.ou.module.test.presenter.MainPresenter;
import com.sven.ou.module.test.presenter.impl.MainPresenterImpl;
import com.sven.ou.navigation.Navigator;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(
    injects = {
            MainViewActivity.class,
            AuthorInfoFragment.class,
            AuthorVideoFragment.class,
            HeroVideoFragment.class,
            NewestVideoFragment.class,
            VideoSearchFragment.class
    },
    addsTo = ApplicationModule.class,
    library = true
)
public class MainViewActivityModule {
    private final MainViewActivity activity;

    public MainViewActivityModule(MainViewActivity activity) {
        this.activity = activity;
    }

    @Named("activityContext")
    @Provides
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    AuthorInfoPresenter provideAuthorInfoPresenter(Context context) {
        return new AuthorInfoPresenterImpl(context);
    }

    @Provides
    NewestVideoPresenter provideNewestVideoPresenter(Context context) {
        return new NewestVideoPresenterImpl(context);
    }
}