

package com.sven.ou;

import android.app.Application;

import com.igexin.sdk.PushManager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.squareup.leakcanary.LeakCanary;
import com.sven.ou.common.config.Config;
import com.sven.ou.di.ApplicationModule;
import com.sven.ou.di.MainActivityModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by sven-ou on 2016/6/8.
 */
public class LolApplication extends Application {
    public static Application instance;
    private static final String TAG = LolApplication.class.getSimpleName();
    public static final int DISK_CACHE_SIZE = 50 * 1024 * 1024;
    //与application周期绑定的对象图
    private ObjectGraph applicationGraph;

    @Override public void onCreate() {
        super.onCreate();
        instance = this;
        applicationGraph = ObjectGraph.create(getModules().toArray());
        LeakCanary.install(this);
        PushManager.getInstance().initialize(this.getApplicationContext());
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
                this).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(DISK_CACHE_SIZE)
                .tasksProcessingOrder(QueueProcessingType.LIFO);

        if(Config.isDevelopMode())
            builder.writeDebugLogs(); // Remove for release app

        ImageLoaderConfiguration config = builder.build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * {@link ApplicationModule#application}  test {@link Application}
     * @return 646456
     * @throws NullPointerException
     * @see MainActivityModule
     */
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ApplicationModule(this));
    }

    public ObjectGraph getApplicationGraph() {
        return applicationGraph;
    }
}
