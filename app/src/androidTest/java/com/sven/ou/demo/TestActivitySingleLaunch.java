package com.sven.ou.demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.test.SingleLaunchActivityTestCase;
import android.widget.FrameLayout;

import com.sven.ou.R;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.test.activities.MainActivity;

/**
 * Created by sven-ou on 2016/7/14.
 */
public class TestActivitySingleLaunch extends SingleLaunchActivityTestCase<MainActivity> {
    private static final String TAG = TestActivitySingleLaunch.class.getSimpleName();
    private Context ctx;
    private AppCompatActivity mActivity;
    private FrameLayout fraContainer;
    private String resourceString;

    public TestActivitySingleLaunch() {
        super("com.sven.ou", MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        Logger.d(TAG, "setUp");
        super.setUp();
        mActivity = getActivity();
        ctx = getActivity().getApplicationContext();
        fraContainer = (FrameLayout) mActivity.findViewById(R.id.fra_container);
        resourceString = mActivity.getString(R.string.choose_play_action);
    }

//    public void testStart() {
//        Intent intent = new Intent(ctx, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        ctx.startActivity(intent);
//    }

    public void testText() {
        Logger.d(TAG, "Running testText");
        assertEquals(resourceString, mActivity.getTitle());
    }

    public void testA() {
        Logger.d(TAG, "Running testA");
        assertNotNull(resourceString);
    }

    public void testPreconditions() {
        Logger.d(TAG, "Running testPreconditions");
        assertNotNull(fraContainer);
    }



    @Override
    protected void tearDown() throws Exception {
        Logger.d(TAG, "tearDown");
        super.tearDown();
    }
}
