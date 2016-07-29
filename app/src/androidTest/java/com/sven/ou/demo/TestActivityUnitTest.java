package com.sven.ou.demo;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.util.Log;
import android.widget.FrameLayout;

import com.sven.ou.R;
import com.sven.ou.module.module1.activities.SecondActivity;

/**
 * Created by sven-ou on 2016/7/15.
 */
public class TestActivityUnitTest extends ActivityUnitTestCase<SecondActivity> {

    private static final String TAG = TestActivityUnitTest.class.getSimpleName();

    private Intent mStartIntent;
    private FrameLayout mfraContainer;

    public TestActivityUnitTest() {
        super(SecondActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        Log.d(TAG, "setUp");
        super.setUp();
        mStartIntent = new Intent(Intent.ACTION_MAIN);
    }

    @MediumTest
    public void testPreconditions() {
        // TODO: 2016/7/15  activity需要手动启动
       // startActivity(mStartIntent, null, null);

        mfraContainer = (FrameLayout) getActivity().findViewById(R.id.fra_container);

        assertNotNull(getActivity());
        assertNotNull(mfraContainer);
    }

//    @MediumTest
//    public void testSubLaunch() {
//        SecondActivity activity
//                = startActivity(mStartIntent, null, null);
//        mButton = (Button) activity.findViewById(R.id.go);
//
//        mButton.performClick();
//
//        assertNotNull(getStartedActivityIntent());
//        assertTrue(isFinishCalled());
//    }

//    @MediumTest
//    public void testLifeCycleCreate() {
//        SecondActivity activity
//                = startActivity(mStartIntent, null, null);
//        getInstrumentation().callActivityOnStart(activity);
//        getInstrumentation().callActivityOnResume(activity);
//
//        getInstrumentation().callActivityOnPause(activity);
//
//        getInstrumentation().callActivityOnStop(activity);
//    }

    @Override
    protected void tearDown() throws Exception {
        Log.d(TAG, "tearDown");
        super.tearDown();
    }
}
