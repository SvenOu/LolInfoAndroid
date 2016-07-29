package com.sven.ou.module.module1.model.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sven.ou.R;
import com.sven.ou.common.executor.RxAsyncHelper;
import com.sven.ou.module.lol.entity.PlayerInfo;
import com.sven.ou.module.module1.model.FlavoursTest;
import com.sven.ou.network.Network;
import com.sven.ou.network.api.Record;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
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
            Log.e(TAG, "---umengChannel: ---" + umengChannel);
            Log.e(TAG, "---appMode: ---" + appMode);
            Log.e(TAG, "---isDevelopMode: ---" + isDevelopMode);

        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
        } catch (NullPointerException e) {
            Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
        }
    }

    @Override
    public void testObservable() {
//        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> observer) {
//                try {
//                    if (!observer.isUnsubscribed()) {
//                        for (int i = 1; i < 5; i++) {
//                            Thread.sleep(3000);
//                            observer.onNext(i);
//                        }
//                        observer.onCompleted();
//                    }
//                } catch (Exception e) {
//                    observer.onError(e);
//                }
//            }
//        });
//
//        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
//            @Override
//            public void onNext(Integer item) {
//                System.out.println("Next: " + item);
//                Toast.makeText(applicationContext, "Next "+item ,Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onError(Throwable error) {
//                System.err.println("Error: " + error.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("Sequence complete.");
//                Toast.makeText(applicationContext, "complete" ,Toast.LENGTH_LONG).show();
//            }
//        };
//
//        RxAsyncHelper.executeAsync(observable, subscriber);

//        Network.getLolApi().getPlayerInfo("班德尔城", "SvenOu").
//                subscribeOn(Schedulers.io()).
//                observeOn(AndroidSchedulers.mainThread()).
//                subscribe(new Observer<PlayerInfo>() {
//            @Override
//            public void onCompleted() {
//                Log.e(TAG, "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG, e.getMessage());
//                Toast.makeText(applicationContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNext(PlayerInfo playerInfo) {
//                Log.e(TAG, playerInfo.toString());
//            }
//        });
//
//        Network.getLolApi().getRecord("班德尔城", "SvenOu").
//                subscribeOn(Schedulers.io()).
//                observeOn(AndroidSchedulers.mainThread()).
//                subscribe(new Observer<Record>() {
//            @Override
//            public void onCompleted() {
////                Log.e(TAG, "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG, e.getMessage());
//                Toast.makeText(applicationContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNext(Record record) {
//                Log.e(TAG, record.toString());
//            }
//        });
    }
}
