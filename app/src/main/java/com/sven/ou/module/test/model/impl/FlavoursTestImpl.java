package com.sven.ou.module.test.model.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.common.entity.Page;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.lol.entity.Area;
import com.sven.ou.module.lol.entity.Authors;
import com.sven.ou.module.lol.entity.Champion;
import com.sven.ou.module.lol.entity.OnlineStatus;
import com.sven.ou.module.lol.entity.SkillRank;
import com.sven.ou.module.lol.entity.UserHotInfo;
import com.sven.ou.module.lol.entity.thisweek.Hero;
import com.sven.ou.module.lol.oberver.LolObserver;
import com.sven.ou.module.lol.entity.UserArea;
import com.sven.ou.module.test.model.FlavoursTest;
import com.sven.ou.network.Network;

import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sven-ou on 2016/6/15.
 */
public class FlavoursTestImpl implements FlavoursTest {
    private static final String TAG = FlavoursTestImpl.class.getName();


    private Context applicationContext;

    public FlavoursTestImpl(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void printAllVariate() {
        try {
            ApplicationInfo ai = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            String umengChannel = bundle.getString("UMENG_CHANNEL");
            String appMode = bundle.getString("APP_MODE");
            boolean isDevelopMode = bundle.getBoolean("IS_DEVELOP_MODE");
            Logger.e(TAG, "---umengChannel: ---" + umengChannel);
            Logger.e(TAG, "---appMode: ---" + appMode);
            Logger.e(TAG, "---isDevelopMode: ---" + isDevelopMode);

        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
        } catch (NullPointerException e) {
            Logger.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
        }
    }

    @Override
    public void testDaiWaiLolDataApi() {
        Network.getDaiWanLolDataApi().getUserArea("SvenOu").
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<UserArea>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<UserArea>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });
        Network.getDaiWanLolDataApi().getUserHotInfo("U17347121168407285161", 5).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<UserHotInfo>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<UserHotInfo>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }

                });

        Network.getDaiWanLolDataApi().getSkillRank("1",1).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Page<List<SkillRank>>>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Page<List<SkillRank>>>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }

                });
        Network.getDaiWanLolDataApi().getFree().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Map<String, Hero>>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Map<String, Hero>>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolVideoApi().getAuthors().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Authors>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Authors>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolDataApi().getChampion().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Champion>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Champion>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolDataApi().getArea().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Area>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Area>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolDataApi().getChampionDetail("1").
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Map>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Map>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });
        Network.getDaiWanLolDataApi().getOnline("漆黑之火", "5").
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<OnlineStatus>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<OnlineStatus>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

    }
}
