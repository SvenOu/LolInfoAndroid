
package com.sven.ou.module.lol.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sven.ou.R;
import com.sven.ou.common.base.BaseActivity;
import com.sven.ou.di.CheeseDetailActivityModule;
import com.sven.ou.module.lol.entity.Cheeses;

import java.util.Arrays;
import java.util.List;

public class CheeseDetailActivity extends BaseActivity {

    public static final String EXTRA_NAME = "cheese_name";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(EXTRA_NAME);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(cheeseName);

        loadBackdrop();
    }

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        ImageLoader.getInstance().displayImage("drawable://"+ Cheeses.getRandomCheeseDrawable(), imageView);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new CheeseDetailActivityModule(this));
    }
}
