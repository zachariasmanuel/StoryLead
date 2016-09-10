package com.sequoiahack.storylead.ui;


import android.support.v4.app.Fragment;
import android.util.Log;

import com.sequoiahack.storylead.app.AppConstant;

/**
 * Base fragment for all fragments
 * Created by zac on 10/09/16.
 */
public class BaseFragment extends Fragment {
    protected void showLog(String message) {
        if (AppConstant.ENABLE_LOG)
            Log.d(AppConstant.APP_TAG, message);
    }
}
