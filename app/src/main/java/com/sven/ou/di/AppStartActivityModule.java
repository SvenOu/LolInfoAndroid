
package com.sven.ou.di;

import android.app.ProgressDialog;
import android.content.Context;

import com.sven.ou.module.launch.activities.AppStartActivity;
import com.sven.ou.module.launch.view.AppLaunchFragment;
import com.sven.ou.module.test.model.LolApiTest;
import com.sven.ou.module.test.model.RecusionTest;
import com.sven.ou.module.test.presenter.MainPresenter;
import com.sven.ou.module.test.presenter.impl.MainPresenterImpl;
import com.sven.ou.navigation.Navigator;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(
    injects = {AppStartActivity.class, AppLaunchFragment.class},
    addsTo = ApplicationModule.class,
    library = true
)
public class AppStartActivityModule {
    private final AppStartActivity activity;

    public AppStartActivityModule(AppStartActivity activity) {
        this.activity = activity;
    }

    @Named("activityContext")
    @Provides
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    MainPresenter provideMainPresenter(RecusionTest recusionTest, LolApiTest lolApiTest, Navigator navigator) {
        return new MainPresenterImpl(recusionTest, lolApiTest, navigator);
    }

    @Provides
    ProgressDialog provideMainPresenter(@Named("activityContext")Context context) {
        ProgressDialog dialog = new ProgressDialog(context, ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
