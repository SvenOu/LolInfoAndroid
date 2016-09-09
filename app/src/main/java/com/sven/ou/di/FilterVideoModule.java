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
