
package com.sven.ou.di;

import android.app.ProgressDialog;
import android.content.Context;

import com.sven.ou.module.lol.activities.MainViewActivity;
import com.sven.ou.module.lol.presenter.AuthorInfoPresenter;
import com.sven.ou.module.lol.presenter.NewestVideoPresenter;
import com.sven.ou.module.lol.presenter.SearchUserPresenter;
import com.sven.ou.module.lol.presenter.SearchVideoDialogPresenter;
import com.sven.ou.module.lol.presenter.impl.AuthorInfoPresenterImpl;
import com.sven.ou.module.lol.presenter.impl.NewestVideoPresenterImpl;
import com.sven.ou.module.lol.presenter.impl.SearchUserPresenterImpl;
import com.sven.ou.module.lol.presenter.impl.SearchVideoDialogPresenterImpl;
import com.sven.ou.module.lol.view.AboutUSFragment;
import com.sven.ou.module.lol.view.AuthorInfoFragment;
import com.sven.ou.module.lol.view.DiscoverFragment;
import com.sven.ou.module.lol.view.FriendFragment;
import com.sven.ou.module.lol.view.HomeFragment;
import com.sven.ou.module.lol.view.MyFragment;
import com.sven.ou.module.lol.view.NewestVideoFragment;
import com.sven.ou.module.lol.view.SearchUserFragment;
import com.sven.ou.module.lol.view.SearchVideoDialog;
import com.sven.ou.module.lol.view.SettingFragment;
import com.sven.ou.navigation.ActivityScreenNavigator;
import com.sven.ou.navigation.Navigator;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(
    injects = {
            MainViewActivity.class,
            AuthorInfoFragment.class,
            NewestVideoFragment.class,
            HomeFragment.class,
            FriendFragment.class,
            DiscoverFragment.class,
            MyFragment.class,
            SettingFragment.class,
            AboutUSFragment.class,
            SearchUserFragment.class
    },
    addsTo = ApplicationModule.class,
    library = true
)
public class MainViewActivityModule {
    private final MainViewActivity activity;

    public MainViewActivityModule(MainViewActivity activity) {
        this.activity = activity;
    }

    @Named("activityContext")
    @Provides
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    ActivityScreenNavigator provideActivityScreenNavigator() {
        return new ActivityScreenNavigator(activity);
    }
    @Provides
    AuthorInfoPresenter provideAuthorInfoPresenter(Context context) {
        return new AuthorInfoPresenterImpl(context);
    }

    @Provides
    NewestVideoPresenter provideNewestVideoPresenter(Context context) {
        return new NewestVideoPresenterImpl(context);
    }

    @Provides
    SearchVideoDialogPresenter provideSearchVideoDialogPresenter(Context context) {
        return new SearchVideoDialogPresenterImpl(context);
    }
    @Provides
    SearchUserPresenter provideSearchUserPresenter(Context context) {
        return new SearchUserPresenterImpl(context);
    }
    @Provides
    SearchVideoDialog provideSearchVideoDialog( Navigator navigator, SearchVideoDialogPresenter searchVideoDialogPresenter) {
        return new SearchVideoDialog(activity, navigator, searchVideoDialogPresenter);
    }

    @Provides
    ProgressDialog provideMainPresenter(@Named("activityContext")Context context) {
        ProgressDialog dialog = new ProgressDialog(context, ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
