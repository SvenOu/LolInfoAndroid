package com.sven.ou;

import android.test.ApplicationTestCase;
import android.util.Log;

import com.sven.ou.common.utils.Logger;

import java.util.List;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<AndroidApplication> {

    private static final String TAG = ApplicationTest.class.getSimpleName();

    private AndroidApplication mAndroidApplication;
    private List mModules;

    public ApplicationTest() {
        super(AndroidApplication.class);
    }

    @Override
    protected void setUp() throws Exception {
        Logger.d(TAG, "setUp");
        super.setUp();
        createApplication();
        mAndroidApplication = getApplication();
    }

    public void testA() {
        Logger.d(TAG, "Running testA");
        mModules = mAndroidApplication.getModules();
        assertNotNull(mModules);
    }

    @Override
    protected void tearDown() throws Exception {
        Logger.d(TAG, "tearDown");
        super.tearDown();
    }
}