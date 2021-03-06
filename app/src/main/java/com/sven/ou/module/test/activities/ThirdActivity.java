package com.sven.ou.module.test.activities;

import android.os.Bundle;

import com.sven.ou.R;
import com.sven.ou.common.base.BaseActivity;
import com.sven.ou.di.ThirdActivityModule;
import com.sven.ou.module.test.view.impl.ThirdFragment;

import java.util.Arrays;
import java.util.List;

public class ThirdActivity extends BaseActivity {
    private static final String TAG = ThirdActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_third);
        setTitle(R.string.third_activity);
        if(null == savedInstanceState){
            addFragment(R.id.fra_container, new ThirdFragment(null));
        }
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ThirdActivityModule(this));
    }
}
