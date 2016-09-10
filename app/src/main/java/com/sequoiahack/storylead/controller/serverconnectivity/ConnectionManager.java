package com.sequoiahack.storylead.controller.serverconnectivity;

import android.content.Context;
import android.util.Log;

import com.sequoiahack.storylead.app.AppConstant;
import com.sequoiahack.storylead.controller.serverconnectivity.interfaces.ServerInternalListener;

import java.io.File;

import retrofit.RetrofitError;

/**
 * Connection Manger to handle all the connection
 * Created by zac on 10/09/16.
 */
public class ConnectionManager implements ServerInternalListener {
    private ServerConnection mServerConnection;
    private DataSecureHandler mDataHandler;


    public ConnectionManager(Context context) {
        mServerConnection = new ServerConnection(ConnectionManager.this);
        mDataHandler = new DataSecureHandler();
    }

    public void sendVoiceToServer(File file) {
        mServerConnection.getUploadLink(file.getName(), mDataHandler.getAccessToken());
    }

    @Override
    public void onUploadLinkReceived(String url) {
        showLog("ConnectionManager - UploadLinkReceived -" + url);
    }

    @Override
    public void onUploadLinkGettingFailed() {
        showLog("ConnectionManager - UploadLink Getting Failed");
    }

    @Override
    public void onRetrofitError(RetrofitError error) {
        showLog("ConnectionManager - Retrofit error " + error);
    }

    protected void showLog(String message) {
        if (AppConstant.ENABLE_LOG)
            Log.d(AppConstant.APP_TAG, message);
    }
}
