

package com.sven.ou;

import android.app.Application;

import com.igexin.sdk.PushManager;
import com.squareup.leakcanary.LeakCanary;
import com.sven.ou.di.ApplicationModule;
import com.sven.ou.di.MainActivityModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by sven-ou on 2016/6/8.
 */
public class AndroidApplication extends Application {

    private static final String TAG = AndroidApplication.class.getSimpleName();

    //与application周期绑定的对象图
    private ObjectGraph applicationGraph;

    @Override public void onCreate() {
        super.onCreate();
        applicationGraph = ObjectGraph.create(getModules().toArray());
        LeakCanary.install(this);
        PushManager.getInstance().initialize(this.getApplicationContext());
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
