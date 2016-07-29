package com.sven.ou.common.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.igexin.sdk.PushConsts;

/**
 * Created by sven-ou on 2016/7/28.
 */
public class PushReceiver extends BroadcastReceiver{
    private static final String TAG = PushReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
            case PushConsts.GET_CLIENTID:

                String cid = bundle.getString("clientid");
                // TODO:处理cid返回
                Log.e(TAG, "cid = " + cid);
                break;
            case PushConsts.GET_MSG_DATA:

                String taskid = bundle.getString("taskid");
                String messageid = bundle.getString("messageid");
                byte[] payload = bundle.getByteArray("payload");
                if (payload != null) {
                    String data = new String(payload);
                    // TODO:接收处理透传（payload）数据
                    Log.e(TAG, "data = " + data);
                }
                Log.e(TAG, "taskid = " + taskid);
                Log.e(TAG, "messageid = " + messageid);
                break;
            default:
                break;
        }
    }
}
