
package com.sven.ou.common.executor;

import java.util.concurrent.Executor;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class RxAsyncHelper {

    private Executor mThreadExecutor;
    private Scheduler mPostThread;

    /**
     * 静态内部类单例写法
     */
    private static class SingletonHolder {
        private static final RxAsyncHelper INSTANCE = new RxAsyncHelper();
    }

    private RxAsyncHelper() {
        this.mThreadExecutor = new JobExecutor();
        this.mPostThread = AndroidSchedulers.mainThread();
    }

    public static final RxAsyncHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    private Subscription ext(Observable observable, Subscriber subscriber) {
        return observable
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostThread)
                .subscribe(subscriber);
    }

    @SuppressWarnings("unchecked")
    public static Subscription execute(Observable observable,
                                       Subscriber subscriber,
                                       Scheduler scheduler,
                                       Scheduler postScheduler) {
        return observable
                .subscribeOn(scheduler)
                .observeOn(postScheduler)
                .subscribe(subscriber);
    }

    public static Subscription executeAsync(Observable observable, Subscriber subscriber) {
        return RxAsyncHelper.getInstance().ext(observable, subscriber);
    }
}
