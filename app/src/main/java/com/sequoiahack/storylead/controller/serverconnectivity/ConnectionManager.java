package com.sequoiahack.storylead.controller.serverconnectivity;

import android.content.Context;
import android.util.Log;

import com.sequoiahack.storylead.app.AppConstant;
import com.sequoiahack.storylead.controller.data.DataManager;
import com.sequoiahack.storylead.controller.data.tablemodels.CallData;
import com.sequoiahack.storylead.controller.serverconnectivity.interfaces.ServerInternalListener;
import com.sequoiahack.storylead.controller.utils.FileManager;

import retrofit.RetrofitError;

/**
 * Connection Manger to handle all the connection
 * Created by zac on 10/09/16.
 */
public class ConnectionManager implements ServerInternalListener {
    private ServerConnection mServerConnection;
    private DataSecureHandler mDataHandler;
    private CallData callData;


    public ConnectionManager(Context context) {
        mServerConnection = new ServerConnection(ConnectionManager.this);
        mDataHandler = new DataSecureHandler();
    }

    public void sendVoiceToServer(CallData callData) {
       showLog("Sending upload link get request to server");
        this.callData = callData;
        /*mServerConnection.getUploadLink(callData.filename, mDataHandler.getAccessToken());*/
        startUpload("");
    }

    private void startUpload(String url) {
        showLog("Starting upload");
        mServerConnection.uploadFile(url, FileManager.getFilePath(callData.filename));
    }

    @Override
    public void onUploadLinkReceived(String url) {
        showLog("ConnectionManager - UploadLinkReceived - " + url);
        startUpload(url);
    }

    @Override
    public void onUploadLinkGettingFailed() {
        showLog("ConnectionManager - UploadLink Getting Failed");
    }

    @Override
    public void onRetrofitError(RetrofitError error) {
        showLog("ConnectionManager - Retrofit error " + error);
    }

    @Override
    public void onUploadSuccess() {
        showLog("ConnectionManager - File Upload Success");
        updateDBRequested();
    }


    @Override
    public void onUploadFailed() {
        showLog("ConnectionManager - File Upload Failed");
    }

    private void updateDBRequested() {
        DataManager.updateStatus("requested",callData.filename);
    }

    protected void showLog(String message) {
        if (AppConstant.ENABLE_LOG)
            Log.d(AppConstant.APP_TAG, message);
    }
}
