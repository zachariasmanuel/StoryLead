package com.sequoiahack.storylead.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.sequoiahack.storylead.R;

/**
 * Main Activity of the app which loads the tab
 * Created by zac on 10/09/16.
 */
public class MainActivity extends BaseActivity {

    private FrameLayout masterFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        masterFrameLayout = (FrameLayout) findViewById(R.id.masterFrameLayout);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
       // loadFragment(R.id.tab_recording);

        assert bottomBar != null;
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                loadFragment(tabId);
            }
        });
    }


    private void loadFragment(int tabId) {
        if (tabId == R.id.tab_recording) {
            RecordingsFragment recordingFragment = new RecordingsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.masterFrameLayout, recordingFragment)
                    .commit();
        } else if (tabId == R.id.tab_profile) {
            ProfileFragment profileFragment = new ProfileFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.masterFrameLayout, profileFragment)
                    .commit();
        } else if (tabId == R.id.tab_record) {
            RecordFragment recordFragment = new RecordFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.masterFrameLayout, recordFragment)
                    .commit();
        } else if (tabId == R.id.tab_notification) {
            NotificationFragment notificationFragment = new NotificationFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.masterFrameLayout, notificationFragment)
                    .commit();
        } else if (tabId == R.id.tab_settings) {
            SettingsFragment settingsFragment = new SettingsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.masterFrameLayout, settingsFragment)
                    .commit();
        }
    }

}
