
package com.sven.ou.module.launch.activities;

import android.os.Bundle;

import com.activeandroid.util.Log;
import com.sven.ou.R;
import com.sven.ou.common.base.BaseActivity;
import com.sven.ou.di.AppStartActivityModule;

import java.util.Arrays;
import java.util.List;

public class AppStartActivity extends BaseActivity {

    public static final String TAG = AppStartActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        toast(stringFromJNI());
    }

    private void init() {
        setContentView(R.layout.act_app_start);
    }


    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new AppStartActivityModule(this));
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
