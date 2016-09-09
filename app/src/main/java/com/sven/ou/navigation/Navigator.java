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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.sven.ou.module.lol.activities.FilterVideoActivity;
import com.sven.ou.module.lol.activities.VideoPlayActivity;
import com.sven.ou.module.lol.entity.Author;
import com.sven.ou.module.lol.entity.Video;
import com.sven.ou.module.lol.entity.thisweek.Hero;
import com.sven.ou.module.test.activities.SecondActivity;
import com.sven.ou.module.test.activities.ThirdActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 针对全局的跳转
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {

    }

    public void goToSecondView(Context context) {
        context.startActivity(new Intent(context, SecondActivity.class));
    }

    public void goToThirdView(Context context) {
        context.startActivity(new Intent(context, ThirdActivity.class));
    }

    public void goToVideoPlayActivity(Activity activity, Video video) {
        VideoPlayActivity.StartVideoPlayActivity(activity, video);
    }
    public void goToFilterActivity(Activity activity, Hero hero) {
        FilterVideoActivity.startFilterVideoActivity(activity, FilterVideoActivity.FILTER_TYPE_HERO, hero, null);
    }
    public void goToFilterActivity(Activity activity, Author author) {
        FilterVideoActivity.startFilterVideoActivity(activity, FilterVideoActivity.FILTER_TYPE_AUTHOR, null, author);
    }


}
