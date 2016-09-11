package com.sequoiahack.storylead.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.sequoiahack.storylead.R;
import com.sequoiahack.storylead.controller.gcm.RegistrationIntentService;

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
        askPermission();

        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
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

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void askPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE,android.Manifest.permission.PROCESS_OUTGOING_CALLS, android.Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
        }
    }


}
