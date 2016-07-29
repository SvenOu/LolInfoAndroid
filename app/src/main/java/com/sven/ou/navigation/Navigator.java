/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sven.ou.navigation;

import android.content.Context;
import android.content.Intent;

import com.sven.ou.module.module1.activities.SecondActivity;
import com.sven.ou.module.module1.activities.ThirdActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Navigator {

    @Inject
    public Navigator() {

    }

    public void geoToSecondView(Context context) {
        context.startActivity(new Intent(context, SecondActivity.class));
    }

    public void geoToThirdView(Context context) {
        context.startActivity(new Intent(context, ThirdActivity.class));
    }
}
