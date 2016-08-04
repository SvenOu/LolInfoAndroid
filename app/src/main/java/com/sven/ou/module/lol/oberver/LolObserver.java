package com.sven.ou.module.lol.oberver;

import android.content.Context;
import android.widget.Toast;

import com.sven.ou.common.utils.Logger;

import rx.Observer;

/**
 * 用此抽象类统一处理错误信息和完成信息
 */
public abstract class LolObserver<T> implements Observer<T>{
    private static final String TAG = LolObserver.class.getSimpleName();

    private Context applicationContext;
    public LolObserver(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onCompleted() {
        Logger.i(TAG, "onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(applicationContext, e.getMessage(), Toast.LENGTH_LONG).show();
    }
}
