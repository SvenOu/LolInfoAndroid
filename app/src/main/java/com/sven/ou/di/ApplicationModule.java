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
import android.location.LocationManager;

import com.sven.ou.AndroidApplication;
import com.sven.ou.module.module1.model.FlavoursTest;
import com.sven.ou.module.module1.model.RecusionTest;
import com.sven.ou.module.module1.model.impl.FlavoursTestImpl;
import com.sven.ou.module.module1.model.impl.RecusionTestImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.LOCATION_SERVICE;

/**
 * 此module的管理的实例和application的生命周期绑定，
 * 其余的 xxx-ActivityModule管理的实例和各自的Activity或者Fragment的生命周期绑定，
 */
@Module(library = true)
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager() {
        return (LocationManager) application.getSystemService(LOCATION_SERVICE);
    }

    @Provides
    @Singleton
    RecusionTest provideRecusionTest(Context applicationContext) {
        return  new RecusionTestImpl(applicationContext);
    }

    @Provides
    @Singleton
    FlavoursTest provideFlavoursTest(Context applicationContext) {
        return  new FlavoursTestImpl(applicationContext);
    }
}
