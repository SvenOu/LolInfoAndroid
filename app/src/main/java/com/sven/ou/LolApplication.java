

package com.sven.ou;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import com.squareup.leakcanary.LeakCanary;
import com.sven.ou.common.config.Config;
import com.sven.ou.di.ApplicationModule;
import com.sven.ou.di.MainActivityModule;

import java.util.Arrays;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import dagger.ObjectGraph;

public class LolApplication extends Application {
    public static Application instance;
    private static final String TAG = LolApplication.class.getSimpleName();

    //与application周期绑定的对象图
    private ObjectGraph applicationGraph;

    @Override public void onCreate() {
        MultiDex.install(this.getApplicationContext());
        super.onCreate();
        instance = this;
        applicationGraph = ObjectGraph.create(getModules().toArray());
        LeakCanary.install(this);
        iniJMessage();
    }

    private void iniJMessage() {
        JMessageClient.init(getApplicationContext());
        if(Config.isDevelopMode()){
            JPushInterface.setDebugMode(true);
        }
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
    public static DisplayMetrics getDisplayMetrics(){
        return LolApplication.instance.getResources().getDisplayMetrics();
    }
}
