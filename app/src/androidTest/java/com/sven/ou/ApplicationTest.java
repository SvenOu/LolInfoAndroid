package com.sven.ou;

import android.test.ApplicationTestCase;

import com.sven.ou.common.utils.Logger;

import java.util.List;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<LolApplication> {

    private static final String TAG = ApplicationTest.class.getSimpleName();

    private LolApplication mLolApplication;
    private List mModules;

    public ApplicationTest() {
        super(LolApplication.class);
    }

    @Override
    protected void setUp() throws Exception {
        Logger.d(TAG, "setUp");
        super.setUp();
        createApplication();
        mLolApplication = getApplication();
    }

    public void testA() {
        Logger.d(TAG, "Running testA");
        mModules = mLolApplication.getModules();
        assertNotNull(mModules);
    }

    @Override
    protected void tearDown() throws Exception {
        Logger.d(TAG, "tearDown");
        super.tearDown();
    }
}