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
