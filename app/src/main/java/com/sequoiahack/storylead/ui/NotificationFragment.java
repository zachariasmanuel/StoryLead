package com.sequoiahack.storylead.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sequoiahack.storylead.R;

/**
 * This fragment is loaded when user clicks on Notification button on BottomBar
 * Created by zac on 10/09/16.
 */
public class NotificationFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.notification_fragment, container, false);

        return layout;
    }
}
