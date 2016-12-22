
package com.sven.ou.di;

import android.content.Context;
import android.location.LocationManager;

import com.sven.ou.LolApplication;
import com.sven.ou.common.appdata.MemoryData;
import com.sven.ou.module.test.model.LolApiTest;
import com.sven.ou.module.test.model.RecusionTest;
import com.sven.ou.module.test.model.impl.LolApiTestImpl;
import com.sven.ou.module.test.model.impl.RecusionTestImpl;

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
    private final LolApplication application;

    public ApplicationModule(LolApplication application) {
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
    LolApiTest provideLolApiTest(Context applicationContext) {
        return  new LolApiTestImpl(applicationContext);
    }
    @Provides
    @Singleton
    MemoryData provideMemoryData() {
        return  new MemoryData();
    }
}
