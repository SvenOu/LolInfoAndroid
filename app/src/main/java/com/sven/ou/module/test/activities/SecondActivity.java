package com.sven.ou.module.test.activities;

import android.os.Bundle;

import com.sven.ou.R;
import com.sven.ou.common.base.BaseActivity;
import com.sven.ou.di.SecondActivityModule;
import com.sven.ou.module.test.view.impl.SecondFragment;

import java.util.Arrays;
import java.util.List;

public class SecondActivity extends BaseActivity {
    private static final String TAG = SecondActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        setTitle(R.string.second_activity);
        if(null == savedInstanceState){
            addFragment(R.id.fra_container, new SecondFragment());
        }
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new SecondActivityModule(this));
    }
}
