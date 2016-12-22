
package com.sven.ou.common.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sven.ou.LolApplication;

import java.util.List;

import butterknife.ButterKnife;
import dagger.ObjectGraph;

public abstract class BaseActivity extends AppCompatActivity {

    private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the activity graph by .plus-ing our modules onto the application graph.
        LolApplication application = (LolApplication) getApplication();
        activityGraph = application.getApplicationGraph().plus(getModules().toArray());
        // Inject ourselves so subclasses will have dependencies fulfilled when this method returns.
        activityGraph.inject(this);
    }

    /**
     * @param layoutResID
     * auto bind for ButterKnife
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initContentView();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initContentView();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        initContentView();
    }

    private void initContentView() {
        ButterKnife.bind(this);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(int containerViewId, android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }
    protected void toast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
    protected void toast(int msg){
        toast(getString(msg));
    }

    protected abstract List<Object> getModules();

    /** Inject the supplied {@code object} using the activity-specific graph. */
    public void inject(Object object) {
        activityGraph.inject(object);
    }

}
