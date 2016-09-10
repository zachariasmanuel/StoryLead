package com.sequoiahack.storylead.controller.data;

import android.util.Log;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.sequoiahack.storylead.app.AppConstant;
import com.sequoiahack.storylead.controller.data.tablemodels.CallData;

import java.util.List;

/**
 * DataManger class to handle database
 * Created by zac on 10/09/16.
 */
public class DataManager {

    public static void saveData(String filename, String name, String number, String status, String text, String sentiment, String emotion, String politicalLeaning, String topics, String summary) {
        CallData missedCall = new CallData(filename, name, number, status, text, sentiment, emotion, politicalLeaning, topics, summary);
        missedCall.save();
        showLog("Data saved");
    }

    public static List<CallData> getAllData() {
        Select select = new Select();
        return select.all().from(CallData.class).execute();
    }

    private static void showLog(String message) {
        if (AppConstant.ENABLE_LOG)
            Log.d(AppConstant.APP_TAG, message);
    }

    public static void updateStatus(String status, String filename) {
        showLog("Filename - " + filename);
        new Update(CallData.class)
                .set("status = ?", status)
                .where("filename = ?", filename)
                .execute();
    }
}
