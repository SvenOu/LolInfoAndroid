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

import com.sven.ou.module.module1.activities.ThirdActivity;
import com.sven.ou.module.module1.presenter.ThirdPresenter;
import com.sven.ou.module.module1.presenter.impl.ThirdPresenterImpl;
import com.sven.ou.module.module1.view.impl.ThirdFragment;
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
