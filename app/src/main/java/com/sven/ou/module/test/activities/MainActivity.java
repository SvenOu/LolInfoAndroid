package com.sven.ou.module.test.activities;

import android.os.Bundle;

import com.sven.ou.R;
import com.sven.ou.common.base.BaseActivity;
import com.sven.ou.di.MainActivityModule;
import com.sven.ou.module.test.view.impl.MainFragment;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);
        setTitle(R.string.choose_play_action);
        if(null == savedInstanceState){
            addFragment(R.id.fra_container, new MainFragment());
        }
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MainActivityModule(this));
    }
}
