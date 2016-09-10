package com.sequoiahack.storylead.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Preference manager
 * Created by zac on 10/09/16.
 */
public class PreferenceManager {

    private String mFileName = "StoryLeadPreference";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor sEditor;

    public PreferenceManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
    }

    public void setUserName(String name) {
        sEditor = mSharedPreferences.edit();
        sEditor.putString("username", name);
        sEditor.commit();
    }

    public String getUserName() {
        return mSharedPreferences.getString("username", "");
    }

    public void setPassword(String name) {
        sEditor = mSharedPreferences.edit();
        sEditor.putString("password", name);
        sEditor.commit();
    }

    public String getPassword() {
        return mSharedPreferences.getString("password", "");
    }

}
