package com.sven.ou.common.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class ViewUtil {
    public static void hideKeyboardFrom(Context context, View targetTextView) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(targetTextView.getWindowToken(), 0);
    }

}
