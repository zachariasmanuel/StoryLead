
package com.sequoiahack.storylead.controller.callmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.sequoiahack.storylead.app.AppConstant;

public class PhoneStateReceiver extends BroadcastReceiver {

    private String phoneNumber;

    @Override
    public void onReceive(Context context, Intent intent) {
        phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        String extraState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Bundle bundle = intent.getExtras();
        String phoneNr= bundle.getString("incoming_number");


        try {

            if (extraState != null) {
                if (extraState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                    showLog("OffHook - "+phoneNr);
                        /*Intent myIntent = new Intent(context,
                                RecordService.class);
						myIntent.putExtra("commandType",
								Constants.STATE_CALL_START);
						context.startService(myIntent);*/
                } else if (extraState
                        .equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                    showLog("State Idle - "+phoneNr);
                        /*Intent myIntent = new Intent(context,
								RecordService.class);
						myIntent.putExtra("commandType",
								Constants.STATE_CALL_END);
						context.startService(myIntent);*/
                } else if (extraState
                        .equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    showLog("Extra state ringing - "+phoneNr);
					/*	if (phoneNumber == null)
							phoneNumber = intent
									.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
						Intent myIntent = new Intent(context,
								RecordService.class);
						myIntent.putExtra("commandType",
								Constants.STATE_INCOMING_NUMBER);
						myIntent.putExtra("phoneNumber", phoneNumber);
						myIntent.putExtra("silentMode", silent);
						context.startService(myIntent);*/
                }

            }
        } catch (Exception e) {
				/*Log.e(Constants.TAG, "Exception");*/
            e.printStackTrace();
        }
    }

    protected void showLog(String message) {
        if (AppConstant.ENABLE_LOG)
            Log.d(AppConstant.APP_TAG, message);
    }


}
