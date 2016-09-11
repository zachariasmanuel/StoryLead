package com.sequoiahack.storylead.controller.gcm;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmListenerService;
import com.sequoiahack.storylead.app.AppConstant;

/**
 * Created by zac on 11/09/16.
 */
public class MyGcmListenerService extends GcmListenerService {
    private static final String TAG = "MyGcmListenerService";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        showLog("MyGCMListenerService - From  " + from);
        showLog("MyGCMListenerService - DATA: " + data);
        showLog(data.getString("hello"));
        showLog("MyGCMListenerService - Message: " + data.getString("data"));
        Toast.makeText(this, "GCM received", Toast.LENGTH_SHORT).show();

    }

    protected void showLog(String message) {
        if (AppConstant.ENABLE_LOG)
            Log.d(AppConstant.APP_TAG, message);
    }


}
