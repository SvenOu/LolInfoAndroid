package com.sven.ou.common.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sven.ou.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    private Unbinder unbinder;

    private String fragmentId;
    private View rootView;
    private boolean cacheContentData = true;

    @SuppressLint("ValidFragment")
    public BaseFragment(String fragmentId) {
        this.fragmentId = fragmentId;
    }

    public BaseFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(null == rootView && cacheContentData){
            rootView = onCreateFragmentView(inflater, container, savedInstanceState);
        }
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * @return use for {@link BaseFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}, for butterknife bind.
     */
    protected abstract View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BaseActivity) getActivity()).inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public String getFragmentId() {
        return fragmentId;
    }

    public boolean isCacheContentData() {
        return cacheContentData;
    }

    public void setCacheContentData(boolean cacheContentData) {
        this.cacheContentData = cacheContentData;
    }
}
