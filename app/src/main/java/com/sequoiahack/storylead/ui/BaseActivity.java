package com.sequoiahack.storylead.ui;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sequoiahack.storylead.app.AppConstant;

/**
 * Base activity
 * Created by zac on 10/09/16.
 */
public class BaseActivity extends AppCompatActivity {

    protected void showLog(String message) {
        if (AppConstant.ENABLE_LOG)
            Log.d(AppConstant.APP_TAG, message);
    }
}
