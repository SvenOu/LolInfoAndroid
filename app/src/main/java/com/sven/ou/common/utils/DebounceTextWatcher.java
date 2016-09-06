package com.sven.ou.common.utils;

import android.text.Editable;
import android.text.TextWatcher;


public abstract class DebounceTextWatcher implements TextWatcher {

    private static final String TAG = DebounceTextWatcher.class.getSimpleName();

    private int delayTime = 750;

    public DebounceTextWatcher() {
    }

    public DebounceTextWatcher(int delayTime) {
        this.delayTime = delayTime;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(final Editable editable) {
        //防止暴力后退
        String id = TAG + "afterTextChanged";
        Debouncer.debounce(id, new Runnable() {
            @Override
            public void run() {
                delayAfterTextChanged(String.valueOf(editable));
            }
        }, delayTime);
    }

    protected abstract void delayAfterTextChanged(String afterChangeText);

}
