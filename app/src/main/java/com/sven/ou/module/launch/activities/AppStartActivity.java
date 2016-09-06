/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sven.ou.module.launch.activities;

import android.os.Bundle;

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
    }

    private void init() {
        setContentView(R.layout.act_app_start);
    }


    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new AppStartActivityModule(this));
    }
}
