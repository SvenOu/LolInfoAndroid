package com.sven.ou;

import org.junit.Test;

import rx.Observer;
import rx.subjects.PublishSubject;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest extends BaseUnitTest{
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testPublishSubject() {
        PublishSubject<String> stringPublishSubject = PublishSubject.create();
        stringPublishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                println("Observable completed");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                println("Observer consumed " + s);
            }
        });
        stringPublishSubject.onNext("hello world");
        stringPublishSubject.onCompleted();
    }

}