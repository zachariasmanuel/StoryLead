package com.sequoiahack.storylead.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.sequoiahack.storylead.R;

/**
 * Main Activity of the app which loads the tab
 * Created by zac on 10/09/16.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        assert bottomBar != null;
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_favorites) {
                    showLog("Clicked on Favorites");
                } else if (tabId == R.id.tab_nearby) {
                    showLog("Clicked on Nearby");
                } else if (tabId == R.id.tab_friend) {
                    showLog("Clicked on Friends");
                }
            }
        });
    }


}
