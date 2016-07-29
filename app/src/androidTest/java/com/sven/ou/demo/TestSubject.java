package com.sven.ou.demo;

import android.test.InstrumentationTestCase;
import android.util.Log;

import rx.Observer;
import rx.subjects.PublishSubject;

/**
 * Created by sven-ou on 2016/7/14.
 */
public class TestSubject extends InstrumentationTestCase {
    private static final String TAG = TestSubject.class.getSimpleName();


    /**
     * setUp 和 tearDown方法可省略
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        Log.d(TAG, "setUp(): " + getName());
        super.setUp();
    }

    public void testPublishSubject() {
        PublishSubject<String> stringPublishSubject = PublishSubject.create();
        stringPublishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Observable completed");

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Observer consumed " + s);
            }
        });
        stringPublishSubject.onNext("hello world");
        stringPublishSubject.onCompleted();
    }

    @Override
    protected void tearDown() throws Exception {
        Log.d(TAG, "tearDown(): " + getName());
        super.tearDown();
    }

}
